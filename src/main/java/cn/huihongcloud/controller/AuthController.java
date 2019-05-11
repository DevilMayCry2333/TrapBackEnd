package cn.huihongcloud.controller;

import cn.huihongcloud.component.JWTComponent;
import cn.huihongcloud.entity.auth.AuthInfo;
import cn.huihongcloud.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 钟晖宏 on 2018/5/6
 */
@RestController
@Api("AuthController API")
public class AuthController {

    @Autowired
    private JWTComponent jwtComponent;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation("登录")
    public AuthInfo login(@RequestParam("username") String userName, @RequestParam("password") String password, HttpServletResponse response) {
        int n = userMapper.verifyUserByUserNameAndPassword(userName, password);
        if (n == 1) {
            AuthInfo authInfo = new AuthInfo();
            authInfo.setToken(jwtComponent.createToken(userName));
            return authInfo;
        }
        response.setStatus(401);
        return null;
    }

    /**
     * renew token
     *
     * @param response HttpServletResponse
     * @return new token
     */
    @RequestMapping(value = "auth_api/token", method = RequestMethod.GET)
    @ApiOperation("更新token")
    public AuthInfo renew(@RequestHeader("token") String token,HttpServletResponse response) {
        String userName = jwtComponent.verify(token);
        if (userName != null) {
            AuthInfo authInfo = new AuthInfo();
            authInfo.setToken(jwtComponent.createToken(userName));
            return authInfo;
        }
        response.setStatus(401);
        return null;
    }

    /**
     * auth
     *
     * @param token
     * @param response
     * @return auth result
     */
    @RequestMapping(value = "auth_api/token", method = RequestMethod.POST)
    @ApiOperation("验证token")
    public String auth(@RequestHeader("token") String token, HttpServletResponse response, HttpServletRequest request) {
        if (jwtComponent.verify(token) != null) {
            response.setStatus(200);
        } else {
            response.setStatus(401);
        }
        return "";
    }


}
