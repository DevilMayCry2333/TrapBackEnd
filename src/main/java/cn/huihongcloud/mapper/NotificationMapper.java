package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.notification.Notification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/10/2
 */
public interface NotificationMapper extends BaseMapper<Notification> {

    List<Notification> getNotificationByAdcodeAndTown(@Param("adcode") String adcode, @Param("town") String town);

    List<Notification> getNotificationByUsername(String username);

}
