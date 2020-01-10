package cn.huihongcloud.controller;

import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.UserMapper;
import cn.huihongcloud.service.UserService;
import cn.huihongcloud.util.DistUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/5/8
 */

@RestController
@Api("UserController API")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DistUtil distUtil;

    @Autowired
    UserMapper userMapper;

    @RequestMapping(path = "auth_api/user", method = RequestMethod.PUT)
    @ApiOperation("更新用户信息")
    public Result updateUser(@RequestAttribute("username") String username, @RequestBody User user,
                             HttpServletResponse response) {
        /*
        //获取当前用户
        User currentUser = userService.getUserByUserName(username);
        //获取要更新的用户
        User userToUpdate = userService.getUserByUserName(user.getUsername());
        // 工人与业主只能在乡镇
        if (user.getRole() >= 2) {
            if (userToUpdate.getTown() == null) {
                return new Result.Failed();
            }
        }



        //验证是否有权限修改，如果是更改自身也放行
        if (!userService.verifyUser(currentUser, userToUpdate) && !user.getUsername().equals(username)) {
            return new Result.PermissionDenied();
        }
        //禁止更改自身的一些属性
        if (user.getUsername().equals(username)) {
            user.setAdcode(null);
            user.setActive(null);
            user.setRole(null);
            user.setProvince(null);
            user.setCity(null);
            user.setArea(null);
            user.setTown(null);
            return new Result.PermissionDenied();
        }
        //禁止将等级低的用户级别修改为与当前同级或更高级
        if (user.getRole() != null && currentUser.getRole() >= user.getRole()) {
            user.setRole(null);
            return new Result.PermissionDenied();
        }
        // 如果更改了adcode 需要写入行政区文字信息
        if (user.getAdcode() != null || user.getTowncode() != null || user.getTown() != null) {
            String adcode = user.getAdcode();
            String[] dist = distUtil.getNames(adcode, user.getTowncode());
            user.setProvince(dist[0]);
            user.setCity(dist[1]);
            user.setArea(dist[2]);
            if (dist[3] != null)
                user.setTown(dist[3]);

            //限制将下属用户改为其他区
            if (!distUtil.underDist(currentUser, user)) {
                user.setAdcode(null);
                user.setProvince(null);
                user.setCity(null);
                user.setArea(null);
                user.setTown(null);
                return new Result.PermissionDenied();
            }
        }
        if (!userService.updateUser(user)) {
            return new Result.Failed();
        }
        */


        /*
        只有超级管理员 县级用户 管理员能修改用户
        如果是超级管理员，允许修改所有用户
        县级用户只能修改管理员
        管理员只能修改工人
         */

        User currentUser = userService.getUserByUserName(username);
        User userToUpdate = userService.getUserByUserName(user.getUsername());
        System.out.printf("====================");
        System.out.printf(userToUpdate.getName());
        if (currentUser.getRole() != 0 && currentUser.getRole() != 3 && currentUser.getRole() != 4) {
            return new Result.PermissionDenied();
        }

        if (currentUser.getRole() == 3 && userToUpdate.getRole() != 4) {
            return new Result.PermissionDenied();
        }

        if (currentUser.getRole() == 4 && userToUpdate.getRole() != 5) {
            return new Result.PermissionDenied();
        }


        /*
        现在不允许修改地区
         */

        user.setAdcode(null);
        user.setRole(null);
        user.setProvince(null);
        user.setCity(null);
        user.setArea(null);
        user.setTown(null);

        if (!userService.updateUser(user)) {
            return new Result.Failed();
        }

        return new Result.Ok();
    }

    @RequestMapping(path = "auth_api/user_list", method = RequestMethod.GET)
    @ApiOperation("获取用户列表")
    public PageWrapper getUser(@RequestAttribute("username") String username,
                               @RequestParam(value = "searchText", required = false) String searchText,
                               @RequestParam("page") int page, @RequestParam("limit") int limit,
                               @RequestParam(required = false) Integer roleType,
                               @RequestParam(required = false) Boolean active) {
        User currentUser = userService.getUserByUserName(username);
        Page<Object> pageObjects = PageHelper.startPage(page, limit);

        System.out.println(currentUser.getRole());

        /*
         1. 超级管理员可以看到所有的用户
         2. 县级用户看到管理员
         3. 管理员看到工人
         */

        String condition = null;
        int flag = 0;
        List<User> users = null;
        // 这里就暂时用拼接sql处理了
        switch (currentUser.getRole()) {
            case 3:
                condition = " role = 4 or role = 6 and parent like '%" +  username +  "%'" ;
                System.out.println(condition);
                break;
            case 4:
                condition = " role = 5 and parent = '" + username + "'";
                break;
            case 6:
                users = userMapper.getProjectAdminByAdcode(currentUser.getAdcode());
                System.out.println("6666");
                flag = 1;
                break;
            case 7:
                System.out.println("roleType=7");
                users = userMapper.getProjectUsersByAdcode(currentUser.getAdcode());
                flag = 1;
                break;

        }

        if (flag == 0) {
            users = userService.getUserByAdcodeAndTownAndStringAndUserRole(currentUser.getAdcode(), currentUser.getTown(),
                    searchText.trim(), currentUser.getRole(), roleType, active, condition);
        }

        for (User user : users) {
            user.setPassword(null);
        }
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(users);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalPage(pageObjects.getPages());
        pageWrapper.setTotalNum(pageObjects.getTotal());
        return pageWrapper;
    }

    @RequestMapping(path = "auth_api/projects", method = RequestMethod.GET)
//    @ApiOperation("获取工程项目")
    public List<User> getProjects(@RequestParam("adcode") String adcode) {
        List<User> projects = userService.getProjectsByAdCode(adcode);
        /*
         * todo
         * */
        return projects;
    }

    @RequestMapping(path = "auth_api/user/provinceName", method = RequestMethod.GET)
    @ApiOperation("获取用户列表")
    public Object getUserProvinceName(@RequestParam String adcode, HttpServletResponse response) {

        if (distUtil.getNames(adcode, null) == null) {
            return Result.failed();
        }
        String[] Dist = distUtil.getNames(adcode, null);

        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(Dist);

        return pageWrapper;
    }

    @RequestMapping(path = "auth_api/user_forbid", method = RequestMethod.PUT)
    @ApiOperation("禁用用户信息")
    public Result forbidUser(@RequestAttribute("username") String username,
                             @RequestParam String targetUser, HttpServletResponse response) {
        User currentUser = userService.getUserByUserName(username);
        User userToForbid = userService.getUserByUserName(targetUser);

        if (currentUser.getRole() == 5) {
            return new Result.PermissionDenied();
        }

        if (userToForbid.getRole() <= currentUser.getRole()) {
            return new Result.Failed();
        }


        if (currentUser.getRole() < 4) {
            //获取同地区用户列表
            List<User> users = userService.listUserByArea(userToForbid.getProvince(), userToForbid.getCity(), userToForbid.getArea());
            //禁用县级用户@Param("adcode") String adcode,@Param("area") String area
            // adcode = #{adcode} and and town = #{town}
            //userToForbid.getAdcode(),userToForbid.getArea()
            for (User user : users) {
                Integer roleType = user.getRole();
                Boolean active = user.getActive();
                if (userToForbid.getRole() <= roleType) {
                    if (!userService.forbitToUse(user)) {
                        return new Result.Failed();

                    }
                    if (user.getRole() == 5) {
                        userService.nonActiveDevice(user.getUsername());
                    }
                }
            }
            if (!userService.forbitToUse(userToForbid)) {
                return new Result.Failed();
            }
        } else {
            if (!userService.forbitToUse(userToForbid)) {
                return new Result.Failed();

            }
            userService.nonActiveDevice(userToForbid.getUsername());
        }


        return new Result.Ok();

    }

    @RequestMapping(path = "auth_api/user_list_active", method = RequestMethod.GET)
    @ApiOperation("获取用户列表")
    public PageWrapper getUserActive(@RequestAttribute("username") String username,
                                     @RequestParam(value = "searchText", required = false) String searchText,
                                     @RequestParam("page") int page, @RequestParam("limit") int limit,
                                     @RequestParam(required = false) Integer roleType,
                                     @RequestParam(required = false) Boolean active, HttpServletResponse response) {
        User currentUser = userService.getUserByUserName(username);
        Page<Object> pageObjects = PageHelper.startPage(page, limit);
        /*
         1. 超级管理员可以看到所有的用户
         2. 县级用户看到管理员
         3. 管理员看到工人
         */

        String condition = null;
        Boolean myactive = true;
        // 这里就暂时用拼接sql处理了
        switch (currentUser.getRole()) {
            case 3:
                condition = " role = 4 ";
                break;
            case 4:
                condition = " role = 5 and parent = '" + username + "'";
        }

        List<User> users = userService.getUserByAdcodeAndTownAndStringAndUserRole(currentUser.getAdcode(), currentUser.getTown(),
                searchText.trim(), currentUser.getRole(), roleType, myactive, condition);
        for (User user : users) {
            user.setPassword(null);
        }
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(users);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalPage(pageObjects.getPages());
        pageWrapper.setTotalNum(pageObjects.getTotal());
        return pageWrapper;
    }

    @GetMapping("auth_api/worker_in_region")
    public PageWrapper getUserInTown(String adcode, String town, int page, int limit) {
        Page<Object> pageObjects = PageHelper.startPage(page, limit);
        List<User> users = userService.getWorkerInRegion(adcode, town);
        for (User user : users) {
            user.setPassword(null);
        }
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(users);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalPage(pageObjects.getPages());
        pageWrapper.setTotalNum(pageObjects.getTotal());
        return pageWrapper;
    }

    @RequestMapping(path = "auth_api/user", method = RequestMethod.GET)
    @ApiOperation("获取用户")
    public User getUser(@RequestAttribute("username") String username) {
        User currentUser = userService.getUserByUserName(username);
        currentUser.setPassword(null);
        return currentUser;
    }



    @RequestMapping(path = "auth_api/user", method = RequestMethod.POST)
    @ApiOperation("增加用户")
    public Result addUser(@RequestAttribute("username") String username, @RequestBody User user,
                          HttpServletResponse response) {

        /*
        // 工人与业主只能在乡镇
        if (user.getRole() >= 2) {
            if (user.getTown() == null) {
                return Result.failed();
            }
        }
        // 获取当前用户
        User currentUser = userService.getUserByUserName(username);
        if (!userService.verifyUser(currentUser, user)) {
            return new Result.PermissionDenied();
        }
        // 为用户添加文字行政区
        String[] Dist = distUtil.getNames(user.getAdcode(), user.getTowncode());
        user.setProvince(Dist[0]);
        user.setCity(Dist[1]);
        user.setArea(Dist[2]);
//        user.setTown(Dist[3]);
        if (!userService.registerUser(user)) {
            return Result.failed();
        }
        */

        System.out.println("===田鸡用户==");

        System.out.println(user.getAdcode());
        System.out.println(user.getUsername());
        String[] Dist = distUtil.getNames(user.getAdcode(), user.getTowncode());
        user.setProvince(Dist[0]);
        user.setCity(Dist[1]);
        user.setArea(Dist[2]);

        /*
            如果是工人，要写入parent信息
         */
        if (user.getRole() == 5) {
            user.setParent(username);
        } else if (user.getRole() == 4) { //如果是项目管理员，通过parent与项目工程对应
            User project = userService.getUserByUserName(user.getParent());
            project.setParent(user.getUsername());
            if (!userService.updateUser(project)) {
                return Result.failed();
            }
        }

        System.out.println(user);
        if (userService.getUserByUserName(user.getUsername()) != null) {
            return Result.failed("用户名已存在");
        }

        userService.registerUser(user);
        if (user.getRole() < 5) {
            List<User> users = userService.listUserByArea(user.getProvince(), user.getCity(), user.getArea());
            //禁用县级用户@Param("adcode") String adcode,@Param("area") String area
            // adcode = #{adcode} and and town = #{town}
            //userToForbid.getAdcode(),userToForbid.getArea()
            for (User myuser : users) {
                Integer roleType = myuser.getRole();
                Boolean active = myuser.getActive();
                if (user.getRole() == 5) {
                    userService.ActiveDevice(user.getUsername());
                }
            }
        }

        return Result.ok();
    }



//    @RequestMapping(path = "auth_api/user", method = RequestMethod.PUT)
//    @ApiOperation("修改用户")
//    public Result updateUser1(@RequestAttribute("username") String username, @RequestBody User user,
//                          HttpServletResponse response) {
//
//        /*
//        // 工人与业主只能在乡镇
//        if (user.getRole() >= 2) {
//            if (user.getTown() == null) {
//                return Result.failed();
//            }
//        }
//        // 获取当前用户
//        User currentUser = userService.getUserByUserName(username);
//        if (!userService.verifyUser(currentUser, user)) {
//            return new Result.PermissionDenied();
//        }
//        // 为用户添加文字行政区
//        String[] Dist = distUtil.getNames(user.getAdcode(), user.getTowncode());
//        user.setProvince(Dist[0]);
//        user.setCity(Dist[1]);
//        user.setArea(Dist[2]);
////        user.setTown(Dist[3]);
//        if (!userService.registerUser(user)) {
//            return Result.failed();
//        }
//        */
//
//        System.out.println("===修改用户==");
//        System.out.println(user.getAdcode());
//        System.out.println(user.getUsername());
//        String[] Dist = distUtil.getNames(user.getAdcode(), user.getTowncode());
//        user.setProvince(Dist[0]);
//        user.setCity(Dist[1]);
//        user.setArea(Dist[2]);
//
//        /*
//            如果是工人，要写入parent信息
//         */
//        if (user.getRole() == 5) {
//            user.setParent(username);
//        } else if (user.getRole() == 4) { //如果是项目管理员，通过parent与项目工程对应
//            User project = userService.getUserByUserName(user.getParent());
//            project.setParent(user.getUsername());
//            if (!userService.updateUser(project)) {
//                return Result.failed();
//            }
//        }
//
//        System.out.println(user);
//        if (userService.getUserByUserName(user.getUsername()) != null) {
//            return Result.failed("用户名已存在");
//        }
//
//        userService.registerUser(user);
//        if (user.getRole() < 5) {
//            List<User> users = userService.listUserByArea(user.getProvince(), user.getCity(), user.getArea());
//            //禁用县级用户@Param("adcode") String adcode,@Param("area") String area
//            // adcode = #{adcode} and and town = #{town}
//            //userToForbid.getAdcode(),userToForbid.getArea()
//            for (User myuser : users) {
//                Integer roleType = myuser.getRole();
//                Boolean active = myuser.getActive();
//                if (user.getRole() == 5) {
//                    userService.ActiveDevice(user.getUsername());
//                }
//            }
//        }
//
//        return Result.ok();
//    }

    @RequestMapping(path = "auth_api/user", method = RequestMethod.DELETE)
    @ApiOperation("删除用户")
    public Result deleteUserByUsername(@RequestAttribute("username") String currentUsername,
                                       @RequestParam(value = "username") String username, HttpServletResponse response) {
        User currentUser = userService.getUserByUserName(currentUsername);
        User userToDelete = userService.getUserByUserName(username);
        if (!userService.verifyUser(currentUser, userToDelete)) {
            return new Result.PermissionDenied();
        }
        if (!userService.deleteUserByUsername(username)) {
            return new Result.Failed();
        }
        return new Result.Ok();
    }

    @PostMapping("auth_api/user/reset_password")
    @ApiOperation("重置密码")
    public Result resetPassword(@RequestAttribute("username") String username,
                                @RequestParam String targetUsername) {
        // todo 验证权限
        userService.resetPassword(targetUsername);
        return new Result.Ok();
    }

    @PutMapping("auth_api/user/password")
    @ApiOperation("修改密码")
    public Result changePassword(@RequestAttribute("username") String username,
                                 @RequestParam String currentPassword,
                                 @RequestParam String newPassword) {
        try {
            userService.changePassword(username, currentPassword, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Failed();
        }
        return new Result.Ok();
    }

    @PutMapping("auth_api/user/nameAndPhone")
    public Result changeNameAndPhone(@RequestParam("username") String username,
                                 @RequestParam("name") String name,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("active") Boolean active
                                     ) {
        try {
            userMapper.updateUserNameAndPhone(username,name,phone,active);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result.Failed();
        }
        return new Result.Ok();
    }
    @GetMapping("auth_api/user_associated")
    @ApiOperation("获取已关联用户")
    public Object getAssociatedUser(String deviceId, int page, int limit) {
        Page<Object> pageObjects = PageHelper.startPage(page, limit);
        List<User> users = userService.getCurrentAssociatedUser(deviceId);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(users);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalPage(pageObjects.getPages());
        pageWrapper.setTotalNum(pageObjects.getTotal());
        return pageWrapper;
    }

    @GetMapping("auth_api/user_can_associate")
    @ApiOperation("获取可关联用户")
    public Object getCanAssociateUser(String deviceId, int page, int limit) {
        Page<Object> pageObjects = PageHelper.startPage(page, limit);
        List<User> users = userService.getCanAssociatedUser(deviceId);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setData(users);
        pageWrapper.setCurrentPage(page);
        pageWrapper.setTotalPage(pageObjects.getPages());
        pageWrapper.setTotalNum(pageObjects.getTotal());
        return pageWrapper;
    }

    // 单独为数据分析那开的管理员接口
    @GetMapping("auth_api/manager_list")
    public Object getManagerList(@RequestAttribute("username") String username, String adcode) {
        User user = userService.getUserByUserName(username);
        if (StringUtils.isEmpty(adcode)) {
            adcode = user.getAdcode();
        }
        List<User> managers = userService.getUserByAdcodeAndTownAndStringAndUserRole(adcode, null, "", user.getRole(), 4, null, null);
        return Result.ok(managers);
    }

    @RequestMapping("system/getCustomProject")
    public Object getCustomProject(@RequestAttribute("username") String username, @RequestParam String adcode){
        List<User> userList = userMapper.getProjectAdminByAdcode(adcode);
        return Result.ok(userList);
    }

    @RequestMapping("system/dataReport")
    public Object dataReport(@RequestParam String project,@RequestParam String customProject){
        if(project.equals("诱捕器管理")){
            userMapper.reportTrap(customProject);
        }else if(project.equals("注干剂监测")){
            userMapper.reportInject(customProject);
        }else if(project.equals("天敌防治")){
            userMapper.reportEnemy(customProject);
        }else if(project.equals("枯死树采伐")){
            userMapper.reportDead(customProject);
        }else if(project.equals("轨迹追踪")){
            User user = userMapper.getUserByUserName(customProject);
            System.out.println(user.getParent());
            userMapper.reportTrack(user.getParent());



        }
        return "OK";
    }

}
