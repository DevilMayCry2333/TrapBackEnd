package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.mapper.Device_DeadTrees_maintanceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeadTreeCutService {
    @Autowired
    Device_DeadTrees_maintanceEntityMapper deviceDeadTreesMaintanceEntityMapper;

    public List<Device_DeadTrees_maintanceEntity> selectAll(String username,int num1,int num2){
        return deviceDeadTreesMaintanceEntityMapper.selectAll(username, num1, num2);
    }

    public int countAll(String username){
        return deviceDeadTreesMaintanceEntityMapper.countAll(username);
    }

    public int countAllByArea(String adcode){
        return deviceDeadTreesMaintanceEntityMapper.countAllByArea(adcode);
    }

    public List<Device_DeadTrees_maintanceEntity> selectAllByArea(String adcode,int num1,int num2){
        return deviceDeadTreesMaintanceEntityMapper.selectAllByArea(adcode, num1, num2);
    }

    public List<Device_DeadTrees_maintanceEntity> selectByDateAndColSearch(String username,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode){
        return deviceDeadTreesMaintanceEntityMapper.selectByDateAndColSearch(username, startDate, endDate, colName, searchText, num1, num2,adcode);
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

}
