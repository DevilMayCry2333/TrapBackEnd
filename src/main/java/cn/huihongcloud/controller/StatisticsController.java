package cn.huihongcloud.controller;

import cn.huihongcloud.component.JWTComponent;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.statistics.InputEntity;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.DeviceMaintenanceService;
import cn.huihongcloud.service.StatisticsService;
import cn.huihongcloud.service.UserService;
import cn.huihongcloud.util.DistUtil;
import cn.huihongcloud.util.StatisticsUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/9/23
 */
@RestController
public class StatisticsController {

    @Autowired
    private DeviceMaintenanceService deviceMaintenanceService;

    @Autowired
    private DistUtil distUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private JWTComponent jwtComponent;

    @GetMapping("auth_api/statistics/city")
    public Object getStatisticsDataForCity(String adcode, String startDate, String endDate) {
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        List<InputEntity> inputEntityForCity = deviceMaintenanceService.getInputEntityForCity(adcode, startDate, endDate);
        if (inputEntityForCity.isEmpty() || inputEntityForCity.size()<2){
            return Result.failed();
        }
        return Result.ok(StatisticsUtil.getResult(inputEntityForCity));
    }
//and date_format(`date`, '%Y') >= #{startYear}
    @GetMapping("auth_api/monthSummary/city")
    public Object getMonthSummaryDataByCity(@RequestAttribute("username") String username,
                                            String adcode,String startYear,
                                            String endYear,
                                            @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if(startYear!="" && startYear!=null) {
            startYear = startYear + " 00:00:00";
        }
        if(endYear!="" && endYear!=null) {
            endYear = endYear + " 23:59:59";
        }
        Object monthSummaryByCity= deviceMaintenanceService.getMonthSummaryByCity(adcode, startYear,endYear);
        if (((List) monthSummaryByCity).isEmpty()){
            return Result.failed();
        }


        //currentUser.setProvince(Dist[0]);
        //currentUser.setCity(Dist[1]);
        //currentUser.setArea(Dist[2]);
        //Page<Object> pageObject = PageHelper.startPage(page, limit);
        PageWrapper pageWrapper = new PageWrapper();
        //pageWrapper.setTotalPage(pageObject.getPages());
        //pageWrapper.setTotalNum(pageObject.getTotal());

        pageWrapper.setData(monthSummaryByCity);
        return pageWrapper;
    }

    @GetMapping("auth_api/statistics/area")
    public Object getStatisticsDataForArea(String adcode, String startDate, String endDate) {
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        List<InputEntity> inputEntityForArea = deviceMaintenanceService.getInputEntityForArea(adcode, startDate, endDate);
        if (inputEntityForArea.isEmpty()|| inputEntityForArea.size()<2){
            return Result.failed();
        }
        return Result.ok(StatisticsUtil.getResult(inputEntityForArea));
    }
    @GetMapping("auth_api/monthSummary/area")
    public Object getMonthSummaryDataByArea(String adcode,String startYear,
                                            String endYear,
                                            @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if(startYear!="" && startYear!=null) {
            startYear = startYear + " 00:00:00";
        }
        if(endYear!="" && endYear!=null) {
            endYear = endYear + " 23:59:59";
        }
        Object monthSummaryByArea= deviceMaintenanceService.getMonthSummaryByArea(adcode,startYear,endYear);
        if (((List) monthSummaryByArea).isEmpty()){
            return Result.failed();
        }
        //Page<Object> pageObject = PageHelper.startPage(page, limit);
        PageWrapper pageWrapper = new PageWrapper();

        pageWrapper.setData(monthSummaryByArea);
        return pageWrapper;
    }
    @GetMapping("auth_api/statistics/town")
    public Object getStatisticsDataForTown(String adcode, String startDate, String endDate) {
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        List<InputEntity> inputEntityForTown = deviceMaintenanceService.getInputEntityForTown(adcode, startDate, endDate);
        if (inputEntityForTown.isEmpty()|| inputEntityForTown.size()<2){
            return Result.failed();
        }

        return Result.ok(StatisticsUtil.getResult(inputEntityForTown));
    }
    @GetMapping("auth_api/monthSummary/town")
    public Object getMonthSummaryDataByTown(String adcode,String startYear,
                                            String endYear,
                                            @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if(startYear!="" && startYear!=null) {
            startYear = startYear + " 00:00:00";
        }
        if(endYear!="" && endYear!=null) {
            endYear = endYear + " 23:59:59";
        }
        Object monthSummaryByTown= deviceMaintenanceService.getMonthSummaryByTown(adcode, startYear,endYear);
        if (((List) monthSummaryByTown).isEmpty()){
            return Result.failed();
        }
        //Page<Object> pageObject = PageHelper.startPage(page, limit);
        PageWrapper pageWrapper = new PageWrapper();

        pageWrapper.setData(monthSummaryByTown);
        return pageWrapper;
    }

    @GetMapping("auth_api/statistics/worker")
    public Object getStatisticsDataForWorker(@RequestAttribute("username") String user, String startDate, String endDate,
                                             String manager) {
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        if (StringUtils.isEmpty(manager)) {
            manager = user;
        }
//`date` like concat(#{endYear}, '%')
        List<InputEntity> inputEntityForWorker = deviceMaintenanceService.getInputEntityForWorker(manager, startDate, endDate);
        if (inputEntityForWorker.isEmpty() || inputEntityForWorker.size()<2){
            return Result.failed();
        }
        System.out.println(inputEntityForWorker);
        return Result.ok(StatisticsUtil.getResult(inputEntityForWorker));
    }
    @GetMapping("auth_api/monthSummary/worker")
    public Object getMonthSummaryDataByWorker(String adcode,String startYear,
                                              String endYear,
                                            @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if(startYear!="" && startYear!=null) {
            startYear = startYear + " 00:00:00";
        }
        if(endYear!="" && endYear!=null) {
            endYear = endYear + " 23:59:59";
        }
        Object monthSummaryByWorker= deviceMaintenanceService.getMonthSummaryByWorker(adcode, startYear,endYear);
        if (((List) monthSummaryByWorker).isEmpty()){
            return Result.failed();
        }
        //Page<Object> pageObject = PageHelper.startPage(page, limit);
        PageWrapper pageWrapper = new PageWrapper();

        pageWrapper.setData(monthSummaryByWorker);
        return pageWrapper;
    }
    @GetMapping("statistics/export")
    public void exportStatisticsData(HttpServletResponse response, @RequestParam(required = false) String adcode, @RequestParam(required = false) String manager,
                                     String startDate, String endDate, Integer type, String token) {
        if(startDate!="" && startDate!=null) {
            startDate = startDate + " 00:00:00";
        }
        if(endDate!="" && endDate!=null) {
            endDate = endDate + " 23:59:59";
        }
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");
        String username = jwtComponent.verify(token);
        Workbook workbook = statisticsService.exportStatisticsData(adcode, manager, startDate, endDate, type);
        if(workbook!=null) {
            try {
                workbook.write(response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
