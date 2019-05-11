package cn.huihongcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 钟晖宏 on 2018/5/6
 */
@Controller
public class TestAuthController {

    @RequestMapping(value = "auth_api/test")
    @ResponseBody
    public String test() {
        return "hello";
    }
}
