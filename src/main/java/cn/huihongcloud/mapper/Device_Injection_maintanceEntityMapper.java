package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.summary.InjectionSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface Device_Injection_maintanceEntityMapper {

    int CountAll(String username);

    List<Device_Injection_maintanceEntity> selectAll(int page1,int page2,String username);

    List<Device_Injection_maintanceEntity> selectById(BigInteger id);

    int insert(Device_Injection_maintanceEntity record);

    int insertSelective(Device_Injection_maintanceEntity record);

    List<InjectionSummary> queryDeviceSummaryByArea(String adcode, String startDate, String endDate);

    List<Device_Injection_maintanceEntity> getMaintenanceDataByAdcodeAndTownArea(@Param("adcode") String adcode, @Param("town") String town,
                                                               @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);
    List<Device_Injection_maintanceEntity> getMaintenanceDataByManagerArea(@Param("adcode") String adcode, @Param("town") String town,
                                                         @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);

    List<Device_Injection_maintanceEntity> getMaintenanceDataByDeviceId(String myusername, String deviceId, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("reported")Boolean reported);


}