package cn.huihongcloud.controller.medicine;


import cn.huihongcloud.entity.Device_Medicine_MaintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_Medicine_MaintanceEntityMapper;
import cn.huihongcloud.service.DeviceMedicineMaintanceService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/medicineData/Summary")
public class MedicineSummary {
    @Autowired
    private UserService userService;
    @Autowired
    private Device_Medicine_MaintanceEntityMapper device_medicine_maintanceEntityMapper;
    @Autowired
    private DeviceMedicineMaintanceService deviceMedicineMaintanceService;


    @RequestMapping("/byCustomReigon")
    public Object byCustomReigon(@RequestAttribute("username") String username,
                                 @RequestParam int page,
                                 @RequestParam int limit,
                                 @RequestParam String adcode,
                                 @RequestParam(required = false) Integer optionIndex,
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

        List<Device_Medicine_MaintanceEntity> device_medicine_maintanceEntities = deviceMedicineMaintanceService.getDryInjectionSummaryByCustomReigon(adcode,user, optionIndex, searchText, startDate, endDate);

        double totalMedicineQuaSum = 0;
        double totalAreaFzNum = 0;
        for (Device_Medicine_MaintanceEntity lim: device_medicine_maintanceEntities) {
            lim.setStartDate(startDate);
            lim.setEndDate(endDate);
            totalMedicineQuaSum += lim.getMedicineQuaSum();
            totalAreaFzNum += lim.getAreaFzSum();


        }


        for (Device_Medicine_MaintanceEntity lim: device_medicine_maintanceEntities) {
            lim.setTotalAreaFzNum(totalAreaFzNum);
            lim.setTotalMedicineQuaSum(totalMedicineQuaSum);
        }


        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(device_medicine_maintanceEntities);
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());

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
            Map<String, Integer> sum = device_medicine_maintanceEntityMapper.getDeviceSum(adcode, startDate, endDate);
            return Result.ok(sum);
        }
        if(user.getRole()==4){
            Map<String, Integer> sum = device_medicine_maintanceEntityMapper.getDeviceSum5(adcode, startDate, endDate);
            return Result.ok(sum);
        }
        return Result.failed();
    }


    @GetMapping("/medicinemanager")
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
        List<cn.huihongcloud.entity.summary.MedicineSummary> summaryEntities22 = device_medicine_maintanceEntityMapper.queryDeviceSummaryByManager111(adcode,startDate,endDate);
        for (cn.huihongcloud.entity.summary.MedicineSummary is: summaryEntities22) {
            User user = userService.getUserByUserName(is.getName());
            is.setName(user.getParent());
        }
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities22);
        return Result.ok(pageWrapper);
    }


    @RequestMapping("/medicinearea")
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
        List<cn.huihongcloud.entity.summary.MedicineSummary> summaryEntities = deviceMedicineMaintanceService.queryDeviceSummaryByArea111(adcode, startDate, endDate);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
    }

}
