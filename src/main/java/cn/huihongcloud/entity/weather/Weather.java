package cn.huihongcloud.entity.weather;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Month;
import java.time.Year;
import java.util.Date;

/**
 * Created by 钟晖宏 on 2018/6/25
 */
public class Weather {

    @JsonIgnore
    private int id;
    private int tempMax;
    private int tempMin;
    private int hum;
    private int tempMean;
    private int min;
    private int max;

    private int mean;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date date;
    private String yearmonth;

    private Integer month;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy")
    private Year year;
    private String adcode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public int getTempMean() {
        return tempMean;
    }

    public void setTempMean(int tempMean) {
        this.tempMean = tempMean;
    }

    public int getMean() {
        return mean;
    }

    public void setMean(int mean) {
        this.mean = mean;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setYear(Year year) {
        this.year = year;
    }
    public Year getYear() {
        return year;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
    public Integer getMonth() {
        return month;
    }

    public String getYearMonth() {
        return yearmonth;
    }
    public void setYearMonth(Year year,Integer month) {
        this.yearmonth = year+"年"+month+"月";
    }
    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }
}
