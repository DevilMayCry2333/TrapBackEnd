package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.device.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/5/31
 */
@Repository
public interface DeviceMapper {

    Boolean isExist(String id);
    Device queryDeviceByDeviceid(@Param("deviceId")String deviceId);
    int addDevice(@Param("device") Device device);
    int updateDevice(@Param("device") Device device);
    int updateDevice1(@Param("device") Device device);
    List<Device> getDeviceByMap(String username);
    List<Device> getDeviceByLocation(@Param("adcode") String adcode, @Param("town") String town,
                                     @Param("searchText") String searchText);
    List<Device> getDeviceByManager(@Param("manager") String manager);

    List<Device> getDeviceByWorker(@Param("worker") String worker);

    List<Device> getDeviceandWorkerByManager(@Param("manager") String manager);

    int addDeviceRelation(@Param("deviceId") String deviceId, @Param("userId") String username);
    int deleteDeviceRelation(@Param("deviceId") String deviceId, @Param("userId") String username);
    List<Device> getDeviceNotAssociatedWithWorker(@Param("worker") String worker,
                                                  @Param("searchText") String searchText);
    List<String> getDeviceIdsAssociatedWithWorker(@Param("worker") String worker);
    String getWorkerAssociatedWithDeviceIds(@Param("device_id") String device_id);
    List<String> getDeviceIdsCanAssociateWithWorker(@Param("adcode") String adcode, @Param("manager") String manager);

    List<Device> getTownSpotDeviceByLocation(@Param("adcode") String adcode, @Param("town") String town);

    List<Device> getAreaSpotDeviceByLocation(@Param("adcode") String adcode);

    List<Device> getCitySpotDeviceByLocation(@Param("adcode") String adcode);

    List<Device> getProvinceSpotDeviceByLocation(@Param("adcode") String adcode);

    List<Map<String, Integer>> getDeviceCountByAdcode(@Param("key") String key,
                                                      @Param("adcode") String adcode, @Param("town") String town);
    Device getDeviceById(String id);

    List<Device> getDeviceInRegion(@Param("adcode") String adcode, @Param("town") String town);

    Long countDeviceInRegion(@Param("adcode") String adcode, @Param("town") String town);

    Long countDeviceInArea(@Param("adcode") String adcode);

    String getMaxDeviceIdInArea(@Param("adcode") String adcode);

    Long judgeDeviceRelation(@Param("username") String username, @Param("deviceId") String deviceId);
    Long judgeDeviceManager(@Param("username") String username, @Param("deviceId") String deviceId);
    Long judgeDeviceUser(@Param("adcode") String adcode, @Param("deviceId") String deviceId);
    List<String> getDeviceIdInTown(@Param("townCode") String townCode);

    int clearWorkerDeviceRelation(@Param("worker") String worker);

}
