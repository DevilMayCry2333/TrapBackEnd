package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceBeetleMapper;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class Inject {
    @Autowired
    DeviceBeetleMapper deviceBeetleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DeviceMapper deviceMapper;

    @RequestMapping("/getWoodStatus")
    public Object getWoodStatus(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        return deviceBeetleMapper.getInjectWoodStatus(user2.getAdcode());
    }

    @RequestMapping("/getWorkingContent")
    public Object getWorkContent(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        return deviceBeetleMapper.getInjectWorkContent(user2.getAdcode());

    }
}
