package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.component.BDComponent;
import cn.huihongcloud.entity.Device_Medicine_MaintanceEntity;
import cn.huihongcloud.entity.bd.BDInfo;
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
import java.util.List;

@RestController
@RequestMapping("/app")
public class Medicine {
    private static final Logger logger = LoggerFactory.getLogger(Medicine.class);

    @Autowired
    DeviceBeetleMapper deviceBeetleMapper;

    @Autowired
    UserMapper userMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    DeviceService deviceService;
    @Autowired
    Device_Medicine_MaintanceEntityMapper deviceMedicineMaintanceEntityMapper;

    @Autowired
    private BDComponent mBDComponent;



    @RequestMapping("/getMedicinename")
    public Object getmedicineName(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        System.out.println(user2.getUsername());
        return deviceBeetleMapper.getMedicineName(user2.getAdcode());
    }

    @RequestMapping("/getMedicineWorkContent")
    public Object getWorkContent(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        return deviceBeetleMapper.getMedicinetWorkContent(user2.getAdcode());

    }

    @ApiOperation("上传维护信息")
    @PostMapping("/Addmedicine")
    public Object addMaintenanceData(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String deviceId,
                                     Double longitude,
                                     Double latitude,
                                     String altitude,
                                     String accuracy,
                                     String medicinenameValue,          //要和前端名称一致
                                     String workContentValue,
                                     Double medicinenumber,
                                     Double controlarea,
                                     String remarks,
                                     HttpServletResponse response) throws Exception {



//        logger.info("===开始记录数据===");
//        logger.info(username);
//        logger.info(deviceId);
//        logger.info(String.valueOf(longitude));
//        logger.info(String.valueOf(latitude));
//        logger.info(String.valueOf(altitude));
//        logger.info(String.valueOf(accuracy));
//        logger.info(String.valueOf(WoodStatus));   //
//        //
//        logger.info(String.valueOf(injectNum));   //
//        logger.info(remarks);
//        logger.info(workingContent);            //




        System.out.println("image" + image);

//        Device_Injection_maintanceEntity deviceInjectionMaintanceEntity = new Device_Injection_maintanceEntity();
        Device_Medicine_MaintanceEntity deviceMedicineMaintanceEntity = new Device_Medicine_MaintanceEntity();

        Device realDeviceId = deviceMapper.getDeviceByScanId(deviceId);

        User user = userMapper.getUserByUserName(username);
        User user1 = userMapper.getUserByUserName(user.getParent());
        System.out.println("USername");
        int maxBatchNum = 0;


        try {
            List<Device_Medicine_MaintanceEntity> maxBatch = deviceMedicineMaintanceEntityMapper.getMaxBatch(realDeviceId.getId());
            System.out.println("批次");
            System.out.println(maxBatch.get(0).getDeviceId());

            System.out.println(maxBatch.get(0).getBatch());
            maxBatchNum = maxBatch.get(0).getBatch();

        }catch (Exception e){
            maxBatchNum = 0;
        }



        System.out.println(user1.getUsername());

//        deviceInjectionMaintanceEntity.setWorker(username);
//        deviceInjectionMaintanceEntity.setDeviceId(Long.valueOf(realDeviceId.getId()));
//        deviceInjectionMaintanceEntity.setLongitude(longitude);
//        deviceInjectionMaintanceEntity.setLatitude(latitude);
//        deviceInjectionMaintanceEntity.setAltitude(altitude);
//        deviceInjectionMaintanceEntity.setDataPrecision(accuracy);
//        deviceInjectionMaintanceEntity.setWoodstatus(WoodStatus);
//        deviceInjectionMaintanceEntity.setInjectionNum(injectNum);
//        deviceInjectionMaintanceEntity.setRemarks(remarks);
//        deviceInjectionMaintanceEntity.setWorkContent(workingContent);
//        deviceInjectionMaintanceEntity.setUsername(user1.getUsername());
//        deviceInjectionMaintanceEntity.setSerial(realDeviceId.getCustomSerial());
//        deviceInjectionMaintanceEntity.setBatch(maxBatchNum + 1);
        deviceMedicineMaintanceEntity.setSubmitDate(new Date());
        deviceMedicineMaintanceEntity.setDeviceId(Long.valueOf(realDeviceId.getId()));
        deviceMedicineMaintanceEntity.setScanId(Long.valueOf(realDeviceId.getScanId()));
        deviceMedicineMaintanceEntity.setCustomTown(realDeviceId.getCustomTown());
        deviceMedicineMaintanceEntity.setLongitude(Double.valueOf(String.format("%.6f",longitude)));
        deviceMedicineMaintanceEntity.setLatitude(Double.valueOf(String.format("%.6f",latitude)));
        deviceMedicineMaintanceEntity.setAltitude(altitude);
        deviceMedicineMaintanceEntity.setDataPrecision(accuracy);
        deviceMedicineMaintanceEntity.setMedicineName(medicinenameValue);
        deviceMedicineMaintanceEntity.setWorkContent(workContentValue);
        deviceMedicineMaintanceEntity.setMedicineQua(String.valueOf(medicinenumber));
        deviceMedicineMaintanceEntity.setAreaFz(controlarea);
        deviceMedicineMaintanceEntity.setRemarks(remarks);
        deviceMedicineMaintanceEntity.setUsername(user1.getUsername());
        deviceMedicineMaintanceEntity.setBatch(maxBatchNum + 1);
        deviceMedicineMaintanceEntity.setSerial(realDeviceId.getCustomSerial());
        deviceMedicineMaintanceEntity.setReported(0);
        deviceMedicineMaintanceEntity.setWorker(username);

        BDInfo bdInfo = mBDComponent.parseLocation(latitude,longitude);
//setTown
        deviceMedicineMaintanceEntity.setTown(bdInfo.getResult().getAddressComponent().getTown());




        System.out.println("CustomeSerial");

        System.out.println(realDeviceId.getCustomSerial());

        Date date= new Date(System.currentTimeMillis());
        String pattern="yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf= new SimpleDateFormat(pattern);
        String datestr=sdf.format(date);// format  为格式化方法

        deviceMedicineMaintanceEntity.setSubmitDate(sdf.parse(datestr));
        deviceMedicineMaintanceEntity.setRegion(realDeviceId.getArea());

        //修改了一些

        //随机数
        // deviceMaintenance.setNonceStr((int)(1+Math.random()*100000));

        if (image != null) {
            String imgId = deviceService.saveImg(image, deviceId, username);
            deviceMedicineMaintanceEntity.setPic(imgId);
            System.out.println("执行了这部");

        }
        deviceMedicineMaintanceEntityMapper.addMaintanceData(deviceMedicineMaintanceEntity);
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

    @RequestMapping("/MedicineWorker")
    public List<Device_Medicine_MaintanceEntity> getMedicine(@RequestParam String scanId){
        return deviceBeetleMapper.MedicineWorker(scanId);

    }


}
