package cn.huihongcloud.service;

import cn.huihongcloud.entity.notification.Notification;
import cn.huihongcloud.entity.notification.NotificationReq;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.NotificationMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/10/2
 */
@Service
public class NotificationService {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationMapper notificationMapper;

    public List<Notification> getNotificationByUser(User user) {
        QueryWrapper<Notification> qw = new QueryWrapper<>();
        String adcode = user.getAdcode();
        String town = user.getTown();
        if (adcode != null && !adcode.isEmpty()) {
            qw.eq("target_adcode", adcode);
        }
        if (adcode == null || adcode.isEmpty()) {
            qw.isNull("target_adcode");
        }
        if (town != null && !town.isEmpty()) {
            qw.eq("target_town", town);
        }
        if (town == null || town.isEmpty()) {
            qw.isNull("target_town");
        }
        qw.orderByDesc("date");
        List<Notification> notifications = notificationMapper.selectList(qw);
        return notifications;
    }

    public void sendNotification(String username, NotificationReq notificationReq) {
        User user = userService.getUserByUserName(username);
        String targetAdcode = user.getAdcode();
        if (user.getTown() == null || user.getTown().isEmpty())
            targetAdcode = user.getAdcode().substring(0, user.getAdcode().length() - 2);
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationReq, notification);
        notification.setTargetAdcode(targetAdcode);
        notification.setDate(new Date());
        notification.setProvince(user.getProvince());
        notification.setCity(user.getCity());
        notification.setArea(user.getArea());
        notification.setTown(user.getTown());
        notification.setSourceUsername(username);
        notificationMapper.insert(notification);
    }
}
