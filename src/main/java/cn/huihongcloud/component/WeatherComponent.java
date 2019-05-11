package cn.huihongcloud.component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/9/8
 */
@Component
public class WeatherComponent {

    @Bean("weatherData")
    public Map<String, String> weatherAreaData() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("weather.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map<String, String> map = objectMapper.readValue(inputStream, new TypeReference<Map<String, String>>(){});
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
