package cn.huihongcloud.entity.weather;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2018/6/25
 */
@Data
public class WeatherOutput {

    @Excel(name = "最高温")
    private int tempMax;
    @Excel(name = "最低温")
    private int tempMin;
    @Excel(name = "湿度")
    private int hum;
    @Excel(name = "平均温")
    private int tempMean;
    @Excel(name = "时间", format = "yyyy-MM-dd")
    private Date date;

}
