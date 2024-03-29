package cn.huihongcloud.controller.injection;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.huihongcloud.component.BDComponent;
import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.bd.BDInfo;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.summary.InjectionSummary;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.mapper.Device_Injection_maintanceEntityMapper;
import cn.huihongcloud.service.DryInjectionService;
import cn.huihongcloud.service.UserService;
import cn.huihongcloud.util.DistUtil;
import cn.huihongcloud.util.ImageDownUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/dryWatch")
public class DryInjectionWatch {
    @Autowired
    DryInjectionService dryInjectionService;
    @Autowired
    UserService userService;
    @Autowired
    private Device_Injection_maintanceEntityMapper deviceInjectionMaintanceEntityMapper;
    JSONObject jsonObject = new JSONObject();

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    DistUtil distUtil;

    @Autowired
    private BDComponent mBDComponent;

    @Value("${com.youkaiyu.batchImg}")
    private String batchImgUrl;


    @RequestMapping("/dataDetail")
    public Object getDataDetail(@RequestAttribute("username") String username,
                             @RequestParam int page,
                             @RequestParam int limit,
                             @RequestParam Integer optionIndex,
                             @RequestParam(required = false) String searchText,
                             @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {

        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);





        if (!Objects.equals(startDate, "")) {
            startDate = startDate + " 00:00:00";
        }
        if (!Objects.equals(endDate, "")) {
            endDate = endDate + " 23:59:59";
        }
        List<Device_Injection_maintanceEntity> deviceInjectionMaintanceEntities = null;

        if(user.getRole()==4){
            deviceInjectionMaintanceEntities = dryInjectionService.getDryInjectionDetail(user.getParent(), optionIndex, searchText, startDate, endDate);
        }else if(user.getRole()<=3){
            deviceInjectionMaintanceEntities = deviceInjectionMaintanceEntityMapper.selectByConditionsAdcode(user.getAdcode(), optionIndex, searchText, startDate, endDate);
        }


        for (Device_Injection_maintanceEntity d:
             deviceInjectionMaintanceEntities) {




        }

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(deviceInjectionMaintanceEntities);
        return Result.ok(pageWrapper);
    }

    @RequestMapping("/area")
    public Object getDeviceSummaryByArea(String adcode, int page, int limit,
                                         @RequestParam(required = false) String startDate,
                                         @RequestParam(required = false) String endDate) {
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        if (startDate != "" && startDate != null) {
            startDate = startDate + " 00:00:00";
        }
        if (endDate != "" && endDate != null) {
            endDate = endDate + " 23:59:59";
        }
        List<InjectionSummary> summaryEntities = dryInjectionService.queryDeviceSummaryByArea(adcode, startDate, endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
    }

    @RequestMapping("/getAreaMaintanceDetail")
    public Object getMaintenanceData2(@RequestAttribute("username") String username, int page, int limit,
                                      @RequestParam(required = false) String condition,
                                      @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {

//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if (startDate != "" && startDate != null) {
            startDate = startDate + " 00:00:00";
        }
        if (endDate != "" && endDate != null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<Device_Injection_maintanceEntity> maintenanceData = dryInjectionService.getAreaMaintanceDetail(user, condition, startDate, endDate);
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
                                               @RequestParam(required = false) String myusername,
                                               @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {


//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if (startDate != "" && startDate != null) {
            startDate = startDate + " 00:00:00";
        }
        if (endDate != "" && endDate != null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Object maintenanceData = dryInjectionService.getMaintenanceDataByDeviceId(user, myusername, deviceId, startDate, endDate);


        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);

        return pageWrapper;
    }


    @GetMapping("/maintenance1")
    public Object getMaintenanceData1(@RequestAttribute("username") String username, int page, int limit,
                                      @RequestParam(required = false) String condition,
                                      @RequestParam(required = false) String batch, @RequestParam(required = false) String town,
                                      @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        //
//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if (startDate != "" && startDate != null) {
            startDate = startDate + " 00:00:00";

        }
        if (endDate != "" && endDate != null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);

        List<Device_Injection_maintanceEntity> maintenanceData = dryInjectionService.getMaintenanceData1(user, condition, startDate, endDate, batch, town);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }

    @PostMapping("/maintenance/report")
    public Object reportMaintenanceData(@RequestBody Map<String, Object> data) {

        List<Integer> list = (List<Integer>) data.get("list");
        dryInjectionService.report(list);
        return Result.ok();
    }

    @RequestMapping(value = "device_list", method = RequestMethod.GET)
    @ApiOperation("获取设备列表")
    public PageWrapper getDevices(@RequestAttribute("username") String username, @RequestParam("page") int page,
                                  @RequestParam("limit") int limit,
                                  @RequestParam(value = "searchText", required = false) String searchText,
                                  @RequestParam(value = "workerName", required = false) String workerName) {

        User user = userService.getUserByUserName(username);
        List<Device> list = null;
        Page<Object> pages = null;
        PageWrapper pageWrapper = new PageWrapper();
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
        pages = PageHelper.startPage(page, limit);
        if (user.getRole() == 1) {
            list = dryInjectionService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 2) {
            list = dryInjectionService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 3) {
            list = dryInjectionService.getDeviceByLocation(user.getAdcode(), null, null);
        }

        if (user.getRole() == 4) {
            list = dryInjectionService.getDeviceByManager(username);
        }

        if (user.getRole() == 5) {
            list = dryInjectionService.getDeviceByWorker(username);
        }
        pageWrapper.setData(list);
        pageWrapper.setTotalPage(pages.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pages.getTotal());
        return pageWrapper;
    }

    @RequestMapping("/searchDetail")
    public Object searchDetail(@RequestAttribute("username") String username, @RequestParam(required = false) String startDate,
                                   @RequestParam(required = false) String endDate, @RequestParam(required = false) String optionIndex,
                                   @RequestParam(required = false) String searchText,@RequestParam int page,
                                   @RequestParam int limit){
//        JSONObject jsonObject = new JSONObject();
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);


        int optVal = 0;

        if(optionIndex!=null && optionIndex!=""){
            optVal = Integer.parseInt(optionIndex);

        }

//        String dateString = "";

        if (!Objects.equals(startDate, "")) {
            startDate = startDate + " 00:00:00";
        }
        if (!Objects.equals(endDate, "")) {
            endDate = endDate + " 23:59:59";
        }
//        if(endDate!=null && endDate!="") {
//
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            ParsePosition pos = new ParsePosition(0);
//            Date currentTime_2 = formatter.parse(endDate, pos);
//
//            currentTime_2.setTime(currentTime_2.getTime() + 24 * 3600 * 1000);
//
//
//
//            dateString = formatter.format(currentTime_2);
//
//
//        }



//        jsonObject.put("Res",true);

        List<Device_Injection_maintanceEntity> list = null;




        if(user.getRole()==4){
            list = deviceInjectionMaintanceEntityMapper.selectByConditions(user.getParent(),optVal,searchText,startDate,endDate);
        }



        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setData(list);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        return Result.ok(pageWrapper);
    }

    @RequestMapping("/deleteRecord")
    public Object deleteRecord(@RequestParam String id){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error",false);

        deviceInjectionMaintanceEntityMapper.deleteRecord(Long.parseLong(id));
        return jsonObject;
    }


    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response,
                            String token,
                            @RequestParam(required = false) String startDate,
                            @RequestParam(required = false) String endDate,
                            @RequestParam(required = false) String colName,
                            @RequestParam(required = false) String searchText,
                            @RequestParam String username,
                            @RequestParam String adcode,
                            @RequestParam(required = false) String customProject,
                            @RequestParam(required = false) String town
    ) throws IOException {
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");

        User user = userService.getUserByUserName(username);
        System.out.println(customProject);
        System.out.println(town);
        System.out.println(adcode);
        if(colName.equals("1")){
            colName = "serial";
        }
        if(colName.equals("2")){
            colName = "CustomTown";
        }
        if(colName.equals("3")){
            colName = "batch";
        }
        if(colName.equals("4")){
            colName = "Worker";
        }
        List<Device_Injection_maintanceEntity> deviceNaturalEnemiesMaintanceEntities = null;

        if(user.getRole()==4){
            deviceNaturalEnemiesMaintanceEntities  = deviceInjectionMaintanceEntityMapper.selectByDateAndColSearch(username,startDate,endDate,colName,searchText,adcode,null,null);
        }else if(user.getRole()==3){
            deviceNaturalEnemiesMaintanceEntities  = deviceInjectionMaintanceEntityMapper.selectByDateAndColSearch(null,startDate,endDate,colName,searchText,adcode,customProject,town);
        }
        else{
            deviceNaturalEnemiesMaintanceEntities  = deviceInjectionMaintanceEntityMapper.selectByDateAndColSearch(null,startDate,endDate,colName,searchText,adcode,null,null);
        }
//        for (Device_NaturalEnemies_maintanceEntity d:
//             deviceNaturalEnemiesMaintanceEntities) {
//
//
//        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("注干剂管理情况明细表", "注干剂管理情况明细表"), Device_Injection_maintanceEntity.class, deviceNaturalEnemiesMaintanceEntities);
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

        User user = userService.getUserByUserName(username);






        ImageDownUtil imageDownUtil = new ImageDownUtil();

        File file=new File("/var/www/html/img");//路径

        int code = imageDownUtil.deleteFile(file);


        if(colName.equals("1")){
            colName = "serial";
        }
        if(colName.equals("2")){
            colName = "CustomTown";
        }
        if(colName.equals("3")){
            colName = "batch";
        }
        if(colName.equals("4")){
            colName = "Worker";
        }

        List<Device_Injection_maintanceEntity> deviceNaturalEnemiesMaintanceEntities  = deviceInjectionMaintanceEntityMapper.selectByDateAndColSearch(username,startDate,endDate,colName,searchText,adcode,null,null);

        for (Device_Injection_maintanceEntity d:deviceNaturalEnemiesMaintanceEntities) {
            try {
                String tmp = d.getPic();
                imageDownUtil.moveFile("/root/img/" + d.getPic(), "/var/www/html/img" + "/" + "编号："+ d.getSerial()+ "," + "区域：" + d.getCustomtown() + "," +"设备ID："+ d.getScanId()+ "," + "批次："+d.getBatch());

            }catch (Exception e){

            }

        }
        imageDownUtil.tarFile(user.getAdcode());
        response.sendRedirect(this.batchImgUrl + user.getAdcode() + ".tar");

    }



    //这里还没写完
    @RequestMapping("/importExcel")
    public Object importExcel(String token,@RequestParam("file") MultipartFile multipartFile) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        List<Device_Injection_maintanceEntity> deviceMaintenanceList = ExcelImportUtil
                .importExcel(multipartFile.getInputStream(), Device_Injection_maintanceEntity.class, importParams);
        for (Device_Injection_maintanceEntity d:
                deviceMaintenanceList) {
            Device device = deviceMapper.getDeviceByScanId(String.valueOf(d.getScanId()));
            BDInfo bdInfo = mBDComponent.parseLocation(d.getLatitude(), d.getLongitude());
            d.setTown(bdInfo.getResult().getAddressComponent().getTown());
            String info[] = distUtil.getNames(d.getAdcode(),null);
            d.setArea(info[2]);
            d.setCity(info[1]);
            d.setProvince(info[0]);
            d.setCustomSerial(d.getSerial());

            d.setDeviceId(Long.valueOf(deviceMapper.getDeviceByScanId(String.valueOf(d.getScanId())).getId()));
            d.setWoodstatus(deviceMapper.getInjectWoodStatus(null,d.getWoodStatusFront(),2).getId());

            d.setWorkContent(String.valueOf(deviceMapper.getInjectWorkContent(null,d.getWorkContentFront(),2).getId()));
            d.setInjectName(String.valueOf(deviceMapper.getInjectName(null,d.getInjectNameFront(),2).getId()));

            Device_Injection_maintanceEntity tmp =  deviceInjectionMaintanceEntityMapper.selectById2(BigInteger.valueOf(d.getScanId()),d.getBatch());
            if(tmp!=null){
                deviceInjectionMaintanceEntityMapper.updateRecordById(d);
                deviceMapper.updateByScanId(d.getScanId(),d.getLongitude(),d.getLatitude());
            }else {
                deviceInjectionMaintanceEntityMapper.insert(d);
                deviceMapper.updateByScanId(d.getScanId(),d.getLongitude(),d.getLatitude());            }

            if (device.getReceiveDate() == null || device.getLongitude() == null ||
                    device.getLatitude() == null || device.getAltitude() == null) {
                device.setLongitude(d.getLongitude());
                device.setLatitude(d.getLatitude());
                device.setAltitude(Double.valueOf(d.getAltitude()));
                device.setReceiveDate(d.getSubmitDate());
                deviceMapper.updateDevice(device);
            }
        }

        return "OK";
    }

    @PostMapping("/updateRec")
    public Object updateRec(@RequestBody Device_Injection_maintanceEntity d){


        deviceInjectionMaintanceEntityMapper.updateRecordByFront(d);
        return "OK";

    }







}
