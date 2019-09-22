package cn.huihongcloud.entity.notification;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2018/10/2
 */
@Data
@TableName("notification")
public class Notification {

    @TableId
    private int id;
    private String title;
    private String content;
    @TableField("target_adcode")
    private String targetAdcode;
    @TableField("target_town")
    private String targetTown;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日 HH:mm:ss")
    @TableField("`date`")
    private Date date;
    @TableField("source_username")
    private String sourceUsername;
    private String province;
    private String city;
    private String area;
    private String town;

}
