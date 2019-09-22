package cn.huihongcloud.controller;

import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.DeviceBeetle;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.service.DeviceBeetleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/7/20
 */

@RestController
@Api("DeviceBeetleController API")
public class DeviceBeetleController {

    @Autowired
    private DeviceBeetleService deviceBeetleService;

    @RequestMapping(value = "/auth_api/device_beetle", method = RequestMethod.POST)
    @ApiOperation("增加数据")
    public Result addData(@RequestBody DeviceBeetle deviceBeetle) {
        if (deviceBeetleService.addData(deviceBeetle))
            return new Result.Ok();
        else
            return new Result.Failed();
    }

    @RequestMapping(value = "/auth_api/device_beetle", method = RequestMethod.DELETE)
    @ApiOperation("删除数据")
    public Result deleteData(int id) {
        if (deviceBeetleService.deleteData(id)) {
            return new Result.Ok();
        } else {
            return new Result.Failed();
        }
    }

    @RequestMapping(value = "/auth_api/device_beetle", method = RequestMethod.PUT)
    @ApiOperation("更新数据")
    public Result updateData(@RequestBody DeviceBeetle deviceBeetle) {
        if (deviceBeetleService.updateData(deviceBeetle)) {
            return new Result.Ok();
        } else {
            return new Result.Failed();
        }
    }

    @RequestMapping(value = "/auth_api/device_beetle", method = RequestMethod.GET)
    @ApiOperation("获取数据")
    public PageWrapper getData(String id, int page, int limit) {
        Page<Object> pageObjects = PageHelper.startPage(page, limit);
        List<DeviceBeetle> deviceBeetles = deviceBeetleService.selectByDeviceId(id);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(deviceBeetles);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalNum(pageObjects.getTotal());
        pageWrapper.setTotalPage(pageObjects.getPages());
        return pageWrapper;
    }

//    @RequestMapping(value = "/auth_api/device_beetle_statistics_by_town", method = RequestMethod.GET)
//    @ApiOperation("获取按乡的统计信息")
    public Object getStatisticsByTown(String adcode, String town, Boolean isChart
            , @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if (isChart) {
            return deviceBeetleService.getStatisticsByTown(adcode, town, isChart);
        }
        else {
            Page<Object> pageObject = PageHelper.startPage(page, limit);
            List<DeviceBeetle> statisticsByTown = deviceBeetleService.getStatisticsByTown(adcode, town, isChart);
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setTotalPage(pageObject.getPages());
            pageWrapper.setTotalNum(pageObject.getTotal());
            pageWrapper.setCurrentPage(page);
            pageWrapper.setData(statisticsByTown);
            return pageWrapper;
        }
    }

//    @RequestMapping(value = "/auth_api/device_beetle_statistics_by_area", method = RequestMethod.GET)
//    @ApiOperation("获取按县的统计信息")
    public Object getStatisticsByArea(String adcode, Boolean isChart
            , @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if (isChart) {
            return deviceBeetleService.getStatisticsByArea(adcode, isChart);
        } else {
            Page<Object> pageObject = PageHelper.startPage(page, limit);
            List<DeviceBeetle> statisticsByArea = deviceBeetleService.getStatisticsByArea(adcode, isChart);
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setTotalPage(pageObject.getPages());
            pageWrapper.setTotalNum(pageObject.getTotal());
            pageWrapper.setCurrentPage(page);
            pageWrapper.setData(statisticsByArea);
            return pageWrapper;
        }
    }

//    @RequestMapping(value = "/auth_api/device_beetle_statistics_by_city", method = RequestMethod.GET)
//    @ApiOperation("获取按市的统计信息")
    public Object getStatisticsByCity(String adcode, Boolean isChart
            , @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if (isChart) {
            return deviceBeetleService.getStatisticsLikeAdcode(adcode, isChart);
        } else {
            Page<Object> pageObject = PageHelper.startPage(page, limit);
            List<DeviceBeetle> statisticsByTown = deviceBeetleService.getStatisticsLikeAdcode(adcode, isChart);
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setTotalPage(pageObject.getPages());
            pageWrapper.setTotalNum(pageObject.getTotal());
            pageWrapper.setCurrentPage(page);
            pageWrapper.setData(statisticsByTown);
            return pageWrapper;
        }
    }

//    @RequestMapping(value = "/auth_api/device_beetle_statistics_by_pronvince", method = RequestMethod.GET)
//    @ApiOperation("获取按省的统计信息")
    public Object getStatisticsByProvince(String adcode, Boolean isChart
            , @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer page) {
        if (isChart) {
            return deviceBeetleService.getStatisticsLikeAdcode(adcode, isChart);
        } else {
            Page<Object> pageObject = PageHelper.startPage(page, limit);
            List<DeviceBeetle> statisticsByTown = deviceBeetleService.getStatisticsLikeAdcode(adcode, isChart);
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setTotalPage(pageObject.getPages());
            pageWrapper.setTotalNum(pageObject.getTotal());
            pageWrapper.setCurrentPage(page);
            pageWrapper.setData(statisticsByTown);
            return pageWrapper;
        }
    }
}
