package cn.huihongcloud.controller.natural;

import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_NaturalEnemies_maintanceEntityMapper;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/natural/Summary")
public class NaturalSummary {
    @Autowired
    Device_NaturalEnemies_maintanceEntityMapper deviceNaturalEnemiesMaintanceEntityMapper;
    @Autowired
    UserService userService;

    @RequestMapping("/area")
    public Object getDeviceSummaryByArea(String adcode, int page, int limit,
                                         @RequestParam(required = false) String startDate,
                                         @RequestParam(required = false) String endDate) {
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }

        List<cn.huihongcloud.entity.summary.NaturalSummary> summaryEntities = deviceNaturalEnemiesMaintanceEntityMapper.queryDeviceSummaryByArea(adcode,startDate,endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
    }

    @GetMapping("/manager")
    public Object getDeviceSummaryByManager(String adcode, int page, int limit,
                                            @RequestParam(required = false) String startDate,
                                            @RequestParam(required = false) String endDate) {
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        List<cn.huihongcloud.entity.summary.NaturalSummary> summaryEntities = deviceNaturalEnemiesMaintanceEntityMapper.queryDeviceSummaryByManager(adcode,startDate,endDate);
        for (cn.huihongcloud.entity.summary.NaturalSummary ns:summaryEntities) {
            User user = userService.getUserByUserName(ns.getName());
            ns.setName(user.getParent());
        }
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
            Map<String, Long> sum = deviceNaturalEnemiesMaintanceEntityMapper.queryDeviceSum(adcode, startDate, endDate);
            return Result.ok(sum);
        }
        if(user.getRole()==4){
            Map<String, Long> sum = deviceNaturalEnemiesMaintanceEntityMapper.queryDeviceSum4(adcode, startDate, endDate);
            return Result.ok(sum);
        }
        return Result.failed();
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
        List<cn.huihongcloud.entity.summary.NaturalSummary> summaryEntities = deviceNaturalEnemiesMaintanceEntityMapper.queryDeviceSummaryByCity(adcode,startDate,endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
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
        List<cn.huihongcloud.entity.summary.NaturalSummary> summaryEntities = null;
        if (user.getRole() != 4) {
            summaryEntities = deviceNaturalEnemiesMaintanceEntityMapper.queryWorkerSummaryByAdcode(adcode,startDate,endDate);
        } else {
            summaryEntities = deviceNaturalEnemiesMaintanceEntityMapper.queryWorkerSummaryByManager(user.getUsername(),startDate,endDate);
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
        List<cn.huihongcloud.entity.summary.NaturalSummary> summaryEntities = deviceNaturalEnemiesMaintanceEntityMapper.queryDeviceSummaryByProvince(adcode,startDate,endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
    }


}
