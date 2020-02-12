package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.summary.NaturalSummary;
import cn.huihongcloud.entity.summary.SummaryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Device_NaturalEnemies_maintanceEntityMapper {

    int selectDevicesByDateAndColSearch(String customProject,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode);

    List<Device_NaturalEnemies_maintanceEntity> selectAllByDateAndColSearch(String customProject,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode);


    List<Device_NaturalEnemies_maintanceEntity> selectByDateAndColSearch(String customProject,@Param("startDate") String startDate,@Param("endDate") String endDate,String colName,String searchText,String adcode,String town);

    List<Device_NaturalEnemies_maintanceEntity> selectByDateAndColSearchAdcode(String startDate,String endDate,String colName,String searchText,String adcode);



    List<Device_NaturalEnemies_maintanceEntity> selectAll(String username,int num1,int num2);

    int countAll(String username,String startDate,String endDate,String colName,String searchText,String adcode);

    int insert(Device_NaturalEnemies_maintanceEntity record);

    int insertSelective(Device_NaturalEnemies_maintanceEntity record);

    int countAllByArea(String adcode);

    List<Device_NaturalEnemies_maintanceEntity> selectAllByArea(String adcode);

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

    List<Device_NaturalEnemies_maintanceEntity> getMaintenanceDataByAdcodeAndTown1(@Param("adcode") String adcode, @Param("town") String town,
                                                                              @Param("condition") String condition,@Param("batch") String batch,@Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);
    List<Device_NaturalEnemies_maintanceEntity> getMaintenanceDataByManager1(@Param("adcode") String adcode, @Param("town") String town,
                                                                        @Param("condition") String condition,@Param("batch")String batch,@Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);
    int reportData(@Param("id") Integer id);

    List<NaturalSummary> queryDeviceSummaryByCity(String adcode,String startDate, String endDate);

    List<NaturalSummary> queryWorkerSummaryByAdcode(String adcode,String startDate, String endDate);


    List<NaturalSummary> queryWorkerSummaryByManager(@Param("manager") String manager,String startDate, String endDate);

    List<NaturalSummary> queryDeviceSummaryByProvince(String adcode,String startDate, String endDate);

    List<Device> getDeviceByLocation(@Param("adcode") String adcode, @Param("town") String town,
                                     @Param("searchText") String searchText);

    List<Device> getDeviceByManager(@Param("manager") String manager);

    List<Device> getDeviceByWorker(@Param("worker") String worker);

    int addMaintance(Device_NaturalEnemies_maintanceEntity dataEntity);

    Device_NaturalEnemies_maintanceEntity selectById(String scanId,Integer batch);

    int updateRecordById(Device_NaturalEnemies_maintanceEntity dataEntity);

    List<Device_NaturalEnemies_maintanceEntity> getMaxBatch(String deviceId);

    int deleteRecord(long id);

    List<Device_NaturalEnemies_maintanceEntity> workerQueryById(String scanId);

    int updateRecordByFront(Device_NaturalEnemies_maintanceEntity dataEntity);


    List<Device_NaturalEnemies_maintanceEntity> selectNaturalByCustomReigonCustomProject(@Param("customProject") String customProject, @Param("optionIndex") Integer optionIndex,
                                                                            @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate);


    List<Device_NaturalEnemies_maintanceEntity> selectNaturalByCustomReigon(@Param("username") String username, @Param("optionIndex") Integer optionIndex,
                                                               @Param("searchText") String searchText, @Param("startDate") String startDate, @Param("endDate") String endDate);


    List<Device_NaturalEnemies_maintanceEntity> queryNatualMannerOne(String user,String customTown);
    List<Device_NaturalEnemies_maintanceEntity> queryNatualMannerTwo(String user,String customTown);
    List<Device_NaturalEnemies_maintanceEntity> queryNatualMannerThree(String user,String customTown);

    List<NaturalSummary> queryNatualMannerOneBytownOne(String adcode,String town);
    List<NaturalSummary> queryNatualMannerOneBytownTwo(String adcode,String town);
    List<NaturalSummary> queryNatualMannerOneBytownThree(String adcode,String town);

    NaturalSummary queryNatualMannerOneByCustomProject(String adcode,String CustomProject);
    NaturalSummary queryNatualMannerTwoByCustomProject(String adcode,String CustomProject);
    NaturalSummary queryNatualMannerThreeByCustomProject(String adcode,String CustomProject);

}