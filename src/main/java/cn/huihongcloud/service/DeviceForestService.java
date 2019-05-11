package cn.huihongcloud.service;

import cn.huihongcloud.entity.device.DeviceForest;
import cn.huihongcloud.mapper.DeviceForestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/7/20
 */
@Service
public class DeviceForestService {

    @Autowired
    private DeviceForestMapper deviceForestMapper;

    public List<DeviceForest> selectByDeviceId(String deviceId) {
        try {
            return deviceForestMapper.selectByDeviceId(deviceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addData(DeviceForest deviceForest) {
        int n = 0;
        try {
            n = deviceForestMapper.addData(deviceForest);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return n == 1;
    }

    public boolean updateData(DeviceForest deviceForest) {
        int n = 0;
        try {
            n = deviceForestMapper.updateData(deviceForest);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return n == 1;
    }

    public boolean deleteData(int id) {
        int n = 0;
        try {
            n = deviceForestMapper.deleteData(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return n == 1;
    }
}
