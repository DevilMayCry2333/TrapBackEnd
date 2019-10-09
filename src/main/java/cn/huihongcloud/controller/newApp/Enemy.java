package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/app")
public class Enemy {
    private static final Logger logger = LoggerFactory.getLogger(Enemy.class);

    @Autowired
    DeviceBeetleMapper deviceBeetleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    DeviceService deviceService;
    @Autowired
    Device_NaturalEnemies_maintanceEntityMapper deviceNaturalEnemiesMaintanceEntityMapper;

    @RequestMapping("/getEnemyType")
    public Object getEnemyType(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        return deviceBeetleMapper.getEnemyType(user2.getAdcode());
    }

    @ApiOperation("上传维护信息")
    @PostMapping("/AddEnemy")
    public Object addMaintenanceData(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String deviceId,
                                     String longitude,
                                     String latitude,
                                     String altitude,
                                     String accuracy,
                                     String predatorsTypeValue,
                                     Integer releaseNum,
                                     String remarks,
                                     HttpServletResponse response) throws Exception {



        logger.info("===开始记录数据===");
        logger.info(username);
        logger.info(deviceId);
        logger.info(String.valueOf(longitude));
        logger.info(String.valueOf(latitude));
        logger.info(String.valueOf(altitude));
        logger.info(String.valueOf(accuracy));
        logger.info(String.valueOf(predatorsTypeValue));
        logger.info(String.valueOf(releaseNum));
        logger.info(remarks);

        System.out.println("image" + image);
        User user = userMapper.getUserByUserName(username);
        User user1 = userMapper.getUserByUserName(user.getParent());
        System.out.println("USername");




        Device_NaturalEnemies_maintanceEntity deviceNaturalEnemiesMaintanceEntity = new Device_NaturalEnemies_maintanceEntity();
        Device realDeviceId = deviceMapper.getDeviceByScanId(deviceId);

        Device_NaturalEnemies_maintanceEntity maxBatch = deviceNaturalEnemiesMaintanceEntityMapper.getMaxBatch(realDeviceId.getId());

        int maxBatchNum = 0;
        try {
            System.out.println(maxBatch.getBatch());
            maxBatchNum = maxBatch.getBatch();

        }catch (Exception e){
            maxBatchNum = 0;
        }

        deviceNaturalEnemiesMaintanceEntity.setWorker(username);
        deviceNaturalEnemiesMaintanceEntity.setDeviceId(Long.valueOf(realDeviceId.getId()));
        deviceNaturalEnemiesMaintanceEntity.setLongitude(longitude);
        deviceNaturalEnemiesMaintanceEntity.setLatitude(latitude);
        deviceNaturalEnemiesMaintanceEntity.setAltitude(altitude);
        deviceNaturalEnemiesMaintanceEntity.setPrecision(accuracy);
        deviceNaturalEnemiesMaintanceEntity.setPredatorstype(predatorsTypeValue);
        deviceNaturalEnemiesMaintanceEntity.setReleaseNum(releaseNum);
        deviceNaturalEnemiesMaintanceEntity.setRemarks(remarks);
        deviceNaturalEnemiesMaintanceEntity.setUsername(user1.getUsername());
        deviceNaturalEnemiesMaintanceEntity.setReported(0);

        Date date= new Date(System.currentTimeMillis());
        String pattern="yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf= new SimpleDateFormat(pattern);
        String datestr=sdf.format(date);// format  为格式化方法
        deviceNaturalEnemiesMaintanceEntity.setSerial(realDeviceId.getCustomSerial());
        deviceNaturalEnemiesMaintanceEntity.setSubmitDate(datestr);
        deviceNaturalEnemiesMaintanceEntity.setRegion(realDeviceId.getArea());
        deviceNaturalEnemiesMaintanceEntity.setBatch(maxBatchNum + 1);



        //修改了一些

        //随机数
        // deviceMaintenance.setNonceStr((int)(1+Math.random()*100000));

        if (image != null) {
            String imgId = deviceService.saveImg(image, deviceId, username);
            deviceNaturalEnemiesMaintanceEntity.setPic(imgId);
            System.out.println("执行了这部");

        }
        deviceNaturalEnemiesMaintanceEntityMapper.addMaintance(deviceNaturalEnemiesMaintanceEntity);
        Device device1 = deviceService.getDeviceById(realDeviceId.getId());
        if(device1 == null || device1.getReceiveDate() == null) {

            Device device = new Device();
            device.setId(realDeviceId.getId());
            device.setLongitude(Double.valueOf(longitude));
            device.setLatitude(Double.valueOf(latitude));
            device.setAltitude(Double.valueOf(altitude));
            device.setReceiveDate(new Date());
            deviceService.updateDevice(device);
        }

        return Result.ok();
        //return null;
    }

}
