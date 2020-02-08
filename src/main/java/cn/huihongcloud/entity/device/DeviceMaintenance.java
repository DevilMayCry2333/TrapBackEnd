package cn.huihongcloud.entity.device;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.MonthDay;

import java.util.Date;

/**
 * Created by 钟晖宏 on 2018/9/24
 * <if test="myYear != null and myYear !=''">
 *             and `date` = DATE_FORMAT(#{myYear}, '%Y')
 *         </if>
 *         group by DATE_FORMAT(convert_tz(`date`, @@session.time_zone,'+08:00'), '%Y-%m')
 *         order by `date` asc
 */
@Data
public class DeviceMaintenance {

    @Excel(name = "ID")
    private int id;

    @Excel(name = "设备ID")
    private String scanId;
    @Excel(name = "编号")
    private String customSerial;
    @Excel(name = "区域")
    private String customTown;

    @Excel(name = "日期",exportFormat = "yyyy年MM月dd日",importFormat = "yyyy年MM月dd日")
    @JsonFormat(pattern = "yyyy年MM月dd日")
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    private Date date;


    private String deviceId;
    @Excel(name = "批次")
    private int batch;

    @Excel(name = "经度",numFormat = "000.000000")
    private Double longitude;
    @Excel(name = "纬度",numFormat = "00.000000")
    private Double latitude;
    @Excel(name = "海拔")
    private Double altitude;

    @Excel(name = "工作内容")
    private String workContentFront;



    @Excel(name = "天牛数量")
    private Integer num;



    @Excel(name = "其他天牛类型")
    private String otherBeetleFront;


    @Excel(name = "其他天牛数量")
    private Integer otherNum;

    @Excel(name = "药剂类型")
    private String drugFront;


//    @Excel(name = "雄虫数")
    private Integer maleNum;
//    @Excel(name = "雌虫数")
    private Integer femaleNum;
    @Excel(name = "用户名")
    private String username;

    private String name;

    @Excel(name = "邮政编码")
    private String adcode;

    //    @Excel(name = "其他天牛种类")
    private Integer otherType;

//    @Excel(name = "日期")
    private String realdate;
    @DateTimeFormat(pattern = "yyyy年MM月")
//    @Excel(name = "年月")
    private YearMonth yesrmonth;
    @DateTimeFormat(pattern = "yyyy年")
//    @Excel(name = "年份")
    private Integer year;
    @DateTimeFormat(pattern = "MM月")
//    @Excel(name = "月")
    private Integer month;


    private String imageId;

    private String drug;
    @Excel(name = "备注")
    private String remark;

    private String imgId;

    private String province;

    private String city;
    private String area;
    private String town;


    private Integer workingContent;
//    private Integer working_content;
    private Integer isactive;
    private Boolean reported;





    private String cityname;



    @Excel(name = "项目工程")
    private String customProject;

    @Excel(name = "项目管理员")
    private String manager;











    //随机数
   /* public int nonceStr;
    public int getNonceStr(){
        return nonceStr;
    }

    public void setNonceStr(int nonceStr){
        this.nonceStr = nonceStr;
    }

*/

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getcityname(){ return cityname;}

    public void setcityname(String cityname) {
        this.cityname = cityname;
    }

    public YearMonth getYearmonth(){ return yesrmonth;}

    public void setYearmonth(YearMonth yearmonth) {
        this.yesrmonth = yearmonth;
    }

    public Integer getYear(){ return year;}

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getmonth(){ return month;}

    public void setmonth(Integer month) {
        this.month = month;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMaleNum() {
        return maleNum;
    }

    public void setMaleNum(Integer maleNum) {
        this.maleNum = maleNum;
    }

    public Integer getFemaleNum() {
        return femaleNum;
    }

    public void setFemaleNum(Integer femaleNum) {
        this.femaleNum = femaleNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    /*
    public Integer getWorkingContent() {
        return workingContent;
    }

    public void setWorkingContent(Integer workingContent) {
        this.workingContent = workingContent;
    }*/

    public Integer getIsActive() {
        return isactive;
    }

    public void setIsActive(Integer isactive) {
        this.isactive = isactive;
    }

    public Boolean getReported() {
        return reported;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }


    public Integer getWorkingContent() {
        return workingContent;
    }

    public void setWorkingContent(Integer workingContent) {
        this.workingContent = workingContent;
    }

    public Integer getOtherType() {
        return otherType;
    }

    public void setOtherType(Integer otherType) {
        this.otherType = otherType;
    }

    public Integer getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(Integer otherNum) {
        this.otherNum = otherNum;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRealdate() {
        return realdate;
    }

    public void setRealdate(String realdate) {
        this.realdate = realdate;
    }

    public String getCustomProject() {
        return customProject;
    }

    public void setCustomProject(String customProject) {
        this.customProject = customProject;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    public String getWorkContentFront() {
        return workContentFront;
    }

    public void setWorkContentFront(String workContentFront) {
        this.workContentFront = workContentFront;
    }

    public String getOtherBeetleFront() {
        return otherBeetleFront;
    }

    public void setOtherBeetleFront(String otherBeetleFront) {
        this.otherBeetleFront = otherBeetleFront;
    }

    public String getDrugFront() {
        return drugFront;
    }

    public void setDrugFront(String drugFront) {
        this.drugFront = drugFront;
    }

    public String getCustomTown() {
        return customTown;
    }

    public void setCustomTown(String customTown) {
        this.customTown = customTown;
    }

    public String getCustomSerial() {
        return customSerial;
    }

    public void setCustomSerial(String customSerial) {
        this.customSerial = customSerial;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
