package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.Device_Medicine_MaintanceEntity;
import cn.huihongcloud.entity.device.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    List<Device_Medicine_MaintanceEntity> getMaxBatch(String deviceId);
    //缺twon
    int addMaintanceData(Device_Medicine_MaintanceEntity dataEntity);
}