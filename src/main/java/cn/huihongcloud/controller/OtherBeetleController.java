package cn.huihongcloud.controller;

import cn.huihongcloud.entity.beetle.BeetleInfo;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.OtherBeetleService;
import cn.huihongcloud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 钟晖宏 on 2019/1/16
 */
@RestController
@RequestMapping("/auth_api")
public class OtherBeetleController {

    @Autowired
    private OtherBeetleService otherBeetleService;

    @Autowired
    private UserService userService;

    // 这个就不分页了
    @ApiOperation("查看其他天牛列表")
    @GetMapping("/other_beetle")
    public Object getOtherBeetleList() {
        return Result.ok(otherBeetleService.getOtherBeetleInfoList());
    }

    @ApiOperation("")
    @PutMapping("/other_beetle")
    public Object editOtherBeetleInfo(BeetleInfo beetleInfo) {
        otherBeetleService.updateOtherBeetleInfo(beetleInfo);
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/other_beetle")
    public Object deleteOtherBeetleInfo(int id) {
        otherBeetleService.deleteOtherBeetleInfo(id);
        return Result.ok();
    }

    @PostMapping("/other_beetle")
    public Object addOtherBeetleInfo(String name) {
        otherBeetleService.addOtherBeetleInfo(name);
        return Result.ok();
    }

    @ApiOperation("")
    @GetMapping("/other_beetle/town")
    public Object getOtherBeetleListForTown(@RequestAttribute("username") String username) {
        User user = userService.getUserByUserName(username);
        System.out.println("fortown");
        return Result.ok(otherBeetleService.getOtherBeetleInfoListForTown(user.getAdcode()));
    }

    @ApiOperation("")
    @PostMapping("/other_beetle/town")
    public Object addOtherBeetleForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleService.addOtherBeetleForTown(user.getAdcode(), beetleInfoId);
        return Result.ok();
    }

    @ApiOperation("")
    @DeleteMapping("/other_beetle/town")
    public Object deleteOtherBeetleForTown(@RequestAttribute("username") String username, Integer beetleInfoId) {
        User user = userService.getUserByUserName(username);
        otherBeetleService.deleteOtherBeetleInfoForTown(user.getAdcode(), beetleInfoId);
        return Result.ok();
    }

}
