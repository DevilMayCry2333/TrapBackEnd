package cn.huihongcloud.entity;

import java.util.Date;

public class Device_Track_MaintanceEntity {
    private Long id;

    private String linename;

    private String timeconsume;

    private Date starttime;

    private Date endtime;

    private String pointcollect;

    private String startpoint;

    private String endpoint;

    private String pic1;

    private String pic2;

    private String pic3;

    private String pic4;

    private String pic5;

    private String worker;

    private String workingContent;

    private String remarks;

    private Long scanid;

    private Long deviceid;

    public Device_Track_MaintanceEntity(Long id, String linename, String timeconsume, Date starttime, Date endtime, String pointcollect, String startpoint, String endpoint, String pic1, String pic2, String pic3, String pic4, String pic5, String worker, String workingContent, String remarks, Long scanid, Long deviceid) {
        this.id = id;
        this.linename = linename;
        this.timeconsume = timeconsume;
        this.starttime = starttime;
        this.endtime = endtime;
        this.pointcollect = pointcollect;
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
        this.deviceid = deviceid;
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

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getPointcollect() {
        return pointcollect;
    }

    public void setPointcollect(String pointcollect) {
        this.pointcollect = pointcollect == null ? null : pointcollect.trim();
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

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }
}