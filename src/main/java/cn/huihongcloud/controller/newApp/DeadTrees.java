package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.*;
import cn.huihongcloud.service.DeviceService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/app")
public class DeadTrees {
    private static final Logger logger = LoggerFactory.getLogger(DeadTrees.class);

    @Autowired
    DeviceBeetleMapper deviceBeetleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    DeviceService deviceService;
    @Autowired
    Device_DeadTrees_maintanceEntityMapper deviceDeadTreesMaintanceEntityMapper;



    @RequestMapping("/getKillMethods")
    public Object getKillMethods(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        return deviceBeetleMapper.getKillMethods(user2.getAdcode());

    }

    @ApiOperation("上传维护信息")
    @PostMapping("/AddDeadtrees")
    public Object addMaintenanceData(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String deviceId,
                                     String longitude,
                                     String latitude,
                                     String altitude,
                                     String accuracy,
                                     String diameter,
                                     String height,
                                     String volume,
                                     String killMethodsValue,
                                     String remarks,
                                     HttpServletResponse response) throws Exception {



        logger.info("===开始记录数据===");
        logger.info(username);
        logger.info(deviceId);
        logger.info(String.valueOf(longitude));
        logger.info(String.valueOf(latitude));
        logger.info(String.valueOf(altitude));
        logger.info(String.valueOf(accuracy));
        logger.info(String.valueOf(diameter));
        logger.info(String.valueOf(height));
        logger.info(volume);
        logger.info(killMethodsValue);
        logger.info(remarks);

        System.out.println("image" + image);

        Device_DeadTrees_maintanceEntity deviceDeadTreesMaintanceEntity = new Device_DeadTrees_maintanceEntity();
        deviceDeadTreesMaintanceEntity.setWorker(username);
        deviceDeadTreesMaintanceEntity.setDeviceId(Long.valueOf(deviceId));
        deviceDeadTreesMaintanceEntity.setLongitude(longitude);
        deviceDeadTreesMaintanceEntity.setLatitude(latitude);
        deviceDeadTreesMaintanceEntity.setAltitude(altitude);
        deviceDeadTreesMaintanceEntity.setAccuracy(accuracy);
        deviceDeadTreesMaintanceEntity.setWooddiameter(diameter);
        deviceDeadTreesMaintanceEntity.setWoodheight(height);
        deviceDeadTreesMaintanceEntity.setWoodvolume(volume);
        deviceDeadTreesMaintanceEntity.setKillmethod(killMethodsValue);
        deviceDeadTreesMaintanceEntity.setRemarks(remarks);



        //修改了一些

        //随机数
        // deviceMaintenance.setNonceStr((int)(1+Math.random()*100000));

        if (image != null) {
            String imgId = deviceService.saveImg(image, deviceId, username);
            deviceDeadTreesMaintanceEntity.setPic(imgId);
            System.out.println("执行了这部");

        }
        deviceDeadTreesMaintanceEntityMapper.addMaintance(deviceDeadTreesMaintanceEntity);
        return Result.ok();
        //return null;
    }


}
