package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.Device_DeadTrees_maintanceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeadTreeCutService {
    @Autowired
    Device_DeadTrees_maintanceEntityMapper deviceDeadTreesMaintanceEntityMapper;
    @Autowired
    UserService userService;

    public List<Device_DeadTrees_maintanceEntity> selectAll(String username,int num1,int num2){
        return deviceDeadTreesMaintanceEntityMapper.selectAll(username, num1, num2);
    }

    public int countAll(String username,String startDate,String endDate,String colName,String searchText){
        return deviceDeadTreesMaintanceEntityMapper.countAll(username,startDate,endDate,colName,searchText);
    }

    public int countAllByArea(String adcode,String startDate,String endDate,String colName,String searchText){
        return deviceDeadTreesMaintanceEntityMapper.countAllByArea(adcode,startDate,endDate,colName,searchText);
    }

    public List<Device_DeadTrees_maintanceEntity> selectAllByArea(String adcode,int num1,int num2){
        return deviceDeadTreesMaintanceEntityMapper.selectAllByArea(adcode, num1, num2);
    }

    public List<Device_DeadTrees_maintanceEntity> selectByDateAndColSearch(String customProject,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode){
        return deviceDeadTreesMaintanceEntityMapper.selectByDateAndColSearch(customProject, startDate, endDate, colName, searchText, num1, num2,adcode);
    }

    public List<Device_DeadTrees_maintanceEntity> selectAllByAdcode(String adcode,int num1,int num2){
        return deviceDeadTreesMaintanceEntityMapper.selectAllByAdcode(adcode, num1, num2);
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
            deviceList = deviceDeadTreesMaintanceEntityMapper.getDeviceByLocation(adcode, town, searchText);
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
            deviceList = deviceDeadTreesMaintanceEntityMapper.getDeviceByManager(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }

    public List<Device> getDeviceByWorker(String worker) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceDeadTreesMaintanceEntityMapper.getDeviceByWorker(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }


    public List<Device_DeadTrees_maintanceEntity> getAreaMaintanceDetail(User user, String condition, String date, String endDate) {
        int role = user.getRole();
        if (role < 3) {
            // 省到县级用户
            boolean reported = true;

            return deviceDeadTreesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTownArea(user.getAdcode(), user.getTown(), condition, date, endDate, reported);

        } else if (role == 3) {
//            User user1 = userService.getUserByUserName(user.getParent());
            System.out.println("=====");
            System.out.println(user.getUsername());


            return deviceDeadTreesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTownArea(user.getAdcode(), user.getTown(), condition, date, endDate, null);
        } else if (role == 4) {
            // 管理员
            return deviceDeadTreesMaintanceEntityMapper.getMaintenanceDataByManagerArea(user.getAdcode(), user.getTown(), condition, date, endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }

    public List<Device_DeadTrees_maintanceEntity> getMaintenanceDataByDeviceId(User user,String myusername,String deviceId, String startDate, String endDate) {
        Integer role=user.getRole();
        if(role<3){
            Boolean reported = true;
            return deviceDeadTreesMaintanceEntityMapper.getMaintenanceDataByDeviceId(myusername,deviceId,startDate,endDate,reported);
        }else if(role == 3 || role ==4) {

            return deviceDeadTreesMaintanceEntityMapper.getMaintenanceDataByDeviceId(myusername,deviceId, startDate, endDate, null);
        }else if (role == 5) {
            return null;
        }
        return null;
    }

    public List<Device_DeadTrees_maintanceEntity> getMaintenanceData1(User user, String condition, String date, String endDate,String batch,String searchtown) {
        int role = user.getRole();
        if (role <3) {
            // 省到县级用户
            boolean reported = true;

            return deviceDeadTreesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTown1(user.getAdcode(), user.getTown(), condition,batch, searchtown,date, endDate,reported);
        } else if (role == 3) {

            return deviceDeadTreesMaintanceEntityMapper.getMaintenanceDataByAdcodeAndTown1(user.getAdcode(), user.getTown(), condition, batch,searchtown,date, endDate,null);
        }else if (role == 4) {
            // 管理员
            return deviceDeadTreesMaintanceEntityMapper.getMaintenanceDataByManager1(user.getAdcode(), user.getTown(), condition, batch,searchtown,date,endDate, user.getUsername());
        } else if (role == 5) {
            return null;
        }
        return null;
    }

    public boolean report(List<Integer> ids) {
        for (Integer id: ids) {
            deviceDeadTreesMaintanceEntityMapper.reportData(id);
        }
        return true;
    }


    public List<Device_DeadTrees_maintanceEntity> getDeadTreesSummaryByCustomReigon(User user, Integer optionIndex, String searchText, String startDate, String endDate) {
        int role = user.getRole();

//        if (role <= 3) {
//            return deviceDeadTreesMaintanceEntityMapper.selectByCustomReigon(user.getUsername(), optionIndex, searchText, startDate, endDate,deadId);
//        }else
        if (role == 4) {
            return deviceDeadTreesMaintanceEntityMapper.selectDeadTreesByCustomReigonCustomProject(user.getParent(), optionIndex, searchText, startDate, endDate);
        }

        /*else if(){

        }*/
        return null;

    }







}
