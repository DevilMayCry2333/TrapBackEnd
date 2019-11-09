package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_Medicine_MaintanceEntity;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.summary.MedicineSummary;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_Medicine_MaintanceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceMedicineMaintanceService {
    @Autowired
    Device_Medicine_MaintanceEntityMapper device_medicine_maintanceEntityMapper;

    public List<Device_Medicine_MaintanceEntity> getMedicineDetail(String customProject, Integer optionIndex, String searchText, String startDate, String endDate) {
        return device_medicine_maintanceEntityMapper.selectByConditions(customProject, optionIndex, searchText, startDate, endDate);
    }
    public List<Device_Medicine_MaintanceEntity> getDryInjectionSummaryByCustomReigon(User user, Integer optionIndex, String searchText, String startDate, String endDate) {
        int role = user.getRole();

        if (role <= 3) {
            return device_medicine_maintanceEntityMapper.selectByCustomReigon(user.getUsername(), optionIndex, searchText, startDate, endDate);
        } else if (role == 4) {
            return device_medicine_maintanceEntityMapper.selectByCustomReigonCustomProject(user.getParent(), optionIndex, searchText, startDate, endDate);

        }

        /*else if(){

        }*/
        return null;
    }
    public List<Device> getDeviceByLocation(String adcode, String town, String searchText) {
        List<Device> deviceList = null;
        try {
            deviceList = device_medicine_maintanceEntityMapper.getDeviceByLocation(adcode, town, searchText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    public List<Device> getDeviceByManager(String manager) {
        List<Device> deviceList = null;
        try {
            deviceList = device_medicine_maintanceEntityMapper.getDeviceByManager(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    public List<Device> getDeviceByWorker(String worker) {
        List<Device> deviceList = null;
        try {
            deviceList = device_medicine_maintanceEntityMapper.getDeviceByWorker(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    public boolean report11(List<Integer> ids) {
        for (Integer id : ids) {
            device_medicine_maintanceEntityMapper.reportData1(id);
        }
        return true;
    }

    public List<Device_Medicine_MaintanceEntity> getMaintenanceData1(User user, String condition, String date, String endDate, String batch, String searchtown) {
        int role = user.getRole();
        if (role < 3) {
            // 省到县级用户
            boolean reported = true;

            return device_medicine_maintanceEntityMapper.getMaintenanceDataByAdcodeAndTown222(user.getAdcode(), user.getTown(), condition, batch, searchtown, date, endDate, reported);
        } else if (role == 3) {

            return device_medicine_maintanceEntityMapper.getMaintenanceDataByAdcodeAndTown222(user.getAdcode(), user.getTown(), condition, batch, searchtown, date, endDate, null);
        } else if (role == 4) {
            // 管理员
            return device_medicine_maintanceEntityMapper.getMaintenanceDataByManager222(user.getAdcode(), user.getTown(), condition, batch, searchtown, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }


    public List<MedicineSummary> queryDeviceSummaryByArea111(String adcode, String startDate, String endDate) {
        return device_medicine_maintanceEntityMapper.queryDeviceSummaryByArea111(adcode, startDate, endDate);
    }

    public List<Device_Medicine_MaintanceEntity> getAreaMaintanceDetail(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role < 3) {
            // 省到县级用户
            boolean reported = true;

            return device_medicine_maintanceEntityMapper.getMaintenanceDataByAdcodeAndTownArea111(user.getAdcode(), user.getTown(), condition, date, endDate, reported);

        } else if (role == 3) {
            return device_medicine_maintanceEntityMapper.getMaintenanceDataByAdcodeAndTownArea111(user.getAdcode(), user.getTown(), condition, date, endDate, null);
        } else if (role == 4) {
            // 管理员
            return device_medicine_maintanceEntityMapper.getMaintenanceDataByManagerArea111(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }


    public List<Device_Medicine_MaintanceEntity> getMaintenanceDataByDeviceId111(User user, String myusername, String deviceId, String startDate, String endDate) {
        Integer role = user.getRole();
        if (role < 3) {
            Boolean reported = true;
            return device_medicine_maintanceEntityMapper.getMaintenanceDataByDeviceId111(myusername, deviceId, startDate, endDate, reported);
        } else if (role == 3 || role == 4) {

            return device_medicine_maintanceEntityMapper.getMaintenanceDataByDeviceId111(myusername, deviceId, startDate, endDate, null);
        } else if (role == 5) {
            return null;
        }
        return null;
    }



    public int countAll1(String username,String startDate,String endDate,Integer optionIndex,String searchText){
        return device_medicine_maintanceEntityMapper.countAll1(username,startDate,endDate,optionIndex,searchText);
    }


}
