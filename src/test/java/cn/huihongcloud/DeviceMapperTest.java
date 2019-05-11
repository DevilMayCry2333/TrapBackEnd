package cn.huihongcloud;

import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.mapper.DeviceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 钟晖宏 on 2018/5/31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DeviceMapperTest {

    @Autowired
    private DeviceMapper deviceMapper;

    @Test
    public void testIsExist() {
        System.out.println(deviceMapper.isExist("1"));
    }

    @Test
    @Transactional
    public void testInsert() {
        Device device = new Device();
        device.setId("2");
        deviceMapper.addDevice(device);

    }

    @Test
    public void update() {
        Device device = new Device();
        device.setId("1");
        device.setProvince("福建省");
        deviceMapper.updateDevice(device);
    }
}
