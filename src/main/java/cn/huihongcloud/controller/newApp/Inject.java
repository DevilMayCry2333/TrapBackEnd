package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.controller.DeviceMaintenanceController;
import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.bd.BDInfo;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceBeetleMapper;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.mapper.Device_Injection_maintanceEntityMapper;
import cn.huihongcloud.mapper.UserMapper;
import cn.huihongcloud.service.DeviceService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/app")
public class Inject {
    private static final Logger logger = LoggerFactory.getLogger(Inject.class);

    @Autowired
    DeviceBeetleMapper deviceBeetleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    DeviceService deviceService;
    @Autowired
    Device_Injection_maintanceEntityMapper deviceInjectionMaintanceEntityMapper;


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

    @ApiOperation("上传维护信息")
    @PostMapping("/AddInjectData")
    public Object addMaintenanceData(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String deviceId,
                                     String longitude,
                                     String latitude,
                                     String altitude,
                                     String accuracy,
                                     Integer WoodStatus,
                                     Integer injectNum,
                                     String remarks,
                                     String workingContent, HttpServletResponse response) throws Exception {



        logger.info("===开始记录数据===");
        logger.info(username);
        logger.info(deviceId);
        logger.info(String.valueOf(longitude));
        logger.info(String.valueOf(latitude));
        logger.info(String.valueOf(altitude));
        logger.info(String.valueOf(accuracy));
        logger.info(String.valueOf(WoodStatus));
        logger.info(String.valueOf(injectNum));
        logger.info(remarks);
        logger.info(workingContent);

        System.out.println("image" + image);

        Device_Injection_maintanceEntity deviceInjectionMaintanceEntity = new Device_Injection_maintanceEntity();
        deviceInjectionMaintanceEntity.setWorker(username);
        deviceInjectionMaintanceEntity.setDeviceId(Long.valueOf(deviceId));
        deviceInjectionMaintanceEntity.setLongitude(longitude);
        deviceInjectionMaintanceEntity.setLatitude(latitude);
        deviceInjectionMaintanceEntity.setAltitude(altitude);
        deviceInjectionMaintanceEntity.setDataPrecision(accuracy);
        deviceInjectionMaintanceEntity.setWoodStatus(WoodStatus);
        deviceInjectionMaintanceEntity.setInjectionNum(injectNum);
        deviceInjectionMaintanceEntity.setRemarks(remarks);
        deviceInjectionMaintanceEntity.setWorkContent(workingContent);

        //修改了一些

        //随机数
        // deviceMaintenance.setNonceStr((int)(1+Math.random()*100000));

        if (image != null) {
            String imgId = deviceService.saveImg(image, deviceId, username);
            deviceInjectionMaintanceEntity.setPic(imgId);
            System.out.println("执行了这部");

        }
        deviceInjectionMaintanceEntityMapper.addMaintanceData(deviceInjectionMaintanceEntity);
        return Result.ok();
        //return null;
    }

}
