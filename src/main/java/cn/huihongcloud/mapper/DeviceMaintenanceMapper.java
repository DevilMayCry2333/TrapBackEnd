package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.statistics.InputEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/9/24
 */
@Repository
public interface DeviceMaintenanceMapper {

    List<DeviceMaintenance> getMaintenanceDataByDeviceId(String myusername,String deviceId, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("reported")Boolean reported);
    List<DeviceMaintenance> getMaintenanceDataByAdcodeAndTown(@Param("adcode") String adcode, @Param("town") String town,
                                                              @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);
    List<DeviceMaintenance> getMaintenanceDataByManager1(@Param("customProject") String customProject, @Param("town") String town,
                                                              @Param("condition") String condition,@Param("batch")String batch,@Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);
    List<DeviceMaintenance> getMaintenanceDataByAdcodeAndTown1(@Param("adcode") String adcode, @Param("town") String town,
                                                              @Param("condition") String condition,@Param("batch") String batch,@Param("searchtown") String searchtown, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);
    List<DeviceMaintenance> getMaintenanceDataByManager(@Param("adcode") String adcode, @Param("town") String town,
                                                        @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);

    List<DeviceMaintenance> getMaintenanceDataByAdcodeAndTown2(@Param("adcode") String adcode, @Param("town") String town,
                                                               @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);
    List<DeviceMaintenance> getMaintenanceDataByManager2(@Param("adcode") String adcode, @Param("town") String town,
                                                        @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager);
    List<DeviceMaintenance> getMaintenanceDataByAdcodeAndTown4(@Param("adcode") String adcode, @Param("town") String town,
                                                               @Param("condition") String condition,@Param("batch") String batch,@Param("searchTown") String searchTown, @Param("date") String date, @Param("endDate") String endDate, @Param("reported") Boolean reported);
    List<DeviceMaintenance> getMaintenanceDataByManager4(@Param("adcode") String adcode, @Param("town") String town,
                                                         @Param("condition") String condition,@Param("batch") String batch,@Param("searchTown") String searchTown, @Param("date") String date, @Param("endDate") String endDate, @Param("manager") String manager,@Param("reported") Boolean reported);


    List<DeviceMaintenance> getMaintenanceDataByUsername(@Param("username") String username,
                                                         @Param("condition") String condition, @Param("date") String date, @Param("endDate") String endDate);
    int addMaintenanceData(DeviceMaintenance deviceMaintenance);
    int updateMaintenanceData(DeviceMaintenance deviceMaintenance);
    int updateMaintenanceDataByDeviceIdAndBatch(DeviceMaintenance deviceMaintenance);
    int deleteById(int id);
    int deleteByIdReally(int id);
    List<DeviceMaintenance> getStatisticsByTown(@Param("adcode") String adcode, @Param("town") String town);
    List<DeviceMaintenance> getStatisticsByArea(@Param("adcode") String adcode);
    List<DeviceMaintenance> getStatisticsByLikeAdcode(@Param("adcode") String adcode);
    int getChangeTimesByDeviceId(String deviceId);
    List<DeviceMaintenance> getMaintenanceDataById(@Param("id") String id, @Param("startDate")String startDate, @Param("endDate")String endDate, @Param("reported")Boolean reported);
    DeviceMaintenance getoneMaintenanceDataById(@Param("id") Integer id, @Param("startDate")String startDate, @Param("endDate")String endDate);
    // 统计专用的几个
    // 获取管理员下的工人统计输入数据
    DeviceMaintenance getMaintenanceDataById1(@Param("id") Integer id);
    DeviceMaintenance getMaintenanceDataById2(@Param("id") Integer id);
    List<DeviceMaintenance> getMaintenanceDataByIdScan(@Param("id") String id);
    // 统计专用的几个
    List<InputEntity> getInputEntityForWorker(@Param("manager") String manager, @Param("startDate") String startDate,
                                              @Param("endDate") String endDate);
    // 获取县下的乡的统计输入数据
    List<InputEntity> getInputEntityForTown(@Param("adcode") String adcode, @Param("startDate") String startDate,
                                            @Param("endDate") String endDate);
    // 获取市下的县的统计输入数据
    List<InputEntity> getInputEntityForArea(@Param("code") String code, @Param("startDate") String startDate,
                                            @Param("endDate") String endDate);
    // 获取省下的市的统计输入数据
    List<InputEntity> getInputEntityForCity(@Param("code") String code, @Param("startDate") String startDate,
                                            @Param("endDate") String endDate);
    List<DeviceMaintenance> getMonthSummaryByCity(@Param("adcode") String adcode, @Param("startYear") String startYear,@Param("endYear") String endYear);
    List<DeviceMaintenance> getMonthSummaryByArea(@Param("adcode") String adcode, @Param("startYear") String startYear,@Param("endYear") String endYear);
    List<DeviceMaintenance> getMonthSummaryByTown(@Param("adcode") String adcode, @Param("startYear") String startYear,@Param("endYear") String endYear);
    List<DeviceMaintenance> getMonthSummaryByWorker(@Param("adcode") String adcode, @Param("startYear") String startYear,@Param("endYear") String endYear);

    int reportData(@Param("id") Integer id);
    Integer getMaxBatchByDeviceid(@Param("deviceID") String deviceID);


}
