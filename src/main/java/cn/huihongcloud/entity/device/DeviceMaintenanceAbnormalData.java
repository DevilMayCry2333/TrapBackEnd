package cn.huihongcloud.entity.device;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2018/10/5
 */
@Data

public class DeviceMaintenanceAbnormalData {
    @Excel(name = "ID")
    private int id;
    @Excel(name = "设备ID")
    private String deviceId;
    @Excel(name = "批次")
    private int batch;
    @Excel(name = "虫子数")
    private Integer num;
    @Excel(name = "雄虫数")
    private Integer maleNum;
    @Excel(name = "雌虫数")
    private Integer femaleNum;
    @Excel(name = "用户名")
    private String username;
    private String name;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日 HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    @Excel(name = "日期")
    private Date date;
    @Excel(name = "经度")
    private Double longitude;
    @Excel(name = "纬度")
    private Double latitude;
    @Excel(name = "海拔")
    private Double altitude;
    //private String imageId;

    @Excel(name = "药剂类型")
    private String drug;
    @Excel(name = "备注")
    private String remark;

    private String province;
    private String city;
    private String area;
    private String town;
    private Integer workingContent;
    private Integer isactive;
    private Boolean reported;
    private Integer otherType;
    private Integer otherNum;
    @TableField("img_id")
    private String imageId;


}
