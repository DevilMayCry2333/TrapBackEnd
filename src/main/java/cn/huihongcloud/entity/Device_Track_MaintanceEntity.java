package cn.huihongcloud.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data
public class Device_Track_MaintanceEntity {
    @Excel(name = "记录ID")
    private Long id;
    @Excel(name = "线路名称")
    private String linename;
    private String timeconsume;
    @Excel(name = "开始时间")
    private String starttime;
    @Excel(name = "结束时间")
    private String endtime;
    private String LatitudeCollect;
    private String LongtitudeCollect;
    private String AltitudeCollect;
    @Excel(name = "开始点")
    private String startpoint;
    @Excel(name = "结束点")
    private String endpoint;
    @Excel(name = "照片1")
    private String pic1;
    private String pic2;
    private String pic3;
    private String pic4;
    private String pic5;
    @Excel(name = "工人")
    private String worker;
    private String workingContent;
    private String remarks;
    private Long scanid;
    private Long deviceId;
    @Excel(name = "用户名")
    private String username;
    private String adcode;
    private String submit_date;

    private Boolean checked;


    public Device_Track_MaintanceEntity(String LatitudeCollect,String LongtitudeCollect,String AltitudeCollect,Long id, String linename, String timeconsume, String starttime, String endtime,String startpoint, String endpoint, String pic1, String pic2, String pic3, String pic4, String pic5, String worker, String workingContent, String remarks, Long scanid, Long deviceId, String username,String submit_date) {
        this.id = id;
        this.linename = linename;
        this.timeconsume = timeconsume;
        this.starttime = starttime;
        this.endtime = endtime;
        this.startpoint = startpoint;
        this.endpoint = endpoint;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.pic4 = pic4;
        this.pic5 = pic5;
        this.worker = worker;
        this.workingContent = workingContent;
        this.remarks = remarks;
        this.scanid = scanid;
        this.deviceId = deviceId;
        this.username = username;
        this.submit_date =submit_date;
        this.LatitudeCollect = LatitudeCollect;
        this.LongtitudeCollect = LongtitudeCollect;
        this.AltitudeCollect = AltitudeCollect;

    }

    public Device_Track_MaintanceEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename == null ? null : linename.trim();
    }

    public String getTimeconsume() {
        return timeconsume;
    }

    public void setTimeconsume(String timeconsume) {
        this.timeconsume = timeconsume == null ? null : timeconsume.trim();
    }

    public String getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(String startpoint) {
        this.startpoint = startpoint == null ? null : startpoint.trim();
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint == null ? null : endpoint.trim();
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1 == null ? null : pic1.trim();
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2 == null ? null : pic2.trim();
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3 == null ? null : pic3.trim();
    }

    public String getPic4() {
        return pic4;
    }

    public void setPic4(String pic4) {
        this.pic4 = pic4 == null ? null : pic4.trim();
    }

    public String getPic5() {
        return pic5;
    }

    public void setPic5(String pic5) {
        this.pic5 = pic5 == null ? null : pic5.trim();
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker == null ? null : worker.trim();
    }

    public String getWorkingContent() {
        return workingContent;
    }

    public void setWorkingContent(String workingContent) {
        this.workingContent = workingContent == null ? null : workingContent.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Long getScanid() {
        return scanid;
    }

    public void setScanid(Long scanid) {
        this.scanid = scanid;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getLatitudeCollect() {
        return LatitudeCollect;
    }

    public void setLatitudeCollect(String latitudeCollect) {
        LatitudeCollect = latitudeCollect;
    }

    public String getLongtitudeCollect() {
        return LongtitudeCollect;
    }

    public void setLongtitudeCollect(String longtitudeCollect) {
        LongtitudeCollect = longtitudeCollect;
    }

    public String getAltitudeCollect() {
        return AltitudeCollect;
    }

    public void setAltitudeCollect(String altitudeCollect) {
        AltitudeCollect = altitudeCollect;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}