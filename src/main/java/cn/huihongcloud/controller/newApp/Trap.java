package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceBeetleMapper;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app")
public class Trap {
    @Autowired
    DeviceBeetleMapper deviceBeetleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @RequestMapping("/getBeetle")
    public Object getBeetle(@RequestParam String username){
        User user = userMapper.getUserByUserName(username);
        String adcode = user.getAdcode();
        return deviceBeetleMapper.getBeetleInfoByArea(adcode);
    }

    @RequestMapping("/getInject")
    public Object getInject(@RequestParam String username){
        User user = userMapper.getUserByUserName(username);
        return deviceBeetleMapper.getInjectTypeByArea(user.getAdcode());
    }

    @RequestMapping("/getWorkContent")
    public Object getWorkContent(@RequestParam String username){
        User user = userMapper.getUserByUserName(username);
        return deviceBeetleMapper.getWorkContentByArea(user.getAdcode());
    }

    @RequestMapping("/getMyDevice")
    public Object getMyDevice(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        String projectName = user2.getUsername();
        return deviceMapper.getDeviceByCustomProject(projectName);
    }

    @RequestMapping("/TrapWorker")
    public List<DeviceMaintenance> worker(@RequestParam String scanId){

        return deviceBeetleMapper.getTrapById(scanId);


    }



}
