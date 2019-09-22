package cn.huihongcloud.controller;

import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 钟晖宏 on 2018/5/7
 */

@RestController
public class RegisterController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "register")
    public String register(HttpServletResponse httpServletResponse, @RequestParam("username") String username, @RequestParam("password") String password,
                           @RequestParam("name") String name, @RequestParam("province") String province,
                           @RequestParam("cities") String cities, @RequestParam("areas") String areas,
                           @RequestParam("streets") String streets, @RequestParam("email") String email,
                           @RequestParam("phone") String phone) {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        user.setAdcode(province + cities + areas);
        user.setTown(streets);
        user.setEmail(email);
        user.setPhone(phone);
        user.setActive(false);
        int n = userMapper.registerUser(user);
        if (n != 1) {
            httpServletResponse.setStatus(403);
        }
        return "";
    }
}
