package cn.huihongcloud.controller;

import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.device.DeviceBeetle;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.DeviceService;
import cn.huihongcloud.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/7/23
 */
@RestController
public class StatisticsMapController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserService userService;

    @RequestMapping("/auth_api/statistics_map/town_spots")
    @ApiOperation("获取统计地图乡镇标点")
    public List<Device> getSpots(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);
        List<Device> spotDevices = deviceService.getTownSpotDeviceByLocation(user.getAdcode(), user.getTown());
        return spotDevices;
    }

    @RequestMapping("/auth_api/statistics_map/area_spots")
    @ApiOperation("获取统计地图县标点")
    public List<Device> getAreaSpots(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);
        List<Device> spotDevice = deviceService.getAreaSpotDeviceByLocation(user.getAdcode());
        return spotDevice;
    }

    @RequestMapping("/auth_api/statistics_map/city_spots")
    @ApiOperation("获取统计地图市标点")
    public List<Device> getCitySpots(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);
        List<Device> spotDevice = deviceService.getCitySpotDeviceByLocation(user.getAdcode());
        return spotDevice;
    }

    @RequestMapping("/auth_api/statistics_map/province_spots")
    @ApiOperation("获取统计地图省标点")
    public List<Device> getProvinceSpots(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);
        List<Device> spotDevice = deviceService.getProvinceSpotDeviceByLocation(user.getAdcode());
        return spotDevice;
    }
}
