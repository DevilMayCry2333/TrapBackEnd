package cn.huihongcloud.controller.natural;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.huihongcloud.component.JWTComponent;
import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.Device_Track_MaintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.device.DeviceMaintenanceOutput;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_NaturalEnemies_maintanceEntityMapper;
import cn.huihongcloud.service.NaturalEnemyService;
import cn.huihongcloud.service.UserService;
import cn.huihongcloud.util.ImageDownUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/natural")
public class NaturalEnemy {
    @Autowired
    NaturalEnemyService naturalEnemyService;
    @Autowired
    UserService userService;
    @Autowired
    private JWTComponent jwtComponent;

    @Autowired
    Device_NaturalEnemies_maintanceEntityMapper deviceNaturalEnemiesMaintanceEntityMapper;


    JSONObject jsonObject = new JSONObject();
    @RequestMapping("/detail")
    public Object NaturalDetail(@RequestParam int page,@RequestParam int limit,@RequestParam String username){
        jsonObject.put("Res",true);
        System.out.println(page);
        System.out.println(limit);
        jsonObject.put("Data",naturalEnemyService.selectAll(username,page*limit-limit,limit));
        jsonObject.put("total",naturalEnemyService.countAll(username,null,null,null,null, null));
        jsonObject.put("current",page);
        return jsonObject;
    }

    @RequestMapping("/areaDetail")
    public Object DetailByArea(@RequestParam int page,@RequestParam int limit,@RequestParam String username){
        User user = userService.getUserByUserName(username);

        jsonObject.put("Data",naturalEnemyService.selectAllByArea(user.getAdcode(),page*limit-limit,limit));
        jsonObject.put("total",naturalEnemyService.countAllByArea(user.getAdcode()));
        jsonObject.put("current",page);
        jsonObject.put("Res",true);
        return jsonObject;
    }

    @GetMapping("/maintenance2")
    public Object getMaintenanceData2(@RequestAttribute("username") String username, int page, int limit,
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
        List<Device_NaturalEnemies_maintanceEntity> maintenanceData = naturalEnemyService.getMaintenanceData2(user, condition, startDate, endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }

    @GetMapping("/maintenance1")
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

        List<Device_NaturalEnemies_maintanceEntity> maintenanceData = naturalEnemyService.getMaintenanceData1(user, condition, startDate, endDate,batch,town);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }


    @RequestMapping("/getAreaMaintanceDetail")
    public Object getAreaMaintanceDetail(@RequestAttribute("username") String username, int page, int limit,
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
        List<Device_NaturalEnemies_maintanceEntity> maintenanceData = naturalEnemyService.getAreaMaintanceDetail(user, condition, startDate, endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }

    @RequestMapping("/maintenance/byDeviceId")
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
        Object maintenanceData = naturalEnemyService.getMaintenanceDataByDeviceId(user,myusername,deviceId, startDate, endDate);
        System.out.println(maintenanceData);

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);

        return pageWrapper;
    }

    @PostMapping("/maintenance/report")
    public Object reportMaintenanceData(@RequestBody Map<String, Object> data) {
        System.out.println(data.size());
        List<Integer> list = (List<Integer>) data.get("list");
        naturalEnemyService.report(list);
        return Result.ok();
    }

    @RequestMapping("/searchDetail")
    public Object searchDetail(@RequestParam int page,@RequestParam int limit,@RequestParam String username,@RequestParam(required = false) String startDate,@RequestParam(required = false) String endDate,@RequestParam(required = false) String colName,@RequestParam(required = false) String searchText,@RequestParam String adcode){


        User user = userService.getUserByUserName(username);

        if (!Objects.equals(startDate, "")) {
            startDate = startDate + " 00:00:00";
        }
        if (!Objects.equals(endDate, "")) {
            endDate = endDate + " 23:59:59";
        }

        jsonObject.put("Res",true);
        System.out.println(page);
        System.out.println(limit);
        int luanKaNum = 0;
        int releasNum = 0;
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<Device_NaturalEnemies_maintanceEntity> deviceNaturalEnemiesMaintanceEntities = null;


        if(user.getRole()==4){
            deviceNaturalEnemiesMaintanceEntities = naturalEnemyService.selectByDateAndColSearch(user.getParent(),startDate,endDate,colName,searchText,page*limit-limit,limit,adcode);
            List<Device_NaturalEnemies_maintanceEntity> allEntity = deviceNaturalEnemiesMaintanceEntityMapper.selectAllByDateAndColSearch(user.getParent(),startDate,endDate,colName,searchText,page*limit-limit,limit,adcode);

            for (Device_NaturalEnemies_maintanceEntity dataEntity: allEntity) {
                if(dataEntity.getPredatorstype().equals("卵卡")){
                    luanKaNum++;
                }
                releasNum += dataEntity.getReleaseNum();
            }

//            jsonObject.put("Data",deviceNaturalEnemiesMaintanceEntities);
//            jsonObject.put("DeviceNum",deviceNaturalEnemiesMaintanceEntityMapper.selectDevicesByDateAndColSearch(user.getParent(),startDate,endDate,colName,searchText,1,100000,adcode));
//            jsonObject.put("LuanKaNum",luanKaNum);
//            jsonObject.put("releaseNum",releasNum);
        }else if(user.getRole()<=3){
            deviceNaturalEnemiesMaintanceEntities = deviceNaturalEnemiesMaintanceEntityMapper.selectByDateAndColSearchAdcode(startDate,endDate,colName,searchText,page*limit-limit,limit,user.getAdcode());
//            jsonObject.put("Data",deviceNaturalEnemiesMaintanceEntityMapper.selectByDateAndColSearchAdcode(startDate,endDate,colName,searchText,page*limit-limit,limit,user.getAdcode()));
        }

//            jsonObject.put("total",naturalEnemyService.countAll(username,startDate,endDate,colName,searchText,adcode));
//        jsonObject.put("current",1);
//        System.out.println(jsonObject);

        deviceNaturalEnemiesMaintanceEntities.get(0).setLuanKaNumSum(String.valueOf(luanKaNum));
        deviceNaturalEnemiesMaintanceEntities.get(0).setReleaseNumSum(String.valueOf(releasNum));

        deviceNaturalEnemiesMaintanceEntities.get(0).setDeviceNum(
                deviceNaturalEnemiesMaintanceEntityMapper.selectDevicesByDateAndColSearch(
                        user.getParent(),startDate,endDate,colName,searchText,1,100000,adcode
                )
        );

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(deviceNaturalEnemiesMaintanceEntities);
        return Result.ok(pageWrapper);

//        return jsonObject;
    }

//    @RequestMapping("/allDetail")
//    public Object allDetail(@RequestParam int page,@RequestParam int limit,@RequestParam String adcode){
//        jsonObject.put("Res",true);
//        System.out.println(page);
//        System.out.println(limit);
//        jsonObject.put("Data",naturalEnemyService.selectAll(username,page*limit-limit,limit));
//        jsonObject.put("total",naturalEnemyService.countAll(username));
//        jsonObject.put("current",page);
//        return jsonObject;
//    }

    @RequestMapping(value = "device_list", method = RequestMethod.GET)
    @ApiOperation("获取设备列表")
    public PageWrapper getDevices(@RequestAttribute("username") String username, @RequestParam("page") int page,
                                  @RequestParam("limit") int limit,
                                  @RequestParam(value = "searchText", required = false) String searchText,
                                  @RequestParam(value = "workerName", required = false) String workerName) {
        System.out.println(workerName);
        User user = userService.getUserByUserName(username);
        List<Device> list = null;
//        Page<Object> pages = null;
//        PageWrapper pageWrapper = new PageWrapper();
        /*
        if (user.getRole() > 1 && user.getRole() != 3) {
            // 工人查询所管理的设备
            pages = PageHelper.startPage(page, limit);
            list = deviceService.getDeviceByMap(username);
        } else if (workerName == null){
            // 管理员未传递指定工人 查询其下属地区所有诱捕器信息
            pages = PageHelper.startPage(page, limit);
            list = deviceService.getDeviceByLocation(user.getAdcode(), user.getTown(), searchText.trim());
        } else {
            // 管理员传递工人信息
            // todo 限制跨区域查询

            list = deviceService.getDeviceByMap(workerName);
        }
        */
        /*
        县级用户看到所有下属的诱捕器
        管理员看到关联的
        工人看到关联的
         */
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        if (user.getRole() == 1) {
            list = naturalEnemyService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 2) {
            list = naturalEnemyService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 3) {
            list = naturalEnemyService.getDeviceByLocation(user.getAdcode(), null, null);
        }

        if (user.getRole() == 4) {
            list = naturalEnemyService.getDeviceByManager(username);
        }

        if (user.getRole() == 5) {
            list = naturalEnemyService.getDeviceByWorker(username);
        }
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(list);
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        return pageWrapper;
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response,
                            String token,
                            @RequestParam(required = false) String startDate,
                            @RequestParam(required = false) String endDate,
                            @RequestParam(required = false) String colName,
                            @RequestParam(required = false) String searchText,
                            @RequestParam String username,
                            @RequestParam String adcode
                            ) throws IOException {
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");

        User user = userService.getUserByUserName(username);


        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(colName);
        System.out.println(searchText);
        List<Device_NaturalEnemies_maintanceEntity> deviceNaturalEnemiesMaintanceEntities = null;

        if(user.getRole()==4){
            deviceNaturalEnemiesMaintanceEntities = naturalEnemyService.selectByDateAndColSearch(user.getParent(),startDate,endDate,colName,searchText,1*10-10,1*10,adcode);
        }else {
            deviceNaturalEnemiesMaintanceEntities = deviceNaturalEnemiesMaintanceEntityMapper.selectByDateAndColSearchAdcode(startDate,endDate,colName,searchText,1*10-10,1*10,user.getAdcode());
        }

//        for (Device_NaturalEnemies_maintanceEntity d:
//             deviceNaturalEnemiesMaintanceEntities) {
//            System.out.println(d.getArea());
//
//        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("天敌防治", "天敌防治"), Device_NaturalEnemies_maintanceEntity.class, deviceNaturalEnemiesMaintanceEntities);
        workbook.write(response.getOutputStream());

    }


    @RequestMapping("/exportImage")
    public void exportImage(HttpServletResponse response,
                            String token,
                            @RequestParam(required = false) String startDate,
                            @RequestParam(required = false) String endDate,
                            @RequestParam(required = false) String colName,
                            @RequestParam(required = false) String searchText,
                            @RequestParam String username,
                            @RequestParam String adcode
    ) throws IOException {
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");

        User user = userService.getUserByUserName(username);

        ImageDownUtil imageDownUtil = new ImageDownUtil();

        File file=new File("/var/www/html/img");//路径

        int code = imageDownUtil.deleteFile(file);

        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(colName);
        System.out.println(searchText);
        List<Device_NaturalEnemies_maintanceEntity> deviceNaturalEnemiesMaintanceEntities = null;

        if(user.getRole()==4){
            deviceNaturalEnemiesMaintanceEntities = naturalEnemyService.selectByDateAndColSearch(user.getParent(),startDate,endDate,colName,searchText,1*10-10,1*10,adcode);
        }else {
            deviceNaturalEnemiesMaintanceEntities = deviceNaturalEnemiesMaintanceEntityMapper.selectByDateAndColSearchAdcode(startDate,endDate,colName,searchText,1*10-10,1*10,user.getAdcode());
        }


        for (Device_NaturalEnemies_maintanceEntity d:deviceNaturalEnemiesMaintanceEntities) {
            try {
                String pic = d.getPic();
                imageDownUtil.moveFile("/root/img/" + d.getPic(), "/var/www/html/img" + "/" + d.getScanId() + d.getSerial() + d.getCustomTown() + "Ser1");
            }catch (Exception e){
            }

        }
        imageDownUtil.tarFile(user.getAdcode());
        response.sendRedirect("http://106.15.200.245/img" + user.getAdcode() + ".tar");

    }



    @RequestMapping("/importExcel")
    public Object importExcel(String token,@RequestParam("file") MultipartFile multipartFile) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        List<Device_NaturalEnemies_maintanceEntity> deviceMaintenanceList = ExcelImportUtil
                .importExcel(multipartFile.getInputStream(), Device_NaturalEnemies_maintanceEntity.class, importParams);
        for (Device_NaturalEnemies_maintanceEntity d:
             deviceMaintenanceList) {
            System.out.println("natural");
            System.out.println(d.getScanId());
            Device_NaturalEnemies_maintanceEntity tmp = deviceNaturalEnemiesMaintanceEntityMapper.selectById(String.valueOf(d.getScanId()));
            if(tmp!=null){
                deviceNaturalEnemiesMaintanceEntityMapper.updateRecordById(d);
            }else {
                deviceNaturalEnemiesMaintanceEntityMapper.insert(d);
            }
        }
        return "OK";
    }

    @RequestMapping("/deleteRecord")
    public Object deleteRecord(@RequestParam String id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Error",false);
        deviceNaturalEnemiesMaintanceEntityMapper.deleteRecord(Long.parseLong(id));
        return jsonObject;
    }

    @RequestMapping("/updateRec")
    public Object updateRec(@RequestBody Device_NaturalEnemies_maintanceEntity d){
        deviceNaturalEnemiesMaintanceEntityMapper.updateRecordByFront(d);
        return "OK";

    }





}
