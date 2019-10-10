package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.*;
import cn.huihongcloud.entity.beetle.BeetleInfo;
import cn.huihongcloud.entity.device.DeviceBeetle;
import cn.huihongcloud.entity.device.DeviceMaintenance;
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

    List<BeetleInfo> getBeetleInfoByArea(String adcode);
    List<injectInfo> getInjectTypeByArea(String adcode);
    List<workContent> getWorkContentByArea(String adcode);

    List<inject_WoodStatus> getInjectWoodStatus(String adcode);
    List<inject_WorkContent> getInjectWorkContent(String adcode);
    List<enemy_EneType> getEnemyType(String adcode);
    List<deadTree_KillMethods> getKillMethods(String adcode);

    List<Device_NaturalEnemies_maintanceEntity> getById(String deivceId);

    List<DeviceMaintenance> getTrapById(String scanId);

    List<Device_Injection_maintanceEntity> InjectWorker(String scanId);

    List<Device_DeadTrees_maintanceEntity> DeadWorker(String scanId);



}
