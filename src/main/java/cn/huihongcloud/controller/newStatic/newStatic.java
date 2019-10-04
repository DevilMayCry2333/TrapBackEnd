package cn.huihongcloud.controller.newStatic;

import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.AnalysisMapper;
import cn.huihongcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statics")
public class newStatic {
    @Autowired
    UserService userService;
    @Autowired
    AnalysisMapper analysisMapper;
    @RequestMapping("/area")
    public Object getAreaStatic(@RequestParam String ProjectAdminName){
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());
        return analysisMapper.getAreaStatic(user1.getUsername());
    }
    @RequestMapping("/month")
    public Object getMonthStatic(@RequestParam String ProjectAdminName,@RequestParam String startM,@RequestParam String endM){
        User user = userService.getUserByUserName(ProjectAdminName);
        User user1 = userService.getUserByUserName(user.getParent());
        return analysisMapper.getMonthStatic(startM,endM,user1.getUsername());

    }

    //这里还没写完
//    @RequestMapping("/batch")
//    public Object getBatchStatic(@RequestParam String ProjectAdminName,@RequestParam String startM,@RequestParam String endM){
//        User user = userService.getUserByUserName(ProjectAdminName);
//        User user1 = userService.getUserByUserName(user.getParent());
//        return analysisMapper.getMonthStatic(startM,endM,user1.getUsername());
//
//    }

}
