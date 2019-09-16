package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.summary.InjectionSummary;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_Injection_maintanceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DryInjectionService {
    @Autowired
    private Device_Injection_maintanceEntityMapper deviceInjectionMaintanceEntityMapper;

    public List<Device_Injection_maintanceEntity> getDryInjectionDetail(int page,int limit,String username){
        return deviceInjectionMaintanceEntityMapper.selectAll(page*limit-limit,page*limit,username);
    }

    public int getTotalNum(String username){
        return deviceInjectionMaintanceEntityMapper.CountAll(username);
    }

    public List<InjectionSummary> queryDeviceSummaryByArea(String adcode, String startDate, String endDate){
        return deviceInjectionMaintanceEntityMapper.queryDeviceSummaryByArea(adcode, startDate, endDate);
    }

    public List<Device_Injection_maintanceEntity> getAreaMaintanceDetail(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role < 3) {
            // 省到县级用户
            boolean reported = true;

            return deviceInjectionMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTownArea(user.getAdcode(), user.getTown(), condition, date, endDate, reported);

        } else if (role == 3) {
            return deviceInjectionMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTownArea(user.getAdcode(), user.getTown(), condition, date, endDate, null);
        } else if (role == 4) {
            // 管理员
            return deviceInjectionMaintanceEntityMapper.getMaintenanceDataByManagerArea(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }

    public List<Device_Injection_maintanceEntity> getMaintenanceDataByDeviceId(User user,String myusername,String deviceId, String startDate, String endDate) {
        Integer role=user.getRole();
        if(role<3){
            Boolean reported = true;
            return deviceInjectionMaintanceEntityMapper.getMaintenanceDataByDeviceId(myusername,deviceId,startDate,endDate,reported);
        }else if(role == 3 || role ==4) {

            return deviceInjectionMaintanceEntityMapper.getMaintenanceDataByDeviceId(myusername,deviceId, startDate, endDate, null);
        }else if (role == 5) {
            return null;
        }
        return null;
    }

    public List<Device_Injection_maintanceEntity> getMaintenanceData1(User user, String condition, String date, String endDate,String batch,String searchtown) {
        int role = user.getRole();
        if (role <3) {
            // 省到县级用户
            boolean reported = true;

            return deviceInjectionMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTown1(user.getAdcode(), user.getTown(), condition,batch, searchtown,date, endDate,reported);
        } else if (role == 3) {

            return deviceInjectionMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTown1(user.getAdcode(), user.getTown(), condition, batch,searchtown,date, endDate,null);
        }else if (role == 4) {
            // 管理员
            return deviceInjectionMaintanceEntityMapper.getMaintenanceDataByManager1(user.getAdcode(), user.getTown(), condition, batch,searchtown,date,endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }

    public boolean report(List<Integer> ids) {
        for (Integer id: ids) {
            deviceInjectionMaintanceEntityMapper.reportData(id);
        }
        return true;
    }






}
