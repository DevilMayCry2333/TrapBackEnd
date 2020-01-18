package cn.huihongcloud.controller;

import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.service.AnalysisService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 钟晖宏 on 2018/10/3
 */
@RestController
@RequestMapping("/auth_api/analysis")
@Api("数据分析")
public class AnalysisController {

    
    @Autowired
    private AnalysisService analysisService;

    public Object getSubDist(@RequestAttribute String username) {
        return null;
    }

    @GetMapping
    public Object getAnalysisData(String adcode, String town, int year) {
        return Result.ok(analysisService.getAnalysisData(adcode, town, year));
    }
}
