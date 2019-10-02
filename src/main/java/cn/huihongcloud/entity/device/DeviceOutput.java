package cn.huihongcloud.entity.device;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class DeviceOutput {
    @Excel(name = "设备id")
    private String id;
    @Excel(name = "省")
    private String province;
    @Excel(name = "市")
    private String city;
    @Excel(name = "县")
    private String area;
    @Excel(name = "管理员")
    private String manager;
}
