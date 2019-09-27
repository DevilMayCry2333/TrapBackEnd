package cn.huihongcloud.entity.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2018/5/30
 */
@Data
public class Device {

    private String id;
    private Double latitude;
    private Double longitude;
    private Double altitude;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date receiveDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日 HH:mm:ss")
    private Date checkDate;
    private String adcode;
    private String towncode;
    private String province;
    private String city;
    private String area;
    private String town;
    private String qrcode;
    private String manager;
    private String worker;
    private int isManagerAssign;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }


    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getTowncode() {
        return towncode;
    }

    public void setTowncode(String towncode) {
        this.towncode = towncode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getIsManagerAssign() {
        return isManagerAssign;
    }

    public void setIsManagerAssign(int isManagerAssign) {
        this.isManagerAssign = isManagerAssign;
    }
}
