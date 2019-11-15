package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.component.BDComponent;
import cn.huihongcloud.controller.DeviceMaintenanceController;
import cn.huihongcloud.entity.*;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private BDComponent mBDComponent;



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

    /**
     * 获得注剂名称
     * @param worker
     * @return
     */
    @RequestMapping("/getInjectname")
    public Object getInjectName(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        System.out.println(user2.getUsername());
        return deviceBeetleMapper.getInjectName(user2.getAdcode());
    }

    @ApiOperation("上传维护信息")
    @PostMapping("/AddInjectData")
    public Object addMaintenanceData(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String deviceId,
                                     Double longitude,
                                     Double latitude,
                                     String altitude,
                                     String accuracy,
                                     Integer WoodStatus,
                                     Integer injectNum,
                                     String remarks,
                                     String workingContent,
                                     String injectName,
                                     Double chestDiameter,HttpServletResponse response) throws Exception {



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
        Device realDeviceId = deviceMapper.getDeviceByScanId(deviceId);

        User user = userMapper.getUserByUserName(username);
        User user1 = userMapper.getUserByUserName(user.getParent());
        System.out.println("USername");
        int maxBatchNum = 0;


        try {
            List<Device_Injection_maintanceEntity> maxBatch = deviceInjectionMaintanceEntityMapper.getMaxBatch(realDeviceId.getId());
            System.out.println("批次");
            System.out.println(maxBatch.get(0).getDeviceId());

            System.out.println(maxBatch.get(0).getBatch());
            maxBatchNum = maxBatch.get(0).getBatch();

        }catch (Exception e){
            maxBatchNum = 0;
        }



        System.out.println(user1.getUsername());
        inject_WoodStatus injectWoodStatus = deviceMapper.getInjectWoodStatus(String.valueOf(WoodStatus),null,1);
        inject_WorkContent workContent = deviceMapper.getInjectWorkContent(workingContent,null,1);
        InjectName injectName1 = deviceMapper.getInjectName(injectName,null,1);

        System.out.println(injectName1.getName());
        System.out.println(workContent.getName());
        System.out.println(injectWoodStatus.getName());

        deviceInjectionMaintanceEntity.setWorker(username);
        deviceInjectionMaintanceEntity.setDeviceId(Long.valueOf(realDeviceId.getId()));
        deviceInjectionMaintanceEntity.setLongitude(Double.valueOf(String.format("%.6f",longitude)));
        deviceInjectionMaintanceEntity.setLatitude(Double.valueOf(String.format("%.6f",latitude)));
        deviceInjectionMaintanceEntity.setAltitude(altitude);
        deviceInjectionMaintanceEntity.setDataPrecision(accuracy);
//        deviceInjectionMaintanceEntity.setWoodstatus(WoodStatus);
        deviceInjectionMaintanceEntity.setWoodStatusFront(injectWoodStatus.getName());
        deviceInjectionMaintanceEntity.setInjectionNum(injectNum);
        deviceInjectionMaintanceEntity.setRemarks(remarks);
//        deviceInjectionMaintanceEntity.setWorkContent(workingContent);
        deviceInjectionMaintanceEntity.setWorkContentFront(workContent.getName());
//        deviceInjectionMaintanceEntity.setInjectName(injectName);
        deviceInjectionMaintanceEntity.setInjectNameFront(injectName1.getName());
        deviceInjectionMaintanceEntity.setChestDiameter(chestDiameter);
        deviceInjectionMaintanceEntity.setUsername(user1.getUsername());
        deviceInjectionMaintanceEntity.setSerial(realDeviceId.getCustomSerial());
        deviceInjectionMaintanceEntity.setBatch(maxBatchNum + 1);
        deviceInjectionMaintanceEntity.setScanId(Long.valueOf(realDeviceId.getScanId()));


        BDInfo bdInfo = mBDComponent.parseLocation(latitude,longitude);

        deviceInjectionMaintanceEntity.setTown(bdInfo.getResult().getAddressComponent().getTown());


        System.out.println("CustomeSerial");
        
        System.out.println(realDeviceId.getCustomSerial());

        Date date= new Date(System.currentTimeMillis());

        deviceInjectionMaintanceEntity.setSubmitDate(date);
        deviceInjectionMaintanceEntity.setRegion(realDeviceId.getCustomTown());

        //修改了一些

        //随机数
        // deviceMaintenance.setNonceStr((int)(1+Math.random()*100000));

        if (image != null) {
            String imgId = deviceService.saveImg(image, deviceId, username);
            deviceInjectionMaintanceEntity.setPic(imgId);
            System.out.println("执行了这部");

        }
        deviceInjectionMaintanceEntityMapper.addMaintanceData(deviceInjectionMaintanceEntity);
        Device device1 = deviceService.getDeviceById(realDeviceId.getId());
        if(device1 == null || device1.getReceiveDate() == null) {

            Device device = new Device();
            device.setId(realDeviceId.getId());
            device.setLongitude(Double.valueOf(longitude));
            device.setLatitude(Double.valueOf(latitude));
            device.setAltitude(Double.valueOf(altitude));
            device.setReceiveDate(new Date());

            System.out.println(device.getReceiveDate());

            deviceService.updateDevice(device);
        }

        return Result.ok();
        //return null;
    }

    @RequestMapping("/InjectWorker")
    public List<Device_Injection_maintanceEntity> getInject(@RequestParam String id){
        return deviceBeetleMapper.InjectWorker(id);

    }

}
