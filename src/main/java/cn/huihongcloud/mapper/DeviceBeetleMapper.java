package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.device.DeviceBeetle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/7/18
 */
@Repository
public interface DeviceBeetleMapper {

    List<DeviceBeetle> selectByDeviceId(String deviceId);
    int addData(DeviceBeetle deviceBeetle);
    int deleteById(int id);
    int updateData(DeviceBeetle deviceBeetle);
    List<DeviceBeetle> getStatisticsByTown(@Param("adcode") String adcode, @Param("town") String town);
    List<DeviceBeetle> getStatisticsByArea(@Param("adcode") String adcode);
    List<DeviceBeetle> getStatisticsByLikeAdcode(@Param("adcode") String adcode);
    int getChangeTimesByDeviceId(String deviceId);
}
