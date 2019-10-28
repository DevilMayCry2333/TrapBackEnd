package cn.huihongcloud.controller.medicine;


import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
                                 @RequestParam Integer optionIndex,
                                 @RequestParam(required = false) String searchText,
                                 @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        System.out.println(username);

        if (!Objects.equals(startDate, "")) {
            startDate = startDate + " 00:00:00";
        }
        if (!Objects.equals(endDate, "")) {
            endDate = endDate + " 23:59:59";
        }

        List<Device_Medicine_MaintanceEntity> device_medicine_maintanceEntities = deviceMedicineMaintanceService.getDryInjectionSummaryByCustomReigon(user, optionIndex, searchText, startDate, endDate);

        double totalMedicineQuaSum = 0;
        double totalAreaFzNum = 0;
        for (Device_Medicine_MaintanceEntity lim: device_medicine_maintanceEntities) {
            lim.setStartDate(startDate);
            lim.setEndDate(endDate);
            totalMedicineQuaSum += lim.getMedicineQuaSum();
            totalAreaFzNum += lim.getAreaFzSum();
            System.out.println(lim.getCustomTown());
            System.out.println(lim.getId());
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
}
