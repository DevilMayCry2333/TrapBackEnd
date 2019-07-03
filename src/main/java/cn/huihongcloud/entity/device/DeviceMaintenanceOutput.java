package cn.huihongcloud.entity.device;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2019/1/19
 */
@Data
public class DeviceMaintenanceOutput {

    @Excel(name = "设备ID")
    private String deviceId;
    @Excel(name = "批次")
    private int batch;
    @Excel(name = "松墨天牛数")
    private Integer num;
    /*
    @Excel(name = "雄虫数")
    private Integer maleNum;
    @Excel(name = "雌虫数")
    private Integer femaleNum;
    */
    @Excel(name = "其它天牛类型")
    private String otherTypeString;
    @Excel(name = "其它天牛数量")
    private Integer otherNum;
    @Excel(name = "用户名")
    private String username;

  //  @Excel(name = "时间", format = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "时间", format = "yyyy-MM-dd")
    private Date date;
    @Excel(name = "经度")
    private Double longitude;
    @Excel(name = "纬度")
    private Double latitude;
    @Excel(name = "海拔")
    private Double altitude;
    private String imageId;
    @Excel(name = "药剂类型")
    private String drug;
    @Excel(name = "备注")
    private String remark;

    @Excel(name = "省")
    private String province;
    @Excel(name = "市")
    private String city;
    @Excel(name = "县")
    private String area;
    @Excel(name = "乡")
    private String town;
    @Excel(name = "施工内容")
    private String workingContentString;

}
