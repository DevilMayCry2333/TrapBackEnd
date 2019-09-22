package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.device.Device;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 钟晖宏 on 2018/6/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceMapperTest {
    @Autowired
    private DeviceMapper deviceMapper;

    @Test
    public void testMapper() {
        assertNotNull(deviceMapper);
    }

    @Test
    public void getDeviceByLocation() {
        List<Device> list = deviceMapper.getDeviceByLocation("350121", "上街镇", "");
        assertTrue(list.size() > 0);
    }

    @Test
    @Transactional
    public void addDeviceRelation() {
        deviceMapper.addDeviceRelation("350681000000", "zhh_2007");
    }

    @Test
    @Transactional
    public void deleteDeviceRelation() {
        deviceMapper.deleteDeviceRelation("1001", "zhh_worker");
    }

    @Test
    public void getDeviceNotAssociatedWithWorker() {
        List<Device> devices = deviceMapper.getDeviceNotAssociatedWithWorker("test", "2");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for (Device device : devices) {
                System.out.println(objectMapper.writeValueAsString(device));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void updateDevice() {
        Device device = new Device();
        device.setId("350121107010");
        device.setReceiveDate(new Date());
        device.setLongitude(1231.0);
        device.setLatitude(1231.0);
        device.setAltitude(30.0);
        deviceMapper.updateDevice(device);
    }
}