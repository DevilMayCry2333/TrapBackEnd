package cn.huihongcloud.service;

import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.device.DeviceMaintenanceAbnormalData;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.DeviceMaintenanceAbnormalDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 钟晖宏 on 2019/2/6
 */
@Service
public class DeviceMaintenanceAbnormalService {

    @Autowired
    private DeviceMaintenanceAbnormalDataMapper mDeviceMaintenanceAbnormalDataMapper;

    public List<DeviceMaintenanceAbnormalData> getMaintenanceData(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role < 3) {
            // 省到市级用户
            boolean reported = true;


            return mDeviceMaintenanceAbnormalDataMapper.getMaintenanceDataByAdcodeAndTown(user.getAdcode(), user.getTown(), condition, date, endDate, reported);
        }else if (role == 3){
            //县级用户
            return mDeviceMaintenanceAbnormalDataMapper.getMaintenanceDataByAdcodeAndTown(user.getAdcode(), user.getTown(), condition, date, endDate, null);
        }else if (role == 4) {
            // 管理员
            return mDeviceMaintenanceAbnormalDataMapper.getMaintenanceDataByManager(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }
    public int deleteById(int id) {
        return mDeviceMaintenanceAbnormalDataMapper.deleteById(id);
    }

    public void updateMaintenanceAbnormalData(DeviceMaintenanceAbnormalData deviceMaintenanceAbnormalData) {
        mDeviceMaintenanceAbnormalDataMapper.updateMaintenanceAbnormalData(deviceMaintenanceAbnormalData);
    }
    public boolean report(List<Integer> ids) {
        for (Integer id: ids) {
            mDeviceMaintenanceAbnormalDataMapper.reportData(id);
        }
        return true;
    }

}
