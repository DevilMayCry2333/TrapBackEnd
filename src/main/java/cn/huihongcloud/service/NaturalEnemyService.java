package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import cn.huihongcloud.entity.device.Device;
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

    public List<Device_NaturalEnemies_maintanceEntity> selectByDateAndColSearch(String customProject,String startDate,String endDate,String colName,String searchText,String adcode,String town){
        return deviceNaturalEnemiesMaintanceEntityMapper.selectByDateAndColSearch(customProject, startDate, endDate, colName, searchText,adcode,town);
    }

    public int countAll(String username,String startDate,String endDate,String colName,String searchText,String adcode){
        return deviceNaturalEnemiesMaintanceEntityMapper.countAll(username,startDate,endDate,colName,searchText,adcode);
    }

    public int countAllByArea(String adcode){
        return deviceNaturalEnemiesMaintanceEntityMapper.countAllByArea(adcode);
    }

    public List<Device_NaturalEnemies_maintanceEntity> selectAllByArea(String adcode,int num1,int num2){
        return deviceNaturalEnemiesMaintanceEntityMapper.selectAllByArea(adcode, num1, num2);
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

    public List<Device_NaturalEnemies_maintanceEntity> getMaintenanceData1(User user, String condition, String date, String endDate,String batch,String searchtown) {
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

    /**
     * 根据地区获取设备
     *
     * @param adcode     地区代码
     * @param town       乡
     * @param searchText 搜索条件
     * @return 设备列表
     */
    public List<Device> getDeviceByLocation(String adcode, String town, String searchText) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceNaturalEnemiesMaintanceEntityMapper.getDeviceByLocation(adcode, town, searchText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    /**
     * 获取管理员下属的设备
     *
     * @param manager 管理员用户名
     * @return 设备列表
     */
    public List<Device> getDeviceByManager(String manager) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceNaturalEnemiesMaintanceEntityMapper.getDeviceByManager(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    public List<Device> getDeviceByWorker(String worker) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceNaturalEnemiesMaintanceEntityMapper.getDeviceByWorker(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }




    public List<Device_NaturalEnemies_maintanceEntity> getNaturalSummaryByCustomReigon(User user, Integer optionIndex, String searchText, String startDate, String endDate) {
        int role = user.getRole();

        if (role <= 3) {
            return deviceNaturalEnemiesMaintanceEntityMapper.selectNaturalByCustomReigon(user.getUsername(), optionIndex, searchText, startDate, endDate);
        } else if (role == 4) {
            return deviceNaturalEnemiesMaintanceEntityMapper.selectNaturalByCustomReigonCustomProject(user.getParent(), optionIndex, searchText, startDate, endDate);

        }

        /*else if(){

        }*/
        return null;
    }








}
