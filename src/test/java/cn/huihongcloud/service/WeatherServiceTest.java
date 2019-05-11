package cn.huihongcloud.service;

import cn.huihongcloud.entity.weather.HourlyWeather;
import cn.huihongcloud.entity.weather.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by 钟晖宏 on 2018/8/27
 */
public class WeatherServiceTest {

    @Test
    public void getWeatherById() {
        WeatherService weatherService = new WeatherService();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Weather weather = weatherService.getWeatherById("101230112");
            System.out.println(objectMapper.writeValueAsString(weather));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHourlyWeatherById() throws IOException {
        WeatherService weatherService = new WeatherService();
        HourlyWeather hourlyWeather = weatherService.getHourlyWeatherById("101230605");
        System.out.println(hourlyWeather);
    }
}