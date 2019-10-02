package cn.huihongcloud.controller;

import cn.huihongcloud.component.JWTComponent;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.entity.weather.HourlyWeather;
import cn.huihongcloud.entity.weather.Weather;
import cn.huihongcloud.service.UserService;
import cn.huihongcloud.service.WeatherService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/9/22
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private JWTComponent jwtComponent;

    @Autowired
    private UserService userService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

    @ApiOperation("获取一定日期范围的天气")
    @GetMapping
    public Object getWeatherInRange(String adcode, String lo
            , String hi) throws ParseException {
        return weatherService.getWeatherInRange(adcode, simpleDateFormat.parse(lo)
                , new Date(simpleDateFormat.parse(hi).getTime() + 24 * 3600 * 1000L));
    }

    @ApiOperation("分页版获取天气")
    @GetMapping("/list")
    public Object getWeatherList(String adcode, String lo, String hi, int page, int limit) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Page<Object> pageObject = PageHelper.startPage(page, limit);
//        if(lo.equals("null")){
//            lo="";
//        }
//        if(hi.equals("null")){
//            hi="";
//        }
        System.out.println(lo);
        if(lo!="" && lo!=null) {
            lo = lo + " 00:00:00";
        }
        if(hi!="" && hi!=null) {
            hi = hi + " 23:59:59";
        }
        List<Weather> weatherList = weatherService.getWeatherList(adcode, lo, hi);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(weatherList);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return Result.ok(pageWrapper);
    }

    @ApiOperation("逐小时天气")
    @GetMapping("/hourly/list")
    public Object getHourlyWeatherList(String adcode,String lo, String hi, int page, int limit) {
        Page<Object> pageObject = PageHelper.startPage(page, limit);

        if(lo!="" && lo!=null) {
            lo = lo + " 00:00:00";
        }
        if(hi!="" && hi!=null) {
            hi = hi + " 23:59:59";
        }
        List<HourlyWeather> weatherList = weatherService.getHourlyWeatherList(adcode, lo, hi);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(weatherList);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return Result.ok(pageWrapper);
    }

    @ApiOperation("逐月天气")
    @GetMapping("/monthly/list")
    public Object getMonthlyWeatherList(String adcode, String lo, String hi, int page, int limit) {
        Page<Object> pageObject = PageHelper.startPage(page, limit);

        if(lo!="" && lo!=null) {
            lo = lo + " 00:00:00";
        }
        if(hi!="" && hi!=null) {
            hi = hi + " 23:59:59";
        }
        List<Weather> weatherList = weatherService.getMonthlyWeatherList(adcode, lo, hi);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(weatherList);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setTotalPage(pageObject.getPages());
        return Result.ok(pageWrapper);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response, String token,
                       @RequestParam(required = false) String adcode,
                       @RequestParam(required = false) String lo, @RequestParam(required = false) String hi) throws IOException {
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");
        String username = jwtComponent.verify(token);
        User user = userService.getUserByUserName(username);
        if(lo.equals("null")){
            lo=null;
        }
        if(hi.equals("null")){
            hi=null;
        }
        if(lo!="" && lo!=null) {
            lo = lo + " 00:00:00";
        }
        if(hi!="" && hi!=null) {
            hi = hi + " 23:59:59";
        }
        Workbook workbook = weatherService.exportWeather(adcode, lo, hi);
        workbook.write(response.getOutputStream());
    }

    @GetMapping("/export/hourly")
    public void exportHourly(HttpServletResponse response, String token,
                             @RequestParam(required = false) String adcode,
                             @RequestParam(required = false) String lo,
                             @RequestParam(required = false) String hi) throws IOException {
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");
        String username = jwtComponent.verify(token);
        User user = userService.getUserByUserName(username);
        if(lo.equals("null")){
            lo=null;
        }
        if(hi.equals("null")){
            hi=null;
        }
        if(lo!="" && lo!=null) {
            lo = lo + " 00:00:00";
        }
        if(hi!="" && hi!=null) {
            hi = hi + " 23:59:59";
        }
        Workbook workbook = weatherService.exportHourlyWeather(adcode, lo, hi);
        workbook.write(response.getOutputStream());
    }
    @GetMapping("/export/monthly")
    public void exportmonthly(HttpServletResponse response, String token,
                             @RequestParam(required = false) String adcode,
                             @RequestParam(required = false) String lo, @RequestParam(required = false) String hi) throws IOException {
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");
        String username = jwtComponent.verify(token);
        User user = userService.getUserByUserName(username);
        if(lo.equals("null")){
            lo=null;
        }
        if(hi.equals("null")){
            hi=null;
        }
        if(lo!="" && lo!=null) {
            lo = lo + " 00:00:00";
        }
        if(hi!="" && hi!=null) {
            hi = hi + " 23:59:59";
        }
        Workbook workbook = weatherService.exportMonthlyWeather(adcode, lo, hi);
        workbook.write(response.getOutputStream());
    }
}
