package cn.huihongcloud.controller.medicine;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.huihongcloud.entity.Device_Medicine_MaintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_Medicine_MaintanceEntityMapper;
import cn.huihongcloud.service.DeviceMedicineMaintanceService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/medicineDataDetail")
public class MedicineDataDetail {
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceMedicineMaintanceService deviceMedicineMaintanceService;
    @Autowired
    private Device_Medicine_MaintanceEntityMapper device_medicine_maintanceEntityMapper;


    @RequestMapping("/getDetail")
    public Object getDetail(@RequestAttribute("username") String username,
                            @RequestParam int page,
                            @RequestParam int limit,
                            @RequestParam(required = false) Integer optionIndex,
                            @RequestParam(required = false) String searchText,
                            @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate){
        User user =  userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page,limit);

        System.out.println(username);
        if (!Objects.equals(startDate, "")) {
            startDate = startDate + " 00:00:00";
        }
        if (!Objects.equals(endDate, "")) {
            endDate = endDate + " 23:59:59";
        }
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        ft.format(date);

        List<Device_Medicine_MaintanceEntity> list = null;
        if(user.getRole()==4){
            list = deviceMedicineMaintanceService.getMedicineDetail(user.getParent(),optionIndex,searchText,startDate,endDate);
            System.out.println(list.size());
        }if(user.getRole()<=3){
            list = device_medicine_maintanceEntityMapper.selectByConditionsAdcode(user.getAdcode(),optionIndex,searchText,startDate,endDate);
        }
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(list);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setTotalNum(pageObject.getPageNum());
        return pageWrapper;
    }

    @RequestMapping("/deleteRecord")
    public Object deleteRecord(String id){
        return device_medicine_maintanceEntityMapper.deleteByPrimaryKey(Long.parseLong(id));
    }

    @RequestMapping("/updateData")
    public Object  updateRecord(@RequestBody Device_Medicine_MaintanceEntity device_medicine_maintanceEntity){
        System.out.println(device_medicine_maintanceEntity.getId());
        device_medicine_maintanceEntityMapper.updateByPrimaryKeySelective(device_medicine_maintanceEntity);
        return "ok";
    }

    @RequestMapping("/deleteData")
    public Object delete(String id){
        device_medicine_maintanceEntityMapper.deleteByPrimaryKey(Long.parseLong(id));
        return "ok";
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

        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(colName);
        System.out.println(searchText);
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

        List<Device_Medicine_MaintanceEntity> device_medicine_maintanceEntities  = device_medicine_maintanceEntityMapper.selectByDateAndColSearch(username,startDate,endDate,colName,searchText,adcode);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("药剂防治管理", "药剂防治管理"), Device_Medicine_MaintanceEntity.class, device_medicine_maintanceEntities);
        workbook.write(response.getOutputStream());
    }


    @RequestMapping("/importExcel")
    public Object importExcel(String token,@RequestParam("file") MultipartFile multipartFile) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        List<Device_Medicine_MaintanceEntity> deviceMaintenanceList = ExcelImportUtil
                .importExcel(multipartFile.getInputStream(), Device_Medicine_MaintanceEntity.class, importParams);
        for (Device_Medicine_MaintanceEntity d:
                deviceMaintenanceList) {
            System.out.println("natural");
            System.out.println(d.getId());
            Device_Medicine_MaintanceEntity tmp =  device_medicine_maintanceEntityMapper.selectById1(BigInteger.valueOf(d.getId()));
            if(tmp!=null){
                device_medicine_maintanceEntityMapper.updateRecordById1(d);
            }else {
                device_medicine_maintanceEntityMapper.insert(d);
            }
        }
        return "OK";
    }

    @RequestMapping(value = "device_list", method = RequestMethod.GET)
    @ApiOperation("获取设备列表")
    public PageWrapper getDevices(@RequestAttribute("username") String username, @RequestParam("page") int page,
                                  @RequestParam("limit") int limit,
                                  @RequestParam(value = "searchText", required = false) String searchText,
                                  @RequestParam(value = "workerName", required = false) String workerName) {
        System.out.println(workerName);
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
            list = deviceMedicineMaintanceService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 2) {
            list = deviceMedicineMaintanceService.getDeviceByLocation(user.getAdcode(), null, null);
        }
        if (user.getRole() == 3) {
            list = deviceMedicineMaintanceService.getDeviceByLocation(user.getAdcode(), null, null);
        }

        if (user.getRole() == 4) {
            list = deviceMedicineMaintanceService.getDeviceByManager(username);
        }

        if (user.getRole() == 5) {
            list = deviceMedicineMaintanceService.getDeviceByWorker(username);
        }
        pageWrapper.setData(list);
        pageWrapper.setTotalPage(pages.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pages.getTotal());
        return pageWrapper;
    }


    @PostMapping("/maintenance/medicinereport")
    public Object reportMaintenanceData(@RequestBody Map<String, Object> data) {
        System.out.println(data.size());
        List<Integer> list = (List<Integer>) data.get("list");
        deviceMedicineMaintanceService.report11(list);
        return Result.ok();
    }

    @GetMapping("/maintenance1")
    public Object getMaintenanceData1(@RequestAttribute("username") String username, int page, int limit,
                                      @RequestParam(required = false) String condition,
                                      @RequestParam(required = false) String batch, @RequestParam(required = false) String town,
                                      @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        //System.out.println(startDate+"cc");
//        if(startDate.equals("null")){
//            startDate=null;
//        }
//        if(endDate.equals("null")){
//            endDate=null;
//        }
        if (startDate != "" && startDate != null) {
            startDate = startDate + " 00:00:00";
            System.out.println(startDate + "dd");
        }
        if (endDate != "" && endDate != null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);

        List<Device_Medicine_MaintanceEntity> maintenanceData = deviceMedicineMaintanceService.getMaintenanceData1(user, condition, startDate, endDate, batch, town);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return pageWrapper;
    }
}
