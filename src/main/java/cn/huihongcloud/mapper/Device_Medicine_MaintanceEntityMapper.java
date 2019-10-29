package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.Device_Medicine_MaintanceEntity;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.summary.MedicineSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public interface Device_Medicine_MaintanceEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Device_Medicine_MaintanceEntity record);

    int insertSelective(Device_Medicine_MaintanceEntity record);

    Device_Medicine_MaintanceEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device_Medicine_MaintanceEntity record);

    int updateByPrimaryKey(Device_Medicine_MaintanceEntity record);

    List<Device_Medicine_MaintanceEntity> selectByConditions(@Param("customProject") String customProject, @Param("optionIndex") Integer optionIndex,
                                                             @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Device_Medicine_MaintanceEntity> selectByConditionsAdcode(@Param("adcode") String adcode, @Param("optionIndex") Integer optionIndex,
                                                                    @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Device_Medicine_MaintanceEntity> selectByDateAndColSearch(String username,String startDate,String endDate,String colName,String searchText,String adcode);

    List<Device_Medicine_MaintanceEntity> selectByCustomReigon(@Param("username") String username, @Param("optionIndex") Integer optionIndex,
                                                                @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Device_Medicine_MaintanceEntity> selectByCustomReigonCustomProject(@Param("customProject") String customProject, @Param("optionIndex") Integer optionIndex,
                                                                             @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Device> getDeviceByLocation(@Param("adcode") String adcode, @Param("town") String town,
                                     @Param("searchText") String searchText);

    List<Device> getDeviceByManager(@Param("manager") String manager);

    List<Device> getDeviceByWorker(@Param("worker") String worker);

    int updateRecordById1(Device_Medicine_MaintanceEntity device_medicine_maintanceEntity);

    int reportData1(@Param("id") Integer id);

    List<Device_Medicine_MaintanceEntity> getMaintenanceDataByAdcodeAndTown222(@Param("adcode") String adcode, @Param("town") String town,
                                                                               @Param("condition") String condition, @Param("batch") String batch, @Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);


    Device_Medicine_MaintanceEntity selectById1(BigInteger id);



    List<Device_Medicine_MaintanceEntity> getMaintenanceDataByManager222(@Param("adcode") String adcode, @Param("town") String town,
                                                                        @Param("condition") String condition, @Param("batch") String batch, @Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);

    Map<String,Integer> getDeviceSum(String adcode,String startDate,String endDate);

    Map<String,Integer> getDeviceSum5(String adcode,String stareDate,String endDate);

    List<Device_Medicine_MaintanceEntity> getMaxBatch(String deviceId);
    //缺twon
    int addMaintanceData(Device_Medicine_MaintanceEntity dataEntity);

    List<MedicineSummary> queryDeviceSummaryByManager111(String adcode, String startDate, String endDate);

    List<MedicineSummary> queryDeviceSummaryByArea111(String adcode, String startDate, String endDate);


    List<Device_Medicine_MaintanceEntity> getMaintenanceDataByAdcodeAndTownArea111(@Param("adcode") String adcode, @Param("town") String town,
                                                                                 @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);

    List<Device_Medicine_MaintanceEntity> getMaintenanceDataByManagerArea111(@Param("adcode") String adcode, @Param("town") String town,
                                                                           @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);


    List<Device_Medicine_MaintanceEntity> getMaintenanceDataByDeviceId111(String myusername, String deviceId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("reported") Boolean reported);

}