package cn.huihongcloud.controller.deadtrees;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.summary.DeadTreesSummary;
import cn.huihongcloud.entity.summary.NaturalSummary;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_DeadTrees_maintanceEntityMapper;
import cn.huihongcloud.mapper.UserMapper;
import cn.huihongcloud.service.DeadTreeCutService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/deadTree/Summary")
public class DeadTreeSummary {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    Device_DeadTrees_maintanceEntityMapper deviceDeadTreesMaintanceEntityMapper;
    @Autowired
    DeadTreeCutService deadTreeCutService;

    JSONObject jsonObject = new JSONObject();

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




        List<DeadTreesSummary> summaryEntities = deviceDeadTreesMaintanceEntityMapper.queryDeviceSummaryByArea(adcode,startDate,endDate);
//        for (int i = 0; i <summaryEntities.size() ; i++) {
//
//        }
        for (int i = 0; i < summaryEntities.size(); i++) {
            String SliceCrushing = "0";
            String BaggingFumigation = "0";
            String IncinerationTreatment = "0";
            String WireMesh = "0";
            String Other = "0";
            DecimalFormat df = new DecimalFormat("0.000000");
            Double d = Double.parseDouble(summaryEntities.get(i).getWoodVolume());
            summaryEntities.get(i).setWoodVolume(df.format(d));



            try {
                SliceCrushing = deviceDeadTreesMaintanceEntityMapper.queryMannerSum11(adcode,summaryEntities.get(i).getName(),null).get(0).getMannerSum11();
            }catch (Exception e){

            }try {
                BaggingFumigation = deviceDeadTreesMaintanceEntityMapper.queryMannerSum22(adcode,summaryEntities.get(i).getName(),null).get(0).getMannerSumBagging();


            }catch (Exception e){


            }try {
                IncinerationTreatment = deviceDeadTreesMaintanceEntityMapper.queryMannerSum33(adcode,summaryEntities.get(i).getName(),null).get(0).getMannerSumBurn();



            }catch (Exception e){
                e.printStackTrace();


            }try {
                WireMesh =  deviceDeadTreesMaintanceEntityMapper.queryMannerSum44(adcode,summaryEntities.get(i).getName(),null).get(0).getMannerSum44();
            }catch (Exception e){

            }try {
                Other = deviceDeadTreesMaintanceEntityMapper.queryMannerSum55(adcode,summaryEntities.get(i).getName(),null).get(0).getMannerSum55();
            }catch (Exception e){

            }
            summaryEntities.get(i).setMannerSum0(
                    "切片粉碎:" +SliceCrushing  + "" + "套袋熏蒸:" + BaggingFumigation+"  "+
                            "焚烧处理:" + IncinerationTreatment + "  "+ "铁丝罩网:" + WireMesh+" "+
                            "其他:" + Other);




        }

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(summaryEntities);
        return Result.ok(pageWrapper);
    }

    @GetMapping("/manager")
    public Object getDeviceSummaryByManager(String adcode, int page, int limit,String CustomProject,
                                            @RequestParam(required = false) String startDate,
                                            @RequestParam(required = false) String endDate) {
        Page<Object> pageObject = PageHelper.startPage(page, limit);

        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        List<User> userList = userMapper.getProjectAdminByAdcode(adcode);
        for (User user : userList){

        }
        List<DeadTreesSummary> summaryEntities = deviceDeadTreesMaintanceEntityMapper.queryDeviceSummaryByManager(adcode,startDate,endDate);


        for (int i = 0; i < summaryEntities.size(); i++) {
            summaryEntities.get(i).setMannerSum(
                    "切片粉碎:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum1(adcode,summaryEntities.get(i).getName()).getMannerSum1() + "  " + "套袋熏蒸:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum2(adcode,summaryEntities.get(i).getName()).getMannerSum2() +"  "+
                    "焚烧处理:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum3(adcode,summaryEntities.get(i).getName()).getMannerSum3() +"  "+ "铁丝罩网:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum4(adcode,summaryEntities.get(i).getName()).getMannerSum4()+" "+
                    "其他:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum5(adcode,summaryEntities.get(i).getName()).getMannerSum5());
        }
//        for (User user : userList){
//            for (DeadTreesSummary ns:summaryEntities) {
//
//                    ns.setMannerSum(
//                            "切片粉碎:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum1(adcode,user.getUsername()).getMannerSum1() + "  " + "套袋熏蒸:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum2(adcode,user.getUsername()).getMannerSum2() +"  "+
//                            "焚烧处理:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum3(adcode,user.getUsername()).getMannerSum3() +"  "+ "铁丝罩网:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum4(adcode,user.getUsername()).getMannerSum4()+" "+
//                            "其他:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum5(adcode,user.getUsername()).getMannerSum5()
//                    );
//            }
//        }
//         jsonObject.put("methodSum1",deviceDeadTreesMaintanceEntityMapper.queryMannerSum1(adcode));
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
            Map<String, Double> sum = deviceDeadTreesMaintanceEntityMapper.queryDeviceSum(adcode, startDate, endDate);


            DecimalFormat df = new DecimalFormat("0.000000");
            Double d = Double.parseDouble(String.valueOf(sum.get("sum")));
            sum.put("sum",Double.parseDouble(df.format(d)));
            return Result.ok(sum);
        }
        if(user.getRole()==4){
            Map<String, Double> sum = deviceDeadTreesMaintanceEntityMapper.queryDeviceSum4(adcode, startDate, endDate);


            DecimalFormat df = new DecimalFormat("0.000000");
            Double d = Double.parseDouble(String.valueOf(sum.get("sum")));
            sum.put("sum",Double.parseDouble(df.format(d)));

            return Result.ok(sum);
        }
        return Result.failed();
    }

    //市级
    @RequestMapping("/city")
    public Object city(@RequestParam String adcode,
                       @RequestParam String startDate,
                       @RequestParam String endDate,
                       @RequestParam int page,
                       @RequestParam int limit){
        //
        Page<Object> pageObject = PageHelper.startPage(page, limit);

        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        List<User> userList = userMapper.getProjectAdminByAdcode(adcode);
        for (User user : userList){

        }
        List<DeadTreesSummary> deadTreesSummaries = deviceDeadTreesMaintanceEntityMapper.queryDeviceSummaryBycity(adcode, startDate, endDate);



        for (int i = 0; i < deadTreesSummaries.size(); i++) {
            deadTreesSummaries.get(i).setMannerSum0(
                    "切片粉碎:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum11(adcode,null,deadTreesSummaries.get(i).getName()).get(0).getMannerSum11() + "  " + "套袋熏蒸:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum22(adcode,null,deadTreesSummaries.get(i).getName()).get(0).getMannerSumBagging() +"  "+
                            "焚烧处理:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum33(adcode,null,deadTreesSummaries.get(i).getName()).get(0).getMannerSumBurn() +"  "+ "铁丝罩网:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum44(adcode,null,deadTreesSummaries.get(i).getName()).get(0).getMannerSum44()+" "+
                            "其他:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum55(adcode,null,deadTreesSummaries.get(i).getName()).get(0).getMannerSum55());
        }
//        for (User user : userList){
//            for (DeadTreesSummary ns:summaryEntities) {
//
//                    ns.setMannerSum(
//                            "切片粉碎:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum1(adcode,user.getUsername()).getMannerSum1() + "  " + "套袋熏蒸:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum2(adcode,user.getUsername()).getMannerSum2() +"  "+
//                            "焚烧处理:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum3(adcode,user.getUsername()).getMannerSum3() +"  "+ "铁丝罩网:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum4(adcode,user.getUsername()).getMannerSum4()+" "+
//                            "其他:" + deviceDeadTreesMaintanceEntityMapper.queryMannerSum5(adcode,user.getUsername()).getMannerSum5()
//                    );
//            }
//        }
//         jsonObject.put("methodSum1",deviceDeadTreesMaintanceEntityMapper.queryMannerSum1(adcode));
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setData(deadTreesSummaries);
        return Result.ok(pageWrapper);

    }




    @RequestMapping("/byCustomReigon")
    public Object byCustomReigon(@RequestAttribute(value = "username",required = false) String username,
                                 @RequestParam String adcode,
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

        List<Device_DeadTrees_maintanceEntity> device_deadTrees_maintanceEntities = deadTreeCutService.getDeadTreesSummaryByCustomReigon(user, optionIndex, searchText, startDate, endDate);


        for(int i=0;i<device_deadTrees_maintanceEntities.size();i++){
            device_deadTrees_maintanceEntities.get(i).setDeadTreesMannerTotal("切片粉碎:" + deviceDeadTreesMaintanceEntityMapper.queryDeadTreesMannerOne(user.getUsername(),device_deadTrees_maintanceEntities.get(i).getCustomTown()).get(0).getDeadTreesMannerOne() + "  " + "套袋熏蒸:" + deviceDeadTreesMaintanceEntityMapper.queryDeadTreesMannerTwo(user.getUsername(),device_deadTrees_maintanceEntities.get(i).getCustomTown()).get(0).getDeadTreesMannerTwo() +"  "+
                    "焚烧处理:" + deviceDeadTreesMaintanceEntityMapper.queryDeadTreesMannerThree(user.getUsername(),device_deadTrees_maintanceEntities.get(i).getCustomTown()).get(0).getDeadTreesMannerThree() +"  "+ "铁丝罩网:" + deviceDeadTreesMaintanceEntityMapper.queryDeadTreesMannerFour(user.getUsername(),device_deadTrees_maintanceEntities.get(i).getCustomTown()).get(0).getDeadTreesMannerFour()+" "+
                    "其他:" + deviceDeadTreesMaintanceEntityMapper.queryDeadTreesMannerFive(user.getUsername(),device_deadTrees_maintanceEntities.get(i).getCustomTown()).get(0).getDeadTreesMannerFive());

        }
        double totalWoodVolumeSum = 0;
        Integer totalDeadIdSum = 0;
        for (Device_DeadTrees_maintanceEntity lim: device_deadTrees_maintanceEntities) {
            lim.setStartDate(startDate);
            lim.setEndDate(endDate);
            totalWoodVolumeSum += Double.parseDouble(lim.getTotalWoodVolume());
            totalDeadIdSum += lim.getTotalDeadId();


        }


        for (Device_DeadTrees_maintanceEntity lim: device_deadTrees_maintanceEntities) {
            lim.setTotalWoodVolumeSum(totalWoodVolumeSum);
            lim.setTotalDeadIdSum(totalDeadIdSum);
        }


        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(device_deadTrees_maintanceEntities);
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());

        return Result.ok(pageWrapper);
    }

}
