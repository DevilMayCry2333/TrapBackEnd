package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.summary.NaturalSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Device_NaturalEnemies_maintanceEntityMapper {

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

}