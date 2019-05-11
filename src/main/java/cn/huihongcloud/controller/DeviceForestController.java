package cn.huihongcloud.controller;

import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.DeviceForest;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.service.DeviceForestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/7/20
 */

@RestController
@Api("DeviceForestController API")
public class DeviceForestController {

    @Autowired
    private DeviceForestService deviceForestService;

    @RequestMapping(value = "/auth_api/device_forest", method = RequestMethod.POST)
    @ApiOperation("增加数据")
    public Result addData(@RequestBody DeviceForest deviceForest) {
        if (deviceForestService.addData(deviceForest))
            return new Result.Ok();
        else
            return new Result.Failed();
    }

    @RequestMapping(value = "/auth_api/device_forest", method = RequestMethod.DELETE)
    @ApiOperation("删除数据")
    public Result deleteData(int id) {
        if (deviceForestService.deleteData(id)) {
            return new Result.Ok();
        } else {
            return new Result.Failed();
        }
    }

    @RequestMapping(value = "/auth_api/device_forest", method = RequestMethod.PUT)
    @ApiOperation("更新数据")
    public Result updateData(@RequestBody DeviceForest deviceForest) {
        if (deviceForestService.updateData(deviceForest)) {
            return new Result.Ok();
        } else {
            return new Result.Failed();
        }
    }

    @RequestMapping(value = "/auth_api/device_forest", method = RequestMethod.GET)
    @ApiOperation("获取数据")
    public PageWrapper getData(String id) {
        List<DeviceForest> deviceBeetles = deviceForestService.selectByDeviceId(id);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(deviceBeetles);
        return pageWrapper;
    }

}
