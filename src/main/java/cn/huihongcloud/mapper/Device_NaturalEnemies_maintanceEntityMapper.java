package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.summary.NaturalSummary;
import cn.huihongcloud.entity.summary.SummaryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Device_NaturalEnemies_maintanceEntityMapper {

    List<Device_NaturalEnemies_maintanceEntity> selectByDateAndColSearch(String username,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode);


    List<Device_NaturalEnemies_maintanceEntity> selectAll(String username,int num1,int num2);

    int countAll(String username);

    int insert(Device_NaturalEnemies_maintanceEntity record);

    int insertSelective(Device_NaturalEnemies_maintanceEntity record);

    int countAllByArea(String username);

    List<Device_NaturalEnemies_maintanceEntity> selectAllByArea(String username, int num1, int num2);

    List<NaturalSummary> queryDeviceSummaryByArea(String adcode, String startDate, String endDate);

    List<Device_NaturalEnemies_maintanceEntity> getMaintenanceDataByAdcodeAndTown2(@Param("adcode") String adcode, @Param("town") String town,
                                                               @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);
    List<Device_NaturalEnemies_maintanceEntity> getMaintenanceDataByManager2(@Param("adcode") String adcode, @Param("town") String town,
                                                         @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);

    List<NaturalSummary> queryDeviceSummaryByManager(String adcode, String startDate, String endDate);

    Map<String, Long> queryDeviceSum(String adcode, String startDate, String endDate);
    Map<String, Long> queryDeviceSum4(String adcode,String startDate,String endDate);

    List<Device_NaturalEnemies_maintanceEntity> getMaintenanceDataByAdcodeAndTownArea(@Param("adcode") String adcode, @Param("town") String town,
                                                                                 @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);

    List<Device_NaturalEnemies_maintanceEntity> getMaintenanceDataByManagerArea(@Param("adcode") String adcode, @Param("town") String town,
                                                                           @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);

    List<Device_NaturalEnemies_maintanceEntity> getMaintenanceDataByDeviceId(String myusername, String deviceId, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("reported")Boolean reported);

    List<Device_Injection_maintanceEntity> getMaintenanceDataByAdcodeAndTown1(@Param("adcode") String adcode, @Param("town") String town,
                                                                              @Param("condition") String condition,@Param("batch") String batch,@Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);
    List<Device_Injection_maintanceEntity> getMaintenanceDataByManager1(@Param("adcode") String adcode, @Param("town") String town,
                                                                        @Param("condition") String condition,@Param("batch")String batch,@Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);
    int reportData(@Param("id") Integer id);

    List<NaturalSummary> queryDeviceSummaryByCity(String adcode,String startDate, String endDate);

    List<NaturalSummary> queryWorkerSummaryByAdcode(String adcode,String startDate, String endDate);


    List<NaturalSummary> queryWorkerSummaryByManager(@Param("manager") String manager,String startDate, String endDate);

    List<NaturalSummary> queryDeviceSummaryByProvince(String adcode,String startDate, String endDate);



}