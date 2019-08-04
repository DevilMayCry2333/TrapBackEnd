package cn.huihongcloud.controller;

import cn.huihongcloud.service.DictService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private DictService dictService;

    @GetMapping("version")
    @ApiOperation("获取app最新版本")
    public String getLatestVersion() {
        return dictService.getValueByKey("app_latest_version");
    }
}

