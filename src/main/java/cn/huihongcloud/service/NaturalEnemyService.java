package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.device.DeviceMaintenance;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_NaturalEnemies_maintanceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NaturalEnemyService {
    @Autowired
    Device_NaturalEnemies_maintanceEntityMapper deviceNaturalEnemiesMaintanceEntityMapper;
    public List<Device_NaturalEnemies_maintanceEntity> selectAll(String username,int num1,int num2){
        return deviceNaturalEnemiesMaintanceEntityMapper.selectAll(username,num1,num2);
    }

    public List<Device_NaturalEnemies_maintanceEntity> selectByDateAndColSearch(String username,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode){
        return deviceNaturalEnemiesMaintanceEntityMapper.selectByDateAndColSearch(username, startDate, endDate, colName, searchText, num1, num2,adcode);
    }

    public int countAll(String username){
        return deviceNaturalEnemiesMaintanceEntityMapper.countAll(username);
    }

    public int countAllByArea(String username){
        return deviceNaturalEnemiesMaintanceEntityMapper.countAllByArea(username);
    }

    public List<Device_NaturalEnemies_maintanceEntity> selectAllByArea(String username,int num1,int num2){
        return deviceNaturalEnemiesMaintanceEntityMapper.selectAllByArea(username, num1, num2);
    }

    public List<Device_NaturalEnemies_maintanceEntity> getMaintenanceData2(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role < 3) {
            // 省到县级用户
            boolean reported = true;

            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTown2(user.getAdcode(), user.getTown(), condition, date, endDate, reported);

        } else if (role == 3) {
            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTown2(user.getAdcode(), user.getTown(), condition, date, endDate, null);
        } else if (role == 4) {
            // 管理员
            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByManager2(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }

    public List<Device_NaturalEnemies_maintanceEntity> getAreaMaintanceDetail(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role < 3) {
            // 省到县级用户
            boolean reported = true;

            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTownArea(user.getAdcode(), user.getTown(), condition, date, endDate, reported);

        } else if (role == 3) {
            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTownArea(user.getAdcode(), user.getTown(), condition, date, endDate, null);
        } else if (role == 4) {
            // 管理员
            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByManagerArea(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }

    public List<Device_NaturalEnemies_maintanceEntity> getMaintenanceDataByDeviceId(User user,String myusername,String deviceId, String startDate, String endDate) {
        Integer role=user.getRole();
        if(role<3){
            Boolean reported = true;
            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByDeviceId(myusername,deviceId,startDate,endDate,reported);
        }else if(role == 3 || role ==4) {

            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByDeviceId(myusername,deviceId, startDate, endDate, null);
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

            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTown1(user.getAdcode(), user.getTown(), condition,batch, searchtown,date, endDate,reported);
        } else if (role == 3) {

            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTown1(user.getAdcode(), user.getTown(), condition, batch,searchtown,date, endDate,null);
        }else if (role == 4) {
            // 管理员
            return deviceNaturalEnemiesMaintanceEntityMapper.getMaintenanceDataByManager1(user.getAdcode(), user.getTown(), condition, batch,searchtown,date,endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }

    public boolean report(List<Integer> ids) {
        for (Integer id: ids) {
            deviceNaturalEnemiesMaintanceEntityMapper.reportData(id);
        }
        return true;
    }





}
