package cn.huihongcloud.entity.device;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class DeviceOutputManager {
    @Excel(name = "设备id")
    private String scanId;
    @Excel(name = "省")
    private String province;
    @Excel(name = "市")
    private String city;
    @Excel(name = "县")
    private String area;
    @Excel(name = "工人")
    private String worker;
}
