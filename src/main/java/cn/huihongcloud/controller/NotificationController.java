package cn.huihongcloud.controller;

import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.notification.Notification;
import cn.huihongcloud.entity.notification.NotificationReq;
import cn.huihongcloud.entity.page.PageWrapper;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.NotificationService;
import cn.huihongcloud.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/10/2
 */
@RequestMapping("/auth_api/notification")
@RestController
@Api("通知")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Object sendNotificationToUpLevel(@RequestAttribute String username,
                                            @RequestBody NotificationReq notificationReq) {
        notificationService.sendNotification(username, notificationReq);
        return Result.ok();
    }

    @GetMapping
    public Object getNotifications(@RequestAttribute String username, int limit, int page) {
        User user = userService.getUserByUserName(username);
        Page<Object> pageObject = PageHelper.startPage(page, limit);
        List<Notification> notifications = notificationService.getNotificationByUser(user);
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setTotalPage(pageObject.getPages());
        pageWrapper.setTotalNum(pageObject.getTotal());
        pageWrapper.setCurrentPage(page);
        pageWrapper.setData(notifications);
        return Result.ok(pageWrapper);
    }
}
