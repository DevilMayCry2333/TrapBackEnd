package cn.huihongcloud.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.huihongcloud.component.BDComponent;
import cn.huihongcloud.component.JWTComponent;
import cn.huihongcloud.entity.bd.BDInfo;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.device.DeviceMaintenanceAbnormalData;
import cn.huihongcloud.entity.device.DeviceMaintenanceOutput;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.service.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by 钟晖宏 on 2018/9/24
 */
@RestController
public class DeviceMaintenanceController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceMaintenanceController.class);
    @Autowired
    private DeviceMaintenanceService deviceMaintenanceService;

    @Autowired
    private DeviceMaintenanceAbnormalService mDeviceMaintenanceAbnormalService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTComponent jwtComponent;

    @Autowired
    private DictService dictService;

    @Autowired
    private BDComponent mBDComponent;

    @Autowired
    DeviceMapper deviceMapper;

    @ApiOperation("上传维护信息")
    @PostMapping("/auth_api/maintenance")
        public Object addMaintenanceData(@RequestAttribute("username") String username,
                                         @RequestParam(required = false) MultipartFile image,
                                         @RequestParam(value = "username", required = false) String targetUsername,
                                         // targetUsername为手动伪造维护信息用的
                                         String deviceId,
                                         Double longitude,
                                         Double latitude,
                                         Double altitude,
                                         //随机数
                                    //     int noncestr,
                                         Integer num,
                                         Integer maleNum,
                                         Integer femaleNum,
                                         @RequestParam(required = false) Integer allLength,
                                         @RequestParam(required = false) Integer curRow,
                                         String drug,
                                         String remark,
                                         Integer otherNum,
                                         Integer otherType,
                                         int workingContent,HttpServletResponse response) throws Exception {

        User user = userService.getUserByUserName(username);

        Device realDeviceId = deviceMapper.getDeviceByScanId(deviceId);
//        logger.info("===开始记录数据===");
//        logger.info(username);
//        logger.info(realDeviceId.getId());
//        logger.info(String.valueOf(longitude));
//        logger.info(String.valueOf(latitude));
//        logger.info(String.valueOf(altitude));
//        logger.info(String.valueOf(num));
//        logger.info(String.valueOf(maleNum));
//        logger.info(String.valueOf(femaleNum));
//        logger.info(drug);
//        logger.info(remark);
//        logger.info(String.valueOf(otherNum));
//        logger.info(String.valueOf(otherType));
//        logger.info(String.valueOf(workingContent));

//        System.out.println("image" + image);

         Boolean relation=deviceService.judgeDeviceRelation(username,realDeviceId.getId());
        //if被我注释了 2019.10.1

//         if(!relation){
//             return Result.ok(deviceService.judgeDeviceRelation(username,deviceId));
//             //throw new Exception("提交失败");
//         }
        
//         if(altitude==null){
//
//             altitude=0D;
//         }

        //修改了一些
        
        DeviceMaintenance deviceMaintenance = new DeviceMaintenance();
        deviceMaintenance.setDeviceId(realDeviceId.getId());
        deviceMaintenance.setScanId(realDeviceId.getScanId());
        deviceMaintenance.setMaleNum(maleNum);
        deviceMaintenance.setFemaleNum(femaleNum);
        deviceMaintenance.setNum(num);
        deviceMaintenance.setLatitude(Double.valueOf(String.format("%.6f",latitude)));
        deviceMaintenance.setLongitude(Double.valueOf(String.format("%.6f",longitude)));
        deviceMaintenance.setAltitude(altitude);
        deviceMaintenance.setDate(new Date());
        deviceMaintenance.setRemark(remark);
        deviceMaintenance.setDrug(drug);
       // deviceMaintenance.setBatch(deviceMaintenanceService.getChangeTimesByDeviceId(deviceMaintenance.getDeviceId()) + 1);
        deviceMaintenance.setBatch(deviceMaintenanceService.getMaxBatchByDeviceid(realDeviceId.getId())+1);
        deviceMaintenance.setWorkingContent(workingContent);
        deviceMaintenance.setReported(false);
        // 其他天牛数量与类型
        deviceMaintenance.setOtherNum(otherNum);
        deviceMaintenance.setOtherType(otherType);
        deviceMaintenance.setCustomSerial(realDeviceId.getCustomSerial());
        deviceMaintenance.setCustomTown(realDeviceId.getCustomTown());
        deviceMaintenance.setCustomProject(realDeviceId.getCustomProject());
        deviceMaintenance.setAdcode(realDeviceId.getAdcode());

        deviceMaintenance.setManager(user.getParent());
        deviceMaintenance.setWorkContentFront(deviceMapper.getTrapWorkContentId(String.valueOf(deviceMaintenance.getWorkingContent())).getName());
        deviceMaintenance.setDrugFront(deviceMapper.getTrapInjectNameId(deviceMaintenance.getDrug()).getName());
        deviceMaintenance.setOtherBeetleFront(deviceMapper.getTrapBeetleInfoId(String.valueOf(deviceMaintenance.getOtherType())).getName());


        //随机数
       // deviceMaintenance.setNonceStr((int)(1+Math.random()*100000));

        if (image != null) {
            String imgId = deviceService.saveImg(image, realDeviceId.getId(), username);

            deviceMaintenance.setImageId(imgId);
//            System.out.println("执行了这部");

        }
        if (targetUsername != null) {
            deviceMaintenanceService.addMaintenanceData(targetUsername, deviceMaintenance);
        }
        else {
            Device device = deviceService.getDeviceById(realDeviceId.getId());
            // 如果存在设备判断是否偏离太远
            // 因为现在生成的二维码也就是设备，当接收日期不为空时才去判断是否偏离太远，否则认为是第一次收到设备信息了

            // 现在乡镇的信息改为用解析的, 如果不存在乡镇信息调用百度接口去解析
            if (device != null) {
                device.setLatitude(latitude);
                device.setLongitude(longitude);
                BDInfo bdInfo = mBDComponent.parseLocation(device.getLatitude(), device.getLongitude());
                device.setTown(bdInfo.getResult().getAddressComponent().getTown());
                deviceService.updateDevice(device);
                deviceMaintenance.setTown(bdInfo.getResult().getAddressComponent().getTown());
                deviceMaintenance.setArea(user.getArea());
                deviceMaintenance.setCity(user.getCity());
                deviceMaintenance.setProvince(user.getProvince());
                System.out.println(user.getArea());
            }

            if (device != null && device.getReceiveDate() != null) {
//                Double longitudeOrg = device.getLongitude();
//                Double latitudeOrg = device.getLatitude();
//                double threshold = Double.parseDouble(dictService.getValueByKey("abnormal_threshold"));
//                double offset = algorithm(longitudeOrg, latitudeOrg, longitude, latitude);
//                if (offset > threshold) {
//
//                    deviceMaintenance.setIsActive(0);
//                    deviceMaintenanceService.recordAbnormal(deviceMaintenance);
//                    //这里注释了一下 2019.10.1
////                    throw new Exception("人员未到场");
//                } else {
                    deviceMaintenanceService.addMaintenanceData(username, deviceMaintenance);
//                }
            }else {
                deviceMaintenanceService.addMaintenanceData(username, deviceMaintenance);
            }

        }
        //2018-12-20 改为只能更新
        Device device1 = deviceService.getDeviceById(realDeviceId.getId());
        if(device1 == null || device1.getReceiveDate() == null) {

            Device device = new Device();
            device.setId(realDeviceId.getId());
            device.setLongitude(longitude);
            device.setLatitude(latitude);
            device.setAltitude(altitude);
            device.setReceiveDate(new Date());
            deviceService.updateDevice(device);
        }


        // 增加or更新设备表
        /*
        Device obj = deviceService.packDevice(latitude, longitude, realDeviceId.getId());
        obj.setAltitude(altitude);
        obj.setReceiveDate(new Date());
        User user = userService.getUserByUserName(username);

        if (deviceService.isExist(obj.getId())) {
            deviceService.updateDevice(obj);
        } else {
            deviceService.addDevice(obj);
        }
        if (user.getRole() == 2) {
            deviceService.addDeviceRelation(realDeviceId.getId(), user.getUsername());
        }
        */
        JSONObject jsonObject = new JSONObject();
        if(curRow!=null && allLength!=null){

            if(curRow>=allLength-1){
                jsonObject.put("isComp",true);
            }else {
                jsonObject.put("isComp",false);
            }
        }

        return jsonObject;
//        return Result.ok();
        //return null;
    }

    public Integer addAbnormaltoMaintenanceData(String username,Date date,
                                      String deviceId,
                                      Double longitude,
                                      Double latitude,
                                      Double altitude,
                                      Integer num,
                                      Integer maleNum,
                                      Integer femaleNum,
                                      String drug,
                                      String remark,
                                      Integer otherNum,
                                      Integer otherType,
                                      Integer workingContent,
                                      Boolean isAbnormal, String imageid)throws Exception {

        if(altitude==null){

            altitude=0D;
        }
        Integer normal=0;
        DeviceMaintenance deviceMaintenance = new DeviceMaintenance();
        deviceMaintenance.setDeviceId(deviceId);
        deviceMaintenance.setMaleNum(maleNum);
        deviceMaintenance.setFemaleNum(femaleNum);
        deviceMaintenance.setNum(num);
        deviceMaintenance.setLatitude(latitude);
        deviceMaintenance.setLongitude(longitude);
        deviceMaintenance.setAltitude(altitude);
        deviceMaintenance.setDate(date);
        deviceMaintenance.setRemark(remark);
        deviceMaintenance.setDrug(drug);
       // deviceMaintenance.setBatch(deviceMaintenanceService.getChangeTimesByDeviceId(deviceMaintenance.getDeviceId()) + 1);
      deviceMaintenance.setBatch(deviceMaintenanceService.getMaxBatchByDeviceid(deviceId)+1);
        deviceMaintenance.setWorkingContent(workingContent);

        // 其他天牛数量与类型
        deviceMaintenance.setOtherNum(otherNum);
        deviceMaintenance.setOtherType(otherType);

        Device device1 = deviceService.getDeviceById(deviceId);
        if(device1 == null || device1.getReceiveDate() == null) {
            normal=1;
            Device device = new Device();
            device.setId(deviceId);
            device.setLongitude(longitude);
            device.setLatitude(latitude);
            device.setAltitude(altitude);
            device.setReceiveDate(date);
            deviceService.updateDevice(device);
        }
        if(imageid!=null){
            deviceMaintenance.setImageId(imageid);
        }


            Device device = deviceService.getDeviceById(deviceId);
            // 如果存在设备判断是否偏离太远
            // 因为现在生成的二维码也就是设备，当接收日期不为空时才去判断是否偏离太远，否则认为是第一次收到设备信息了

            // 现在乡镇的信息改为用解析的, 如果不存在乡镇信息调用百度接口去解析
            if (device != null && device.getTown() == null) {
                device.setLatitude(latitude);
                device.setLongitude(longitude);
                BDInfo bdInfo = mBDComponent.parseLocation(device.getLatitude(), device.getLongitude());
                device.setTown(bdInfo.getResult().getAddressComponent().getTown());
                deviceService.updateDevice(device);
            }

            if (device != null && device.getReceiveDate() != null) {
                Double longitudeOrg = device.getLongitude();
                Double latitudeOrg = device.getLatitude();
                double threshold = Double.parseDouble(dictService.getValueByKey("abnormal_threshold"));
                // double offset = Math.pow(longitudeOrg - longitude, 2) + Math.pow(latitudeOrg - latitude, 2);
                double offset = algorithm(longitudeOrg,latitudeOrg,longitude,latitude);
                if (offset > threshold) {
                    if(!isAbnormal) {
                        deviceMaintenance.setIsActive(0);
                        deviceMaintenanceService.recordAbnormal(deviceMaintenance);

                        throw new Exception();


                    }

                }else {
                    normal=1;
                    deviceMaintenanceService.addMaintenanceData(username, deviceMaintenance);

                }
            } else {

                deviceMaintenanceService.addMaintenanceData(username, deviceMaintenance);
            }



        //2018-12-20 改为只能更新



        // 增加or更新设备表
        /*
        Device obj = deviceService.packDevice(latitude, longitude, deviceId);
        obj.setAltitude(altitude);
        obj.setReceiveDate(new Date());
        User user = userService.getUserByUserName(username);

        if (deviceService.isExist(obj.getId())) {
            deviceService.updateDevice(obj);
        } else {
            deviceService.addDevice(obj);
        }
        if (user.getRole() == 2) {
            deviceService.addDeviceRelation(deviceId, user.getUsername());
        }
        */
        return normal;
    }

    public static double algorithm(double longitude1, double latitude1, double longitude2, double latitude2) {

             double Lat1 = rad(latitude1); // 纬度

             double Lat2 = rad(latitude2);

             double a = Lat1 - Lat2;//两点纬度之差


             double b = rad(longitude1) - rad(longitude2); //经度之差

             double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式

             s = s * 6378137.0;//弧长乘地球半径（半径为米）

             s = Math.round(s * 10000d) / 10000d;//精确距离的数值


             return s;

    }

    private static double rad(double d) {

            return d * Math.PI / 180.00; //角度转换成弧度

    }

    @GetMapping("/auth_api/maintenance/byDeviceId")
    public Object getMaintenanceDataByDeviceId(@RequestAttribute("username") String username,
                                               @RequestParam String deviceId,
                                               @RequestParam (required = false)String myusername,
                                               @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {


//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        User user=userService.getUserByUserName(username);
        Object maintenanceData = deviceMaintenanceService.getMaintenanceDataByDeviceId(user,user.getParent(),deviceId, startDate, endDate);

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);

        return pageWrapper;
    }
    @GetMapping("/auth_api/maintenance")
    public Object getMaintenanceData(@RequestAttribute("username") String username, int page, int limit,
                                     @RequestParam(required = false) String condition,
                                     @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
//        System.out.println(condition);
//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<DeviceMaintenance> maintenanceData = deviceMaintenanceService.getMaintenanceData(user, condition, startDate, endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }
    @GetMapping("/auth_api/maintenance2")
    public Object getMaintenanceData2(@RequestAttribute("username") String username, int page, int limit,
                                     @RequestParam(required = false) String condition,
                                     @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
//        System.out.println(condition);
//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<DeviceMaintenance> maintenanceData = deviceMaintenanceService.getMaintenanceData2(user, condition, startDate, endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }

    @GetMapping("/auth_api/maintenance1")
    public Object getMaintenanceData1(@RequestAttribute("username") String username, int page, int limit,
                                     @RequestParam(required = false) String condition,
                                      @RequestParam(required = false) String batch,@RequestParam(required = false) String town,
                                     @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        //System.out.println(startDate+"cc");
//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
            System.out.println(startDate+"dd");
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);



        List<DeviceMaintenance> maintenanceData = deviceMaintenanceService.getMaintenanceData1(user, condition, startDate, endDate,batch,town);
        for (DeviceMaintenance d:
             maintenanceData) {
            System.out.println(d.getImgId());
            System.out.println(d.getDeviceId());
            if(d.getImgId()!=null){
                d.setImageId(d.getImgId());
            }

        }
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
//        System.out.println("====page=====");
//        System.out.println(page);
//        System.out.println(pageObject.getTotal());
//        System.out.println(pageObject.getPages());
//        System.out.println("*********");

        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }
    @GetMapping("/auth_api/maintenance4")
    public Object getMaintenanceData4(@RequestAttribute("username") String username, int page, int limit,
                                      @RequestParam(required = false) String condition,
                                      @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        System.out.println(condition);
//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<DeviceMaintenance> maintenanceData = deviceMaintenanceService.getMaintenanceData4(user, condition,"","", startDate, endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }

    @GetMapping("/auth_api/maintenance/abnormal")
    public Object getMaintenanceAbnormalData(@RequestAttribute("username") String username, int page, int limit,
                                             @RequestParam(required = false) String condition,
                                             @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        System.out.println(condition);
//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<DeviceMaintenanceAbnormalData> maintenanceData = mDeviceMaintenanceAbnormalService.getMaintenanceData(user, condition, startDate, endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }

    @GetMapping("/auth_api/maintenance/abnormal/threshold")
    public Object getMaintenanceAbnormalThreshold(@RequestAttribute String username) {
        User user = userService.getUserByUserName(username);
        if (user.getRole() != 0) {
            return Result.ok(null);
        }
        String threshold = dictService.getValueByKey("abnormal_threshold");
        return Result.ok(threshold);
    }

    @PostMapping("/auth_api/maintenance/abnormal/threshold")
    public Object setMaintenanceAbnormalThreshold(@RequestAttribute String username, String threshold) {
        User user = userService.getUserByUserName(username);
        if (user.getRole() != 0) {
            return Result.ok("假的");
        }
        dictService.setValueByKey("abnormal_threshold", threshold);
        return Result.ok();
    }
    @PutMapping("/auth_api/maintenance/abnormal")
    public Object updateMaintenanceAbnormalData(@RequestAttribute("username") String username,
                                        @RequestBody DeviceMaintenanceAbnormalData deviceMaintenanceAbnormalData) {

        mDeviceMaintenanceAbnormalService.updateMaintenanceAbnormalData(deviceMaintenanceAbnormalData);
        return null;
    }
    @PutMapping("/auth_api/maintenance")
    public Object updateMaintenanceData(@RequestAttribute("username") String username,
                                        @RequestBody DeviceMaintenance deviceMaintenance) {
        System.out.println("===========================");
        System.out.println(deviceMaintenance);
        deviceMaintenanceService.updateMaintenanceData(deviceMaintenance);
        String deviceId=deviceMaintenance.getDeviceId();
        System.out.println(deviceMaintenance.getBatch());
        if(deviceMaintenance.getBatch()==1 && deviceMaintenance.getLongitude()!=null && deviceMaintenance.getLatitude()!=null ){
            Device device=deviceService.queryDeviceByDeviceid(deviceId);
            device.setAltitude(deviceMaintenance.getAltitude());
            device.setLatitude(deviceMaintenance.getLatitude());
            device.setTown(deviceMaintenance.getTown());
            device.setLongitude(deviceMaintenance.getLongitude());
            device.setReceiveDate(deviceMaintenance.getDate());
            deviceService.updateDevice(device);
        }
        return null;
    }

    @DeleteMapping("/auth_api/maintenance")
    public Object deleteMaintenanceData(@RequestAttribute("username") String username,
                                        int id,String deviceID) {
        deviceMaintenanceService.deleteByIdReally(id);
        User user = userService.getUserByUserName(username);
        List<DeviceMaintenance> dm=deviceMaintenanceService.getMaintenanceDataById(user,deviceID,null, null);

        if(dm.size()==0) {
            Device device = new Device();
            device.setId(deviceID);
            device.setLongitude(null);
            device.setLatitude(null);
            device.setAltitude(null);
            device.setReceiveDate(null);
            deviceService.updateDevice1(device);
        }
        return null;
    }
    
    @DeleteMapping("/auth_api/maintenance/deleteSmoe")
    public Object deleteSomeMaintenanceData(@RequestAttribute("username") String username,@RequestBody Map<String, Object> data) {
        System.out.println(data.size());
        List<Integer> list = (List<Integer>) data.get("list");
        //deviceMaintenanceService.report(list);
        for (Integer id: list) {


            DeviceMaintenance dm1=deviceMaintenanceService.getMaintenanceDataById2(id);
            String deviceID = dm1.getDeviceId();
            User user = userService.getUserByUserName(username);

            deviceMaintenanceService.deleteByIdReally(id);
            List<DeviceMaintenance> dms = deviceMaintenanceService.getMaintenanceDataById(user, deviceID, null, null);

            if (dms.size() == 0) {
                Device device = new Device();
                device.setId(deviceID);
                device.setLongitude(null);
                device.setLatitude(null);
                device.setAltitude(null);
                device.setReceiveDate(null);
                deviceService.updateDevice1(device);
            }
        }
       return Result.ok();
    }
//device_maintenance.male_num,
//        device_maintenance.female_num,
@DeleteMapping("/auth_api/maintenance/abnormal")
public Object deleteMaintenanceAbnormal(@RequestAttribute("username") String username,
                                    int id,String deviceID) {
    mDeviceMaintenanceAbnormalService.deleteById(id);

    return null;
}
    @GetMapping("/maintenance/export1")
    public void export1(HttpServletResponse response, String token,
                       @RequestParam(required = false) String condition,
                       @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) throws IOException {

        if(startDate.equals("null")){
            startDate=null;
        }
        if(endDate.equals("null")){
            endDate=null;
        }
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");
        String username = jwtComponent.verify(token);

        User user = userService.getUserByUserName(username);
        Workbook workbook = deviceMaintenanceService.exportExcel1(user, condition, startDate, endDate);
        workbook.write(response.getOutputStream());
    }

    @GetMapping("/maintenance/export")
    public void export(HttpServletResponse response, String token,
                       @RequestParam(required = false) String condition,
                       @RequestParam(required = false) String batch,@RequestParam(required = false) String town,
                       @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) throws IOException {
    // System.out.println(startDate+"aa");

        if(startDate.equals("null")){
            startDate=null;
        }
        if(endDate.equals("null")){
            endDate=null;
        }
        if(startDate!=null && startDate!="") {
            startDate = startDate + " 00:00:00";
          //  System.out.println(startDate+"bb");
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");
        String username = jwtComponent.verify(token);

        User user = userService.getUserByUserName(username);
        Workbook workbook = deviceMaintenanceService.exportExcel(user, condition, batch,town,startDate, endDate);
        workbook.write(response.getOutputStream());
    }

    @GetMapping("/maintenance/exportSome")
    public void someExport(HttpServletResponse response, String token,
                           @RequestParam(required = false) List<Integer> data) throws IOException {
        System.out.println(data.size());


        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");
        String username = jwtComponent.verify(token);

        User user = userService.getUserByUserName(username);

        List<DeviceMaintenance> list=new ArrayList<DeviceMaintenance>();//创建集合对象；
        List<Integer> ids = data;

        for(Integer id:ids){
            DeviceMaintenance dm=deviceMaintenanceService.getoneMaintenanceDataById(user,id,null,null);
            list.add(dm);//在集合里存入数据
        }

        //List<DeviceMaintenance> list = (List<DeviceMaintenance>) data.get("list");
        System.out.println(list);
        Workbook workbook = deviceMaintenanceService.someExportExcel(list);
        workbook.write(response.getOutputStream());
    }
    @ApiOperation("导入修改后的excel")
    @PostMapping("/maintenance/import1")
    public Object importExcel1( String token,@RequestParam("file") MultipartFile multipartFile) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        String username = jwtComponent.verify(token);

        User user = userService.getUserByUserName(username);


        List<DeviceMaintenance> deviceMaintenanceList = ExcelImportUtil
                .importExcel(multipartFile.getInputStream(), DeviceMaintenance.class, importParams);

        for (DeviceMaintenance dm:deviceMaintenanceList) {
            dm.setDeviceId(deviceMapper.querySingalDeviceId(dm.getScanId()).getId());
            System.out.println(dm.getOtherBeetleFront());
            System.out.println(dm.getDrugFront());
            System.out.println(dm.getWorkContentFront());

            dm.setOtherType(deviceMapper.getTrapBeetleInfo(String.valueOf(dm.getOtherBeetleFront())).getId());
            dm.setDrug(deviceMapper.getTrapInjectName(dm.getDrugFront()).getId().toString());
            dm.setWorkingContent(deviceMapper.getTrapWorkContent(dm.getWorkContentFront()).getFvalue());

        }
        //获取其他天 牛类型编号
        //ObjectMapper objectMapper = new ObjectMapper();
        //String s = objectMapper.writeValueAsString(deviceMaintenanceList);
        //System.out.println(s);
        //deviceMaintenanceService.addList(deviceMaintenanceList);
        deviceMaintenanceService.importExcel(user,deviceMaintenanceList);
        return "{\n" +
                "  \"code\": 0\n" +
                "  ,\"msg\": \"\"\n" +
                "}    ";
    }
    @ApiOperation("导入excel")
    @PostMapping("/maintenance/import")
    public Object importExcel(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        List<DeviceMaintenance> deviceMaintenanceList = ExcelImportUtil
                .importExcel(multipartFile.getInputStream(), DeviceMaintenance.class, new ImportParams());
        deviceMaintenanceService.addList(deviceMaintenanceList);
        return "{\n" +
                "  \"code\": 0\n" +
                "  ,\"msg\": \"\"\n" +
                "}    ";
    }

    @RequestMapping(value = "/auth_api/device_beetle_statistics_by_town", method = RequestMethod.GET)
    @ApiOperation("获取按乡的统计信息")
    public Object getStatisticsByTown(String adcode, String town, Boolean isChart
            , @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if (isChart) {
            return deviceMaintenanceService.getStatisticsByTown(adcode, town, isChart);
        }
        else {
            Page<Object> pageObject = PageHelper.startPage(page, limit);
            Object statisticsByTown = deviceMaintenanceService.getStatisticsByTown(adcode, town, isChart);
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setTotalPage(pageObject.getPages());
            pageWrapper.setTotalNum(pageObject.getTotal());
            pageWrapper.setCurrentPage(page);
            pageWrapper.setData(statisticsByTown);
            return pageWrapper;
        }
    }

    @RequestMapping(value = "/auth_api/device_beetle_statistics_by_area", method = RequestMethod.GET)
    @ApiOperation("获取按县的统计信息")
    public Object getStatisticsByArea(String adcode, Boolean isChart
            , @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if (isChart) {
            return deviceMaintenanceService.getStatisticsByArea(adcode, isChart);
        } else {
            Page<Object> pageObject = PageHelper.startPage(page, limit);
            Object statisticsByArea = deviceMaintenanceService.getStatisticsByArea(adcode, isChart);
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setTotalPage(pageObject.getPages());
            pageWrapper.setTotalNum(pageObject.getTotal());
            pageWrapper.setCurrentPage(page);
            pageWrapper.setData(statisticsByArea);
            return pageWrapper;
        }
    }

    @RequestMapping(value = "/auth_api/device_beetle_statistics_by_city", method = RequestMethod.GET)
    @ApiOperation("获取按市的统计信息")
    public Object getStatisticsByCity(String adcode, Boolean isChart
            , @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if (isChart) {
            return deviceMaintenanceService.getStatisticsLikeAdcode(adcode, isChart);
        } else {
            Page<Object> pageObject = PageHelper.startPage(page, limit);
            Object statisticsByTown = deviceMaintenanceService.getStatisticsLikeAdcode(adcode, isChart);
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setTotalPage(pageObject.getPages());
            pageWrapper.setTotalNum(pageObject.getTotal());
            pageWrapper.setCurrentPage(page);
            pageWrapper.setData(statisticsByTown);
            return pageWrapper;
        }
    }

    @RequestMapping(value = "/auth_api/device_beetle_statistics_by_pronvince", method = RequestMethod.GET)
    @ApiOperation("获取按省的统计信息")
    public Object getStatisticsByProvince(String adcode, Boolean isChart
            , @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if (isChart) {
            return deviceMaintenanceService.getStatisticsLikeAdcode(adcode, isChart);
        } else {
            Page<Object> pageObject = PageHelper.startPage(page, limit);
            Object statisticsByTown = deviceMaintenanceService.getStatisticsLikeAdcode(adcode, isChart);
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setTotalPage(pageObject.getPages());
            pageWrapper.setTotalNum(pageObject.getTotal());
            pageWrapper.setCurrentPage(page);
            pageWrapper.setData(statisticsByTown);
            return pageWrapper;
        }
    }

    @GetMapping("/scanned")
    public Object getMaintenanceDataById(String id, int page, int limit) {
        Page<Object> pageObject = PageHelper.startPage(page, limit);

        Object maintenanceData = deviceMaintenanceService.getMaintenanceDataByIdScan(id);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setData(maintenanceData);
        return pageWrapper;
    }

    @ApiOperation("上报维护信息")
    @PostMapping("/auth_api/maintenance/report")
    public Object reportMaintenanceData(@RequestBody Map<String, Object> data) {
        System.out.println(data.size());
        List<Integer> list = (List<Integer>) data.get("list");
        deviceMaintenanceService.report(list);
        return Result.ok();
    }
    @PostMapping("/auth_api/maintenance/abnormal/report")
    public Result reportMaintenanceAbnormalData(@RequestAttribute("username") String username,
                                               @RequestBody Map<String, Object> data)throws Exception {
        System.out.println(data.size());
        List<Integer> list = (List<Integer>) data.get("list");
        Boolean ab=true;
        if (list.size()<=0){
            return new Result.Failed();

        }
        for(Integer id:list) {
            DeviceMaintenance deviceMaintenance1=deviceMaintenanceService.getMaintenanceDataById1(id);
            String deviceId=deviceMaintenance1.getDeviceId();
            Double longitude=deviceMaintenance1.getLongitude();
            Double latitude=deviceMaintenance1.getLatitude();
            Double altitude=deviceMaintenance1.getAltitude();
            Integer num=deviceMaintenance1.getNum();
            Integer maleNum=deviceMaintenance1.getMaleNum();
            Integer femaleNum=deviceMaintenance1.getFemaleNum();
            String drug=deviceMaintenance1.getDrug();
            String remark=deviceMaintenance1.getRemark();

            Integer otherNum= (Integer) deviceMaintenance1.getOtherNum();
            Integer otherType= (Integer) deviceMaintenance1.getOtherType();

            Date date=deviceMaintenance1.getDate();
            String imageid=deviceMaintenance1.getImageId();
            int workingContent=deviceMaintenance1.getWorkingContent();
            String myusername=deviceService.getWorkerAssociatedWithDeviceIds(deviceId);
            if(addAbnormaltoMaintenanceData(myusername,date,deviceId,longitude,latitude,altitude,num,
                    maleNum,femaleNum,drug,remark,otherNum,otherType,workingContent,true,imageid)==1){

                mDeviceMaintenanceAbnormalService.deleteById(id);


            }else {
                ab=false;
            }

        }
        if(!ab){
            return new Result.Failed();
        }
        return new Result.Ok();

    }

}
