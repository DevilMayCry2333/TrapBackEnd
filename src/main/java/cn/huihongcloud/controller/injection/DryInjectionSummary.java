package cn.huihongcloud.controller.injection;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.inject_WoodStatus;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.summary.InjectionSummary;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_Injection_maintanceEntityMapper;
import cn.huihongcloud.service.DryInjectionService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/dryWatch/Summary")
public class DryInjectionSummary {
    @Autowired
    Device_Injection_maintanceEntityMapper deviceInjectionMaintanceEntityMapper;
    @Autowired
    UserService userService;
    @Autowired
    DryInjectionService dryInjectionService;

    @GetMapping("/manager")
    public Object getDeviceSummaryByManager(String adcode, int page, int limit,
                                            @RequestParam(required = false) String startDate,
                                            @RequestParam(required = false) String endDate) {
        PageWrapper pageWrapper = new PageWrapper();
        try {
            Page<Object> pageObject = PageHelper.startPage(page, limit);
            if(startDate!="" && startDate!=null) {
                startDate = startDate + " 00:00:00";
            }
            if(endDate!="" && endDate!=null) {
                endDate = endDate + " 23:59:59";
            }
            List<InjectionSummary> summaryEntities = deviceInjectionMaintanceEntityMapper.queryDeviceSummaryByManager(adcode,startDate,endDate);
            for (InjectionSummary is: summaryEntities) {
                User user = userService.getUserByUserName(is.getName());
                is.setName(user.getParent());
            }
            pageWrapper.setTotalPage(pageObject.getPages());
            pageWrapper.setCurrentPage(page);
            pageWrapper.setTotalNum(pageObject.getTotal());
            pageWrapper.setData(summaryEntities);
        }catch (Exception e){

        }
        return Result.ok(pageWrapper);
    }

    @GetMapping("/city")
    public Object getDeviceSummaryByCity(String adcode, int page, int limit,
                                         @RequestParam(required = false) String startDate,
                                         @RequestParam(required = false) String endDate) {
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        List<InjectionSummary> summaryEntities = deviceInjectionMaintanceEntityMapper.queryDeviceSummaryByCity(adcode,startDate,endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
    }

    @GetMapping("/sum")
    public Object getDeviceSum(@RequestAttribute("username") String username, String adcode, String startDate,
                               String endDate) {
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        if(user.getRole()<4) {
            Map<String, Integer> sum = deviceInjectionMaintanceEntityMapper.queryDeviceSum(adcode, startDate, endDate);
            return Result.ok(sum);
        }
        if(user.getRole()==4){
            Map<String, Integer> sum = deviceInjectionMaintanceEntityMapper.queryDeviceSum4(adcode, startDate, endDate);
            return Result.ok(sum);
        }
        return Result.failed();
    }

    @GetMapping("/worker")
    public Object getDeviceSummaryByWorker(@RequestAttribute("username") String username,
                                           @RequestParam(required = false) String adcode, int page, int limit,
                                           @RequestParam(required = false) String startDate,
                                           @RequestParam(required = false) String endDate) {
        User user = userService.getUserByUserName(username);
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
//        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<InjectionSummary> summaryEntities = null;
        if (user.getRole() != 4) {
            summaryEntities = deviceInjectionMaintanceEntityMapper.queryWorkerSummaryByAdcode(adcode,startDate,endDate);
        } else {
            summaryEntities = deviceInjectionMaintanceEntityMapper.queryWorkerSummaryByManager(user.getUsername(),startDate,endDate);
        }
        PageWrapper pageWrapper = new PageWrapper();
        //pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setTotalPage(1);
        //pageWrapper.setCurrentPage(page);
        pageWrapper.setCurrentPage(1);
//        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalNum(1000);
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
    }

    @GetMapping("/province")
    public Object getDeviceSummaryByProvince(String adcode, int page, int limit,
                                             @RequestParam(required = false) String startDate,
                                             @RequestParam(required = false) String endDate) {
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<InjectionSummary> summaryEntities = deviceInjectionMaintanceEntityMapper.queryDeviceSummaryByProvince(adcode,startDate,endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
    }

    @RequestMapping("/byCustomReigon")
    public Object byCustomReigon(@RequestAttribute("username") String username,
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



        List<inject_WoodStatus> injectWoodStatuses = deviceInjectionMaintanceEntityMapper.getDeadStatus();

        int deadId = injectWoodStatuses.get(0).getId();


        List<Device_Injection_maintanceEntity> deviceInjectionMaintanceEntities = dryInjectionService.getDryInjectionSummaryByCustomReigon(user, optionIndex, searchText, startDate, endDate,deadId);

        int totalInjectNum = 0;
        int totalDeadNum = 0;
        int totalWoodNum = 0;
        for (Device_Injection_maintanceEntity lim: deviceInjectionMaintanceEntities) {



            lim.setStartDate(startDate);
            lim.setEndDate(endDate);
            totalDeadNum += lim.getWoodStatusSum();


            totalInjectNum += lim.getInjectNumSum();
            totalWoodNum += Integer.parseInt(lim.getWoodNum());
            lim.setDeadRate(lim.getWoodStatusSum()/Double.parseDouble(lim.getWoodNum()));
            lim.setAverageNum(lim.getInjectNumSum()/Double.parseDouble(lim.getWoodNum()));
        }

        for (Device_Injection_maintanceEntity lim: deviceInjectionMaintanceEntities) {
            lim.setTotalInjectSum(totalInjectNum);
            lim.setTotalDeadSum(totalDeadNum);
            lim.setTotalWoodSum(totalWoodNum);
        }


        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(deviceInjectionMaintanceEntities);
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());

        return Result.ok(pageWrapper);
    }





}
