package cn.huihongcloud.component;

import cn.huihongcloud.entity.region.Node;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/4/28.
 */
@Component
public class RegionComponent {

    @Bean("provinces")
    public String provinces() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("provinces.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Bean("provinces_list")
    public List<Node> provinces_list() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("provinces.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Node> list = objectMapper.readValue(inputStream, new TypeReference<List<Node>>(){});
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean("cities")
    public Map<String, List<Node>> cities() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cities.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            Map<String, List<Node>> nodeMap = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, List.class));
            Map<String, List<Node>> nodeMap = objectMapper.readValue(inputStream, new TypeReference<Map<String, List<Node>>>(){});

            return nodeMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean("areas")
    public Map<String, List<Node>> areas() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("areas.json");
            ObjectMapper objectMapper = new ObjectMapper();
//            Map<String, List<Node>> nodeMap = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, List.class));
            Map<String, List<Node>> nodeMap = objectMapper.readValue(inputStream, new TypeReference<Map<String, List<Node>>>(){});
            return nodeMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean("streets")
    public Map<String, List<Node>> streets() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("streets.json");
            ObjectMapper objectMapper = new ObjectMapper();
//            Map<String, List<Node>> nodeMap = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, List.class));
            Map<String, List<Node>> nodeMap = objectMapper.readValue(inputStream, new TypeReference<Map<String, List<Node>>>(){});
            return nodeMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
