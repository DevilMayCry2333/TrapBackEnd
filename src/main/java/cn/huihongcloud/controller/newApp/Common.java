package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class Common {
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/bindId")
    public Object BindId(@RequestParam String scanId,@RequestParam String serial,@RequestParam String username){
        System.out.println(scanId);
        System.out.println(serial);
        User user = userMapper.getUserByUserName(username);
        Device toBeUpdated = deviceMapper.getDeviceBySerial(serial,user.getParent());
        return deviceMapper.updateScanId(toBeUpdated.getId(),scanId,user.getParent());
    }

    @RequestMapping("/queryDeviceId")
    public Object queryDeviceId(@RequestParam String scanId){
        return deviceMapper.queryDeviceId(scanId);
    }
}
