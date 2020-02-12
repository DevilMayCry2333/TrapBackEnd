package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.Device_Medicine_MaintanceEntity;
import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.inject_WoodStatus;
import cn.huihongcloud.entity.summary.InjectionSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public interface Device_Injection_maintanceEntityMapper {

    List<Device_Injection_maintanceEntity> selectByDateAndColSearch(String username,String startDate,String endDate,String colName,String searchText,String adcode,String customProject,String town);




    int CountAll(String username);

//    List<Device_Injection_maintanceEntity> selectAll(int page1, int page2, String username);

    List<Device_Injection_maintanceEntity> selectByConditions(@Param("customProject") String customProject, @Param("optionIndex") Integer optionIndex,
                                                              @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate);


    List<Device_Injection_maintanceEntity> selectByConditionsAdcode(@Param("adcode") String adcode, @Param("optionIndex") Integer optionIndex,
                                                              @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate);


    Device_Injection_maintanceEntity selectById2(BigInteger scanId,Integer batch);

    int insert(Device_Injection_maintanceEntity record);

    int insertSelective(Device_Injection_maintanceEntity record);

    List<InjectionSummary> queryDeviceSummaryByArea(String adcode, String startDate, String endDate);

    List<Device_Injection_maintanceEntity> getMaintenanceDataByAdcodeAndTownArea(@Param("adcode") String adcode, @Param("town") String town,
                                                                                 @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);

    List<Device_Injection_maintanceEntity> getMaintenanceDataByManagerArea(@Param("adcode") String adcode, @Param("town") String town,
                                                                           @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);

    List<Device_Injection_maintanceEntity> getMaintenanceDataByDeviceId(String myusername, String deviceId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("reported") Boolean reported);

    List<InjectionSummary> queryDeviceSummaryByManager(String adcode, String startDate, String endDate);

    List<Device_Injection_maintanceEntity> getMaintenanceDataByAdcodeAndTown1(@Param("adcode") String adcode, @Param("town") String town,
                                                                              @Param("condition") String condition, @Param("batch") String batch, @Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);

    List<Device_Injection_maintanceEntity> getMaintenanceDataByManager1(@Param("adcode") String adcode, @Param("town") String town,
                                                                        @Param("condition") String condition, @Param("batch") String batch, @Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);

    int reportData(@Param("id") Integer id);



    List<InjectionSummary> queryDeviceSummaryByCity(String adcode, String startDate, String endDate);

    Map<String, Integer> queryDeviceSum(String adcode, String startDate, String endDate);

    Map<String, Integer> queryDeviceSum4(String adcode, String startDate, String endDate);

    List<InjectionSummary> queryWorkerSummaryByAdcode(String adcode, String startDate, String endDate);

    List<InjectionSummary> queryWorkerSummaryByManager(@Param("manager") String manager, String startDate, String endDate);

    List<InjectionSummary> queryDeviceSummaryByProvince(String adcode, String startDate, String endDate);

    List<Device> getDeviceByLocation(@Param("adcode") String adcode, @Param("town") String town,
                                     @Param("searchText") String searchText);

    List<Device> getDeviceByManager(@Param("manager") String manager);

    List<Device> getDeviceByWorker(@Param("worker") String worker);

    List<Device_Injection_maintanceEntity> selectByCustomReigon(@Param("username") String username, @Param("optionIndex") Integer optionIndex,
                                                              @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate,
                                                                int deadId);

    List<Device_Injection_maintanceEntity> selectByCustomReigonCustomProject(@Param("customProject") String customProject, @Param("optionIndex") Integer optionIndex,
                                                                @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate,
                                                                int deadId);



    int addMaintanceData(Device_Injection_maintanceEntity dataEntity);

    List<Device_Injection_maintanceEntity> getMaxBatch(String deviceId);

    int deleteRecord(long recordId);

    int updateRecordById(Device_Injection_maintanceEntity dataEntity);

    int updateRecordByFront(Device_Injection_maintanceEntity d);


    List<inject_WoodStatus> getDeadStatus();






}
