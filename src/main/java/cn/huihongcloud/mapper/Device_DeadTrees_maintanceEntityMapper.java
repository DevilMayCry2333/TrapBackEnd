package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.device.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface Device_DeadTrees_maintanceEntityMapper {

    int countAll(String username,String startDate,String endDate,String colName,String searchText);

    List<Device_DeadTrees_maintanceEntity> selectAll(String username,int num1,int num2);


    int insert(Device_DeadTrees_maintanceEntity record);

    int insertSelective(Device_DeadTrees_maintanceEntity record);

    int countAllByArea(String adcode);

    List<Device_DeadTrees_maintanceEntity> selectAllByArea(String adcode,int num1,int num2);

    List<Device_DeadTrees_maintanceEntity> selectByDateAndColSearch(String username,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode);

    List<Device_DeadTrees_maintanceEntity> selectAllByAdcode(String adcode,int num1,int num2);

    List<Device> getDeviceByLocation(@Param("adcode") String adcode, @Param("town") String town,
                                     @Param("searchText") String searchText);

    List<Device> getDeviceByManager(@Param("manager") String manager);

    List<Device> getDeviceByWorker(@Param("worker") String worker);

    int addMaintance(Device_DeadTrees_maintanceEntity dataEnity);

    Device_DeadTrees_maintanceEntity selectById(String id);

    int updateRecordById(Device_DeadTrees_maintanceEntity dataEnity);

    Device_DeadTrees_maintanceEntity getMaxBatch(String deviceId);

    int deleteById(String id);

}