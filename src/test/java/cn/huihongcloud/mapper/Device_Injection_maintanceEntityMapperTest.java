package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Device_Injection_maintanceEntityMapperTest {

    @Autowired
    private Device_Injection_maintanceEntityMapper device_injection_maintanceEntityMapper;

    @Test
    public void selectByConditions() {
//        List<Device_Injection_maintanceEntity> list = device_injection_maintanceEntityMapper.selectByConditions("环翠区", -1, "", "", "");
        List<Device_Injection_maintanceEntity> list = device_injection_maintanceEntityMapper.selectByConditions("环翠区", 1, "01", "", "");
//        List<Device_Injection_maintanceEntity> list=device_injection_maintanceEntityMapper.selectByConditions("环翠区",1,"01","2019-09-02 00:00:00","2019-09-06 23:59:59");
//        assertTrue(list.size() > 0);
        System.out.println(list.size());
        list.forEach(v -> System.out.println(v.getDeviceId()));
    }

}
