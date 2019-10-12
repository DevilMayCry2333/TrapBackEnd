package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceBeetleMapper;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    @RequestMapping("/Fuck")
    public Object Fuck(@RequestParam(required = false) String colName,
                                        @RequestParam int page,
                                        @RequestParam int limit,
                                        @RequestParam(required = false) String searchText,
                                        @RequestParam String username,
                        @RequestParam(required = false) String startDate,
                       @RequestParam(required = false) String endDate){
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        User user = userMapper.getUserByUserName(username);
        PageWrapper pageWrapper = new PageWrapper();
        List<DeviceMaintenance> deviceMaintenanceList = deviceBeetleMapper.getFuckFuck(colName,searchText,user.getAdcode(),startDate,endDate);
        pageWrapper.setData(deviceMaintenanceList);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;

    }



}
