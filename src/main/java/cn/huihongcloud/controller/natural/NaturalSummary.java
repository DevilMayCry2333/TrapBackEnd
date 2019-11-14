package cn.huihongcloud.controller.natural;

import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_NaturalEnemies_maintanceEntityMapper;
import cn.huihongcloud.mapper.UserMapper;
import cn.huihongcloud.service.NaturalEnemyService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/natural/Summary")
public class NaturalSummary {
    @Autowired
    Device_NaturalEnemies_maintanceEntityMapper deviceNaturalEnemiesMaintanceEntityMapper;
    @Autowired
    UserService userService;
    @Autowired
    NaturalEnemyService naturalEnemyService;
    @Autowired
    UserMapper userMapper;

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

        List<cn.huihongcloud.entity.summary.NaturalSummary>summaryEntities = deviceNaturalEnemiesMaintanceEntityMapper.queryDeviceSummaryByArea(adcode,startDate,endDate);
        for(int i=0;i<summaryEntities.size();i++){
               summaryEntities.get(i).setTotalNaturalMannerByTown("花绒寄甲: " +""+deviceNaturalEnemiesMaintanceEntityMapper
                       .queryNatualMannerOneBytownOne(adcode,summaryEntities.get(i).getName()).get(0).getNaturalMannerOneByTown()
                       + "肿腿蜂：" +""+ deviceNaturalEnemiesMaintanceEntityMapper.queryNatualMannerOneBytownTwo(adcode,summaryEntities.get(i).getName()).get(0).getNaturalMannerTwoByTown()
                       + "卵卡: " + "" + deviceNaturalEnemiesMaintanceEntityMapper.queryNatualMannerOneBytownThree(adcode,summaryEntities.get(i).getName()).get(0).getNaturalMannerThreeByTown());
        }
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
        List<User> userList = userMapper.getProjectAdminByAdcode(adcode);
        for (User user : userList){
            System.out.printf("=================================");
            System.out.println(user.getUsername());
        }
        for(int i=0;i<summaryEntities.size();i++){
            summaryEntities.get(i).setTotalNaturalMannerByCustomProject("花绒寄甲:" + deviceNaturalEnemiesMaintanceEntityMapper.queryNatualMannerOneByCustomProject(adcode,userList.get(i).getUsername()).getNaturalMannerOneByCustomProject() + "  " + "肿腿蜂:" + deviceNaturalEnemiesMaintanceEntityMapper.queryNatualMannerTwoByCustomProject(adcode,userList.get(i).getUsername()).getNaturalMannerTwoByCustomProject() +"  "+
                    "卵卡:" + deviceNaturalEnemiesMaintanceEntityMapper.queryNatualMannerThreeByCustomProject(adcode,userList.get(i).getUsername()).getNaturalMannerThreeByCustomProject());
        }
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
        System.out.printf("==================");


        if (!Objects.equals(startDate, "")) {
            startDate = startDate + " 00:00:00";
        }
        if (!Objects.equals(endDate, "")) {
            endDate = endDate + " 23:59:59";
        }

        List<Device_NaturalEnemies_maintanceEntity> device_natural_maintanceEntities = naturalEnemyService.getNaturalSummaryByCustomReigon(user, optionIndex, searchText, startDate, endDate);

        for(int i = 0 ; i<device_natural_maintanceEntities.size();i++){
            device_natural_maintanceEntities.get(i).setNatualMannerTotal("花绒寄甲: " +""+deviceNaturalEnemiesMaintanceEntityMapper
            .queryNatualMannerOne(user.getUsername(),device_natural_maintanceEntities.get(i).getCustomTown()).get(0).getNatualMannerOne()
            + "肿腿蜂：" +""+ deviceNaturalEnemiesMaintanceEntityMapper.queryNatualMannerTwo(user.getUsername(),device_natural_maintanceEntities.get(i).getCustomTown()).get(0).getNatualMannerTwo()
            + "卵卡: " + "" + deviceNaturalEnemiesMaintanceEntityMapper.queryNatualMannerThree(user.getUsername(),device_natural_maintanceEntities.get(i).getCustomTown()).get(0).getNatualMannerThree());
        }
        int totalReleasePlace = 0;
        for (Device_NaturalEnemies_maintanceEntity lim: device_natural_maintanceEntities) {
            lim.setStartDate(startDate);
            lim.setEndDate(endDate);
            totalReleasePlace += lim.getReleaseNum();
            System.out.println(lim.getCustomTown());
            System.out.println(lim.getId());
        }


        for (Device_NaturalEnemies_maintanceEntity lim: device_natural_maintanceEntities) {
            lim.setTotalReleasePlace(totalReleasePlace);
        }


        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(device_natural_maintanceEntities);
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());

        return Result.ok(pageWrapper);
    }



}
