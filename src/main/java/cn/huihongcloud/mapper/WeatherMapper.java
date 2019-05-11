package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.weather.HourlyWeather;
import cn.huihongcloud.entity.weather.Weather;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/9/8
 */
@Repository
public interface WeatherMapper {

    List<String> getCurrentAreas();

    int add(Weather weather);

    int addHourly(HourlyWeather hourlyWeather);

    List<Weather> getWeatherInRange(@Param("adcode") String adcode, @Param("lo") Date lo, @Param("hi") Date hi);

    List<Weather> getWeatherList(@Param("adcode") String adcode, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<HourlyWeather> getHourlyWeatherList(@Param("adcode") String adcode, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Weather> getMonthlyWeatherList(@Param("adcode") String adcode, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
