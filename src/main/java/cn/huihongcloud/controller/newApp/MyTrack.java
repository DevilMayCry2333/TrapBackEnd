package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.Device_Track_MaintanceEntity;
import cn.huihongcloud.entity.common.Result;
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
public class MyTrack {
    private static final Logger logger = LoggerFactory.getLogger(MyTrack.class);

    @Autowired
    DeviceBeetleMapper deviceBeetleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    DeviceService deviceService;
    @Autowired
    Device_Track_MaintanceEntityMapper deviceTrackMaintanceEntityMapper;

    @ApiOperation("上传维护信息")
    @PostMapping("/AddTrack")
    public Object addMaintenanceData(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String longtitudeData,
                                     String latitudeData,
                                     String altitudeData,
                                     String lineName,
                                     String workContent,
                                     String lateIntravl,
                                     String remarks,
                                     int current,
                                     HttpServletResponse response) throws Exception {

        logger.info("===开始记录数据===");
        logger.info(username);
        logger.info(String.valueOf(longtitudeData));
        logger.info(String.valueOf(latitudeData));
        logger.info(String.valueOf(altitudeData));
        logger.info(String.valueOf(lineName));
        logger.info(String.valueOf(workContent));
        logger.info(lateIntravl);
        logger.info(remarks);

        System.out.println("image" + image);
        Device_Track_MaintanceEntity deviceTrackMaintanceEntity = new Device_Track_MaintanceEntity();
        deviceTrackMaintanceEntity.setLongtitudeCollect(longtitudeData);
        deviceTrackMaintanceEntity.setWorker(username);
        deviceTrackMaintanceEntity.setAltitudeCollect(altitudeData);
        deviceTrackMaintanceEntity.setLatitudeCollect(latitudeData);
        deviceTrackMaintanceEntity.setLinename(lineName);
        deviceTrackMaintanceEntity.setWorkingContent(workContent);
        deviceTrackMaintanceEntity.setRemarks(remarks);

        //修改了一些

        //随机数
        // deviceMaintenance.setNonceStr((int)(1+Math.random()*100000));

        if (image!=null) {
            String imgId = deviceService.saveImg(image, lineName, username);
            deviceTrackMaintanceEntity.setPic1(imgId);
            System.out.println("执行了这部");

        }

        deviceTrackMaintanceEntityMapper.addMaintance(deviceTrackMaintanceEntity);
        return Result.ok();
        //return null;
    }
}
