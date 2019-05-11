package cn.huihongcloud.entity.weather;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data
public class MonthlyWeatherOutput {
    @Excel(name = "最高温")
    private int tempMax;
    @Excel(name = "最低温")
    private int tempMin;
    @Excel(name = "平均温")
    private int tempMean;
    @Excel(name = "湿度")
    private int hum;
    @Excel(name = "时间", format = "yyyy-MM")
    private Date date;
    /*
    @Excel(name = "月份")
    private String yearmonth;
    */
}
