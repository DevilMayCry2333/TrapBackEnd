package cn.huihongcloud.entity.weather;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2019/2/9
 */
@Data
public class HourlyWeather {

    private int id;
    private String adcode;
    private int fl;
    private int tmp;
    private String condCode;
    private String condTxt;
    private int windDeg;
    private String windDir;
    private int windSc;
    private int windSpd;
    private int hum;
    private Double pcpn;
    private int pres;
    private int vis;
    private int cloud;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date date;
}
