package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.device.DeviceForest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/7/18
 */
@Repository
public interface DeviceForestMapper {

    List<DeviceForest> selectByDeviceId(String deviceId);
    int addData(DeviceForest deviceForest);
    int updateData(DeviceForest deviceForest);
    int deleteData(int id);
}
