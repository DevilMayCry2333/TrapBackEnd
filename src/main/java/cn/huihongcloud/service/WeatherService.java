package cn.huihongcloud.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.huihongcloud.entity.weather.HourlyWeather;
import cn.huihongcloud.entity.weather.HourlyWeatherOutput;
import cn.huihongcloud.entity.weather.MonthlyWeatherOutput;
import cn.huihongcloud.entity.weather.Weather;
import cn.huihongcloud.entity.weather.WeatherOutput;
import cn.huihongcloud.mapper.WeatherMapper;
import cn.huihongcloud.util.DistUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/8/27
 */
@Service
public class WeatherService {

    //private final String HE_WEATHER_API = "https://free-api.heweather.com/s6/weather/forecast?key=5faeef6d42ae4c20bb5c4730b834bf73&location=CN";
    //private final String HE_WEATHER_NOW_API = "https://free-api.heweather.net/s6/weather/now?key=5faeef6d42ae4c20bb5c4730b834bf73&location=CN";
    private final String HE_WEATHER_API = "https://free-api.heweather.com/s6/weather/forecast?key=718e3fa43356434eb9c67f8fe182e693&location=CN";
    private final String HE_WEATHER_NOW_API = "https://free-api.heweather.net/s6/weather/now?key=718e3fa43356434eb9c67f8fe182e693&location=CN";

    private OkHttpClient okHttpClient;
    private ObjectMapper objectMapper;
    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Autowired
    private WeatherMapper weatherMapper;

    @Autowired
    @Qualifier("weatherData")
    private Map<String, String> weatherData;

    @Autowired
    private DistUtil mDistUtil;

    public WeatherService() {
        okHttpClient = new OkHttpClient();
        objectMapper = new ObjectMapper();
    }

    /**
     * 通过id获取天气
     * @param id 地区id
     * @return 天气对象
     * @throws Exception 异常
     */
    public Weather getWeatherById(String id) throws Exception {
        Response response;
        Weather weather = new Weather();
        // 请求和风天气
        response = okHttpClient.newCall(new Request.Builder()
                .url(HE_WEATHER_API + id)
                .build()).execute();
        // 获取当前天气的json
        String he_weather_json = response.body().string();

        System.out.println(he_weather_json);
        JsonNode he_weather = objectMapper.readTree(he_weather_json).get("HeWeather6").get(0).get("daily_forecast").get(0);
        weather.setTempMax(Integer.parseInt(he_weather.get("tmp_max").textValue()));
        weather.setTempMin(Integer.parseInt(he_weather.get("tmp_min").textValue()));
        weather.setHum(Integer.parseInt(he_weather.get("hum").textValue()));
        weather.setDate(new Date());

        return weather;
    }

    public HourlyWeather getHourlyWeatherById(String id) throws IOException {
        Response response;

        HourlyWeather hourlyWeather = new HourlyWeather();
        response = okHttpClient.newCall(new Request.Builder().url(HE_WEATHER_NOW_API + id).build()).execute();
        String data = response.body().string();
        JsonNode node = objectMapper.readTree(data).get("HeWeather6").get(0).get("now");
        hourlyWeather.setCloud(Integer.parseInt(node.get("cloud").textValue()));
        hourlyWeather.setCondCode(node.get("cond_code").textValue());
        hourlyWeather.setCondTxt(node.get("cond_txt").textValue());
        hourlyWeather.setFl(Integer.parseInt(node.get("fl").textValue()));
        hourlyWeather.setHum(Integer.parseInt(node.get("hum").textValue()));
        hourlyWeather.setPcpn(Double.parseDouble(node.get("pcpn").textValue()));
        hourlyWeather.setPres(Integer.parseInt(node.get("pres").textValue()));
        hourlyWeather.setTmp(Integer.parseInt(node.get("tmp").textValue()));
        hourlyWeather.setVis(Integer.parseInt(node.get("vis").textValue()));
        hourlyWeather.setWindDeg(Integer.parseInt(node.get("wind_deg").textValue()));
        hourlyWeather.setWindDir(node.get("wind_dir").textValue());
        hourlyWeather.setWindSc(Integer.parseInt(node.get("wind_sc").textValue()));
        hourlyWeather.setWindSpd(Integer.parseInt(node.get("wind_spd").textValue()));
        hourlyWeather.setDate(new Date());
        return hourlyWeather;
    }

    @Scheduled(cron = "0 0 12 * * ?")
//    @Scheduled(fixedRate = 36000)
    public void weatherSchedule() throws Exception {
        List<String> currentAreas = weatherMapper.getCurrentAreas();
        for (String area: currentAreas) {

            Weather weather = getWeatherById(weatherData.get(area));
            weather.setAdcode(area);
            weather.setTempMean((weather.getTempMax() + weather.getTempMin()) / 2);
            weatherMapper.add(weather);
        }
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void hourlyWeatherSchedule() throws IOException {
        List<String> currentAreas = weatherMapper.getCurrentAreas();
        for (String area: currentAreas) {
            HourlyWeather weather = getHourlyWeatherById(weatherData.get(area));
            weather.setAdcode(area);
            weatherMapper.addHourly(weather);
        }
    }

    public List<Weather> getWeatherInRange(String adcode, Date lo, Date hi) {
        List<Weather> weatherData = weatherMapper.getWeatherInRange(adcode, lo, hi);
        List<Weather> ret = new ArrayList<>();
        int subDays = (int) ((hi.getTime() - lo.getTime()) / 24 / 3600 / 1000);
        for (int i = 0; i < subDays; ++i) {
            Weather weather = new Weather();
            weather.setDate(new Date(lo.getTime() + i * 24 * 3600 * 1000L));
            System.out.println(outputDateFormat.format(weather.getDate()));
            ret.add(weather);
        }
        System.out.println(ret.size());
        int index = 0;
        for (int i = 0; i < weatherData.size(); ++i) {
            Weather weather = weatherData.get(i);
            String dateA = inputDateFormat.format(weather.getDate());
            for (int j = index; j < subDays; ++j) {
                System.out.println(j);
                String dateB = inputDateFormat.format(ret.get(j).getDate());
                if (dateA.equals(dateB)) {
                    ret.set(j, weather);
                    index = j;
                }
            }
        }
        return ret;
    }

    public List<Weather> getWeatherList(String adcode, String startDate, String endDate) {
        return weatherMapper.getWeatherList(adcode, startDate, endDate);
    }

    public List<HourlyWeather> getHourlyWeatherList(String adcode, String startDate, String endDate) {
        return weatherMapper.getHourlyWeatherList(adcode, startDate, endDate);
    }

    public List<Weather> getMonthlyWeatherList(String adcode, String startDate, String endDate) {
        return weatherMapper.getMonthlyWeatherList(adcode, startDate, endDate);
    }

    public Workbook exportWeather(String adcode, String startDate, String endDate) {
        List<Weather> weatherList = weatherMapper.getWeatherList(adcode, startDate, endDate);
        List<WeatherOutput> weatherOutputList = new ArrayList<>();
        for (Weather weather: weatherList) {
            WeatherOutput weatherOutput = new WeatherOutput();
            BeanUtils.copyProperties(weather, weatherOutput);
            weatherOutputList.add(weatherOutput);
        }
        String[] names = mDistUtil.getNames(adcode, null);
        String areaName = names[0] + names[1] + names[2];
        return ExcelExportUtil.exportExcel(new ExportParams(areaName + "天气信息", "天气信息"), WeatherOutput.class, weatherOutputList);

    }

    public Workbook exportHourlyWeather(String adcode, String startDate, String endDate) {
        List<HourlyWeather> weatherList = weatherMapper.getHourlyWeatherList(adcode, startDate, endDate);
        List<HourlyWeatherOutput> weatherOutputList = new ArrayList<>();
        for (HourlyWeather weather: weatherList) {
            HourlyWeatherOutput weatherOutput = new HourlyWeatherOutput();
            BeanUtils.copyProperties(weather, weatherOutput);
            weatherOutputList.add(weatherOutput);
        }
        String[] names = mDistUtil.getNames(adcode, null);
        String areaName = names[0] + names[1] + names[2];
        return ExcelExportUtil.exportExcel(new ExportParams(areaName + "逐小时天气信息", "逐小时天气信息"), HourlyWeatherOutput.class, weatherOutputList);

    }
    public Workbook exportMonthlyWeather(String adcode, String startDate, String endDate) {
        List<Weather> weatherList = weatherMapper.getMonthlyWeatherList(adcode, startDate, endDate);
        List<MonthlyWeatherOutput> weatherOutputList = new ArrayList<>();
        for (Weather weather: weatherList) {
            MonthlyWeatherOutput weatherOutput = new MonthlyWeatherOutput();
            BeanUtils.copyProperties(weather, weatherOutput);
            weatherOutputList.add(weatherOutput);
        }
        String[] names = mDistUtil.getNames(adcode, null);
        String areaName = names[0] + names[1] + names[2];
        return ExcelExportUtil.exportExcel(new ExportParams(areaName + "每月天气信息", "每月天气信息"), MonthlyWeatherOutput.class, weatherOutputList);

    }



}
