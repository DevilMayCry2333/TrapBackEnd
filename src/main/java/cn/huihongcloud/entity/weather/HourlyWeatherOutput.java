package cn.huihongcloud.entity.weather;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2019/2/9
 */
@Data
public class HourlyWeatherOutput {

    @Excel(name = "体感温度")
    private int fl;
    @Excel(name = "温度")
    private int tmp;
    @Excel(name = "天气代码")
    private String condCode;
    @Excel(name = "天气")
    private String condTxt;
    @Excel(name = "风向(角度)")
    private int windDeg;
    @Excel(name = "风向")
    private String windDir;
    @Excel(name = "风力")
    private int windSc;
    @Excel(name = "风速")
    private int windSpd;
    @Excel(name = "湿度")
    private int hum;
    @Excel(name = "降水量")
    private Double pcpn;
    @Excel(name = "气压")
    private int pres;
    @Excel(name = "能见度")
    private int vis;
    @Excel(name = "云量")
    private int cloud;
    @Excel(name = "时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date date;

}
