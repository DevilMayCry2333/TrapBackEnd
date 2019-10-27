package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_Medicine_MaintanceEntity;
import cn.huihongcloud.entity.device.Device;
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
}
