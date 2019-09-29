package cn.huihongcloud.controller.injection;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.summary.InjectionSummary;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.DryInjectionService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/dataDetail")
    public Object getDataDetail(@RequestAttribute("username") String username,
                             @RequestParam int page,
                             @RequestParam int limit,
                             @RequestParam Integer optionIndex,
                             @RequestParam(required = false) String searchText,
                             @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        User user = userService.getUserByUserName(username);
        System.out.println(username);

        if (!Objects.equals(startDate, "")) {
            startDate = startDate + " 00:00:00";
        }
        if (!Objects.equals(endDate, "")) {
            endDate = endDate + " 23:59:59";
        }
        List<Device_Injection_maintanceEntity> deviceInjectionMaintanceEntities = dryInjectionService.getDryInjectionDetail(user, optionIndex, searchText, startDate, endDate);
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
        System.out.println(condition);
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
        System.out.println(maintenanceData);

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(maintenanceData);

        return pageWrapper;
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
        System.out.println(data.size());
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



}
