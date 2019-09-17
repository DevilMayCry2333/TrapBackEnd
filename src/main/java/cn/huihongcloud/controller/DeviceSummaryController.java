package cn.huihongcloud.controller;

import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.summary.DeviceDetail;
import cn.huihongcloud.entity.summary.SummaryEntity;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceSummaryMapper;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2019/1/18
 */
@RestController
@RequestMapping("/auth_api/device_summary")
public class DeviceSummaryController {

    @Autowired
    private DeviceSummaryMapper deviceSummaryMapper;

    @Autowired
    private UserService userService;

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
        List<SummaryEntity> summaryEntities = deviceSummaryMapper.queryDeviceSummaryByProvince(adcode,startDate,endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
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
        List<SummaryEntity> summaryEntities = deviceSummaryMapper.queryDeviceSummaryByCity(adcode,startDate,endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
    }

    @GetMapping("/area")
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
        List<SummaryEntity> summaryEntities = deviceSummaryMapper.queryDeviceSummaryByArea(adcode,startDate,endDate);
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
        List<SummaryEntity> summaryEntities = deviceSummaryMapper.queryDeviceSummaryByManager(adcode,startDate,endDate);
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
        List<SummaryEntity> summaryEntities = null;
        if (user.getRole() != 4) {
            summaryEntities = deviceSummaryMapper.queryWorkerSummaryByAdcode(adcode,startDate,endDate);
        } else {
            summaryEntities = deviceSummaryMapper.queryWorkerSummaryByManager(user.getUsername(),startDate,endDate);
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

    @GetMapping("/sum")
    public Object getDeviceSum(@RequestAttribute("username") String username,String adcode,String startDate,
                               String endDate) {
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        User user = userService.getUserByUserName(username);
        if(user.getRole()<4) {
            Map<String, Long> sum = deviceSummaryMapper.queryDeviceSum(adcode, startDate, endDate);
            return Result.ok(sum);
        }
        if(user.getRole()==4){
            Map<String, Long> sum = deviceSummaryMapper.queryDeviceSum4(adcode, startDate, endDate);
            return Result.ok(sum);
        }
        return Result.failed();
    }

    @GetMapping("/detail")
    public Object getDetailByAdcode(String adcode) {
        List<DeviceDetail> deviceDetails = deviceSummaryMapper.queryDeviceDetail(adcode);
        return Result.ok(deviceDetails);
    }
}
