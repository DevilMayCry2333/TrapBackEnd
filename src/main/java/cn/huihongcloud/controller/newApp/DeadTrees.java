package cn.huihongcloud.controller.newApp;

import cn.huihongcloud.component.BDComponent;
import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.Device_Track_MaintanceEntity;
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
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
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
    @Autowired
    private BDComponent mBDComponent;


    @RequestMapping("/getKillMethods")
    public Object getKillMethods(@RequestParam String worker){
        User user = userMapper.getUserByUserName(worker);
        User user1 = userMapper.getUserByUserName(user.getParent());
        User user2 = userMapper.getUserByUserName(user1.getParent());
        return deviceBeetleMapper.getKillMethods(user2.getAdcode());

    }

    @RequestMapping("/AddDeadtreePhoto")
    public String addDeadtreePhoto(@RequestAttribute("username") String username,
                                   @RequestParam(required = false) MultipartFile image,
                                   String deviceId,
                                   Double longitude,
                                   Double latitude,
                                   String altitude,
                                   String accuracy,
                                   String diameter,
                                   String height,
                                   String volume,
                                   @RequestParam(required = false) String pic1,
                                   @RequestParam(required = false) String pic2,
                                   @RequestParam(required = false) String pic3,
                                   String killMethodsValue,
                                   @RequestParam(required = false) String remarks,
                                   int current){
        User user = userMapper.getUserByUserName(username);
        User user1 = userMapper.getUserByUserName(user.getParent());

        Device realDevice = deviceMapper.getDeviceByScanId(deviceId);

        Device_DeadTrees_maintanceEntity deviceDeadTreesMaintanceEntity = new Device_DeadTrees_maintanceEntity();
        Device realDeviceId = deviceMapper.getDeviceByScanId(deviceId);

        deviceDeadTreesMaintanceEntity.setWorker(username);
        deviceDeadTreesMaintanceEntity.setDeviceId(Long.valueOf(realDeviceId.getId()));
        deviceDeadTreesMaintanceEntity.setScanId(Long.valueOf(deviceId));
        deviceDeadTreesMaintanceEntity.setLongitude(Double.valueOf(String.format("%.6f",longitude)));
        deviceDeadTreesMaintanceEntity.setLatitude(Double.valueOf(String.format("%.6f",latitude)));
        deviceDeadTreesMaintanceEntity.setAltitude(altitude);
        deviceDeadTreesMaintanceEntity.setAccuracy(accuracy);
        deviceDeadTreesMaintanceEntity.setWooddiameter(diameter);
        deviceDeadTreesMaintanceEntity.setWoodheight(height);
        deviceDeadTreesMaintanceEntity.setWoodvolume(volume);
        deviceDeadTreesMaintanceEntity.setKillmethod(killMethodsValue);
        deviceDeadTreesMaintanceEntity.setRemarks(remarks);
        deviceDeadTreesMaintanceEntity.setUsername(user1.getUsername());
        deviceDeadTreesMaintanceEntity.setCustomTown(realDeviceId.getCustomTown());

        Date date= new Date(System.currentTimeMillis());
        deviceDeadTreesMaintanceEntity.setSerial(realDeviceId.getCustomSerial());
        deviceDeadTreesMaintanceEntity.setSubmitDate(date);
        deviceDeadTreesMaintanceEntity.setReported(0);
        deviceDeadTreesMaintanceEntity.setProvince(realDeviceId.getProvince());
        deviceDeadTreesMaintanceEntity.setCity(realDeviceId.getCity());
        deviceDeadTreesMaintanceEntity.setArea(realDeviceId.getArea());
        deviceDeadTreesMaintanceEntity.setCustomProject(realDeviceId.getCustomProject());
        deviceDeadTreesMaintanceEntity.setPic(pic1);
        deviceDeadTreesMaintanceEntity.setPic2(pic2);
        deviceDeadTreesMaintanceEntity.setPic3(pic3);

        BDInfo bdInfo = mBDComponent.parseLocation(latitude,longitude);

        deviceDeadTreesMaintanceEntity.setTown(bdInfo.getResult().getAddressComponent().getTown());


        List<Device_DeadTrees_maintanceEntity> maxId = deviceDeadTreesMaintanceEntityMapper.getMaxBatch(realDeviceId.getId());
        int maxIdNum = 1;
        try {
            maxIdNum = Integer.parseInt(maxId.get(0).getBatch());
        }catch (Exception e){
            maxIdNum = 1;
        }

        deviceDeadTreesMaintanceEntity.setBatch(String.valueOf(maxIdNum));


        String imgId = null;

        if (image!=null) {
            imgId = deviceService.saveImg2(image, deviceId, username,current,deviceDeadTreesMaintanceEntity,4,user.getParent(),maxIdNum);
//            if(current==1) {
//                deviceDeadTreesMaintanceEntityMapper.addMaintance(deviceDeadTreesMaintanceEntity);
////                deviceDeadTreesMaintanceEntityMapper.updatePic(realDevice.getId(),"Pic",imgId,user.getParent(),maxBatch);
//            }else if(current==2){
//                deviceDeadTreesMaintanceEntityMapper.addMaintance2(deviceDeadTreesMaintanceEntity);
////                deviceDeadTreesMaintanceEntityMapper.updatePic(realDevice.getId(),"Pic2",imgId,user.getParent(),maxBatch);
//            }else if(current==3){
//                deviceDeadTreesMaintanceEntityMapper.addMaintance3(deviceDeadTreesMaintanceEntity);
////                deviceDeadTreesMaintanceEntityMapper.updatePic(realDevice.getId(),"Pic3",imgId,user.getParent(),maxBatch);
//
//            }
        }

        return imgId;

    }

    @RequestMapping("/addDeviceId")
    public Object addLineName(@RequestAttribute("username") String username,@RequestParam Long deviceId){


        User user = userMapper.getUserByUserName(username);
        Device_DeadTrees_maintanceEntity deviceDeadTreesMaintanceEntity = new Device_DeadTrees_maintanceEntity();
        deviceDeadTreesMaintanceEntity.setUsername(username);
        Device realDevice = deviceMapper.getDeviceByScanId(String.valueOf(deviceId));
        int maxBatch = 1;
        try {
            List<Device_DeadTrees_maintanceEntity> deviceDeadTreesMaintanceEntities = deviceDeadTreesMaintanceEntityMapper.getMaxBatch(realDevice.getId());
            maxBatch = Integer.parseInt(deviceDeadTreesMaintanceEntities.get(0).getBatch()) + 1;
        }catch (Exception e){

        }

        deviceDeadTreesMaintanceEntity.setDeviceId(Long.valueOf(realDevice.getId()));
        deviceDeadTreesMaintanceEntity.setBatch(String.valueOf(maxBatch));


        deviceDeadTreesMaintanceEntityMapper.addMaintance(deviceDeadTreesMaintanceEntity);
        return maxBatch;
    }


    @ApiOperation("图像base64上传维护信息")
    @PostMapping("/AddDeadtrees2")
    public Object addMaintenanceData2(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String deviceId,
                                     Double longitude,
                                     Double latitude,
                                     String altitude,
                                     String accuracy,
                                     String diameter,
                                     String height,
                                     String volume,
                                     @RequestParam(required = false) String pic1,
                                     @RequestParam(required = false) String pic2,
                                     @RequestParam(required = false) String pic3,
                                     String killMethodsValue,
                                     @RequestParam(required = false) String remarks,
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
        logger.info(pic1);
        logger.info(pic2);
        logger.info(pic3);

        if(username==null || deviceId==null || longitude==null || latitude==null|| altitude==null || accuracy==null || diameter==null || height==null || volume==null||killMethodsValue==null){
            response.setStatus(500);
            return response;
        }

        System.out.println("image" + image);
        User user = userMapper.getUserByUserName(username);
        User user1 = userMapper.getUserByUserName(user.getParent());
        System.out.println("USername");

        Device_DeadTrees_maintanceEntity deviceDeadTreesMaintanceEntity = new Device_DeadTrees_maintanceEntity();
        Device realDeviceId = deviceMapper.getDeviceByScanId(deviceId);

        deviceDeadTreesMaintanceEntity.setWorker(username);
        deviceDeadTreesMaintanceEntity.setDeviceId(Long.valueOf(realDeviceId.getId()));
        deviceDeadTreesMaintanceEntity.setScanId(Long.valueOf(realDeviceId.getScanId()));
        deviceDeadTreesMaintanceEntity.setLongitude(Double.valueOf(String.format("%.6f",longitude)));
        deviceDeadTreesMaintanceEntity.setLatitude(Double.valueOf(String.format("%.6f",latitude)));
        deviceDeadTreesMaintanceEntity.setAltitude(altitude);
        deviceDeadTreesMaintanceEntity.setAccuracy(accuracy);
        deviceDeadTreesMaintanceEntity.setWooddiameter(diameter);
        deviceDeadTreesMaintanceEntity.setWoodheight(height);
        deviceDeadTreesMaintanceEntity.setWoodvolume(volume);
        deviceDeadTreesMaintanceEntity.setKillmethod(killMethodsValue);
        deviceDeadTreesMaintanceEntity.setRemarks(remarks);
        deviceDeadTreesMaintanceEntity.setUsername(user1.getUsername());
        deviceDeadTreesMaintanceEntity.setCustomTown(realDeviceId.getCustomTown());

        Date date= new Date(System.currentTimeMillis());
        deviceDeadTreesMaintanceEntity.setSerial(realDeviceId.getCustomSerial());
        deviceDeadTreesMaintanceEntity.setSubmitDate(date);
        deviceDeadTreesMaintanceEntity.setReported(0);
        deviceDeadTreesMaintanceEntity.setProvince(realDeviceId.getProvince());
        deviceDeadTreesMaintanceEntity.setCity(realDeviceId.getCity());
        deviceDeadTreesMaintanceEntity.setArea(realDeviceId.getArea());
        deviceDeadTreesMaintanceEntity.setCustomProject(realDeviceId.getCustomProject());
        deviceDeadTreesMaintanceEntity.setPic(pic1);
        deviceDeadTreesMaintanceEntity.setPic2(pic2);
        deviceDeadTreesMaintanceEntity.setPic3(pic3);

        BDInfo bdInfo = mBDComponent.parseLocation(latitude,longitude);

        deviceDeadTreesMaintanceEntity.setTown(bdInfo.getResult().getAddressComponent().getTown());


        List<Device_DeadTrees_maintanceEntity> maxId = deviceDeadTreesMaintanceEntityMapper.getMaxBatch(realDeviceId.getId());
        int maxIdNum = 1;
        try {
            maxIdNum = Integer.parseInt(maxId.get(0).getBatch());
        }catch (Exception e){
            maxIdNum = 1;
        }

        deviceDeadTreesMaintanceEntity.setBatch(String.valueOf(maxIdNum));

        if(pic3!=null && pic3!=""){
            deviceDeadTreesMaintanceEntityMapper.addMaintance3(deviceDeadTreesMaintanceEntity);
        }
        if(pic2!=null && pic2!=""){
            deviceDeadTreesMaintanceEntityMapper.addMaintance2(deviceDeadTreesMaintanceEntity);
        }
        if(pic1!=null && pic1!=""){
            deviceDeadTreesMaintanceEntityMapper.addMaintance(deviceDeadTreesMaintanceEntity);
        }

//        deviceDeadTreesMaintanceEntityMapper.updateMaintance(deviceDeadTreesMaintanceEntity);

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

//        return Result.ok();
        return "OK";
    }

    @ApiOperation("上传维护信息")
    @PostMapping("/AddDeadtrees3")
    public Object addMaintenanceData3(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String deviceId,
                                     Double longitude,
                                     Double latitude,
                                     String altitude,
                                     String accuracy,
                                     String diameter,
                                     String height,
                                     String volume,
                                     String killMethodsValue,
                                     @RequestParam(required = false) String remarks,
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

        if(username==null || deviceId==null || longitude==null || latitude==null|| altitude==null || accuracy==null || diameter==null || height==null || volume==null||killMethodsValue==null){
            response.setStatus(500);
            return response;
        }

        System.out.println("image" + image);
        User user = userMapper.getUserByUserName(username);
        User user1 = userMapper.getUserByUserName(user.getParent());
        System.out.println("USername");

        Device_DeadTrees_maintanceEntity deviceDeadTreesMaintanceEntity = new Device_DeadTrees_maintanceEntity();
        Device realDeviceId = deviceMapper.getDeviceByScanId(deviceId);

        deviceDeadTreesMaintanceEntity.setWorker(username);
        deviceDeadTreesMaintanceEntity.setDeviceId(Long.valueOf(realDeviceId.getId()));
        deviceDeadTreesMaintanceEntity.setScanId(Long.valueOf(realDeviceId.getScanId()));
        deviceDeadTreesMaintanceEntity.setLongitude(Double.valueOf(String.format("%.6f",longitude)));
        deviceDeadTreesMaintanceEntity.setLatitude(Double.valueOf(String.format("%.6f",latitude)));
        deviceDeadTreesMaintanceEntity.setAltitude(altitude);
        deviceDeadTreesMaintanceEntity.setAccuracy(accuracy);
        deviceDeadTreesMaintanceEntity.setWooddiameter(diameter);
        deviceDeadTreesMaintanceEntity.setWoodheight(height);
        deviceDeadTreesMaintanceEntity.setWoodvolume(volume);
        deviceDeadTreesMaintanceEntity.setKillmethod(killMethodsValue);
        deviceDeadTreesMaintanceEntity.setRemarks(remarks);
        deviceDeadTreesMaintanceEntity.setUsername(user1.getUsername());
        deviceDeadTreesMaintanceEntity.setCustomTown(realDeviceId.getCustomTown());

        Date date= new Date(System.currentTimeMillis());
        deviceDeadTreesMaintanceEntity.setSerial(realDeviceId.getCustomSerial());
        deviceDeadTreesMaintanceEntity.setSubmitDate(date);
        deviceDeadTreesMaintanceEntity.setReported(0);
        deviceDeadTreesMaintanceEntity.setProvince(realDeviceId.getProvince());
        deviceDeadTreesMaintanceEntity.setCity(realDeviceId.getCity());
        deviceDeadTreesMaintanceEntity.setArea(realDeviceId.getArea());
        deviceDeadTreesMaintanceEntity.setCustomProject(realDeviceId.getCustomProject());


        BDInfo bdInfo = mBDComponent.parseLocation(latitude,longitude);

        deviceDeadTreesMaintanceEntity.setTown(bdInfo.getResult().getAddressComponent().getTown());


        List<Device_DeadTrees_maintanceEntity> maxId = deviceDeadTreesMaintanceEntityMapper.getMaxBatch(realDeviceId.getId());
        int maxIdNum = 1;
        try {
            maxIdNum = Integer.parseInt(maxId.get(0).getBatch());
        }catch (Exception e){
            maxIdNum = 1;
        }

        deviceDeadTreesMaintanceEntity.setBatch(String.valueOf(maxIdNum));


        //修改了一些

        //随机数
        // deviceMaintenance.setNonceStr((int)(1+Math.random()*100000));

//        if (image != null) {
//            String imgId = deviceService.saveImg(image, deviceId, username);
//            deviceDeadTreesMaintanceEntity.setPic(imgId);
//            System.out.println("执行了这部");
//
//        }


//        deviceDeadTreesMaintanceEntityMapper.updateMaintance(deviceDeadTreesMaintanceEntity);
//
//        Device device1 = deviceService.getDeviceById(realDeviceId.getId());
//        if(device1 == null || device1.getReceiveDate() == null) {
//
//            Device device = new Device();
//            device.setId(realDeviceId.getId());
//            device.setLongitude(Double.valueOf(longitude));
//            device.setLatitude(Double.valueOf(latitude));
//            device.setAltitude(Double.valueOf(altitude));
//            device.setReceiveDate(new Date());
//            deviceService.updateDevice(device);
//        }

//        return Result.ok();
        return "OK";
    }



    @ApiOperation("上传维护信息")
    @PostMapping("/AddDeadtrees")
    public Object addMaintenanceData(@RequestAttribute("username") String username,
                                     @RequestParam(required = false) MultipartFile image,
                                     @RequestParam(value = "username", required = false) String targetUsername,
                                     // targetUsername为手动伪造维护信息用的
                                     String deviceId,
                                     Double longitude,
                                     Double latitude,
                                     String altitude,
                                     String accuracy,
                                     String diameter,
                                     String height,
                                     String volume,
                                     String killMethodsValue,
                                     @RequestParam(required = false) String remarks,
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

        if(username==null || deviceId==null || longitude==null || latitude==null|| altitude==null || accuracy==null || diameter==null || height==null || volume==null||killMethodsValue==null){
            response.setStatus(500);
            return response;
        }

        System.out.println("image" + image);
        User user = userMapper.getUserByUserName(username);
        User user1 = userMapper.getUserByUserName(user.getParent());
        System.out.println("USername");

        Device_DeadTrees_maintanceEntity deviceDeadTreesMaintanceEntity = new Device_DeadTrees_maintanceEntity();
        Device realDeviceId = deviceMapper.getDeviceByScanId(deviceId);

        deviceDeadTreesMaintanceEntity.setWorker(username);
        deviceDeadTreesMaintanceEntity.setDeviceId(Long.valueOf(realDeviceId.getId()));
        deviceDeadTreesMaintanceEntity.setScanId(Long.valueOf(realDeviceId.getScanId()));
        deviceDeadTreesMaintanceEntity.setLongitude(Double.valueOf(String.format("%.6f",longitude)));
        deviceDeadTreesMaintanceEntity.setLatitude(Double.valueOf(String.format("%.6f",latitude)));
        deviceDeadTreesMaintanceEntity.setAltitude(altitude);
        deviceDeadTreesMaintanceEntity.setAccuracy(accuracy);
        deviceDeadTreesMaintanceEntity.setWooddiameter(diameter);
        deviceDeadTreesMaintanceEntity.setWoodheight(height);
        deviceDeadTreesMaintanceEntity.setWoodvolume(volume);
        deviceDeadTreesMaintanceEntity.setKillmethod(killMethodsValue);
        deviceDeadTreesMaintanceEntity.setRemarks(remarks);
        deviceDeadTreesMaintanceEntity.setUsername(user1.getUsername());
        deviceDeadTreesMaintanceEntity.setCustomTown(realDeviceId.getCustomTown());

        Date date= new Date(System.currentTimeMillis());
        deviceDeadTreesMaintanceEntity.setSerial(realDeviceId.getCustomSerial());
        deviceDeadTreesMaintanceEntity.setSubmitDate(date);
        deviceDeadTreesMaintanceEntity.setReported(0);
        deviceDeadTreesMaintanceEntity.setProvince(realDeviceId.getProvince());
        deviceDeadTreesMaintanceEntity.setCity(realDeviceId.getCity());
        deviceDeadTreesMaintanceEntity.setArea(realDeviceId.getArea());
        deviceDeadTreesMaintanceEntity.setCustomProject(realDeviceId.getCustomProject());


        BDInfo bdInfo = mBDComponent.parseLocation(latitude,longitude);

        deviceDeadTreesMaintanceEntity.setTown(bdInfo.getResult().getAddressComponent().getTown());


        List<Device_DeadTrees_maintanceEntity> maxId = deviceDeadTreesMaintanceEntityMapper.getMaxBatch(realDeviceId.getId());
        int maxIdNum = 1;
        try {
            maxIdNum = Integer.parseInt(maxId.get(0).getBatch());
        }catch (Exception e){
            maxIdNum = 1;
        }

        deviceDeadTreesMaintanceEntity.setBatch(String.valueOf(maxIdNum));


        //修改了一些

        //随机数
        // deviceMaintenance.setNonceStr((int)(1+Math.random()*100000));

//        if (image != null) {
//            String imgId = deviceService.saveImg(image, deviceId, username);
//            deviceDeadTreesMaintanceEntity.setPic(imgId);
//            System.out.println("执行了这部");
//
//        }


        deviceDeadTreesMaintanceEntityMapper.updateMaintance(deviceDeadTreesMaintanceEntity);

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

    @RequestMapping("/DeadWorker")
    public List<Device_DeadTrees_maintanceEntity> deadWorker(@RequestParam String scanId){
        return deviceBeetleMapper.DeadWorker(scanId);

    }

    @RequestMapping("/DeleteById")
    public Object deleteById(@RequestParam String id){
        return deviceDeadTreesMaintanceEntityMapper.deleteById(id);
    }




}
