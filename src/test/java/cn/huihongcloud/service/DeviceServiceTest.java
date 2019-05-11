package cn.huihongcloud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 钟晖宏 on 2018/8/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    @Test
    public void getImgPath() {
        System.out.println(deviceService.getImgPath("../8a4e4333-b9eb-49fb-ab84-9ed294d33fa9-fef8f2d8caac8fb8e1d09439431b578e15309f45c9887fbeef90cc55a2f46ba2.jpg"));
    }

    @Test
    public void getDeviceCountByUsername() {
        List<Object> ret = deviceService.getDeviceCountByUser("root");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(ret));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}