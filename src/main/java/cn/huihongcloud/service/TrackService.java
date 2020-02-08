package cn.huihongcloud.service;

import cn.huihongcloud.entity.DeviceTrackMap;
import cn.huihongcloud.entity.Device_Track_MaintanceEntity;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.mapper.Device_Track_MaintanceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService {
    @Autowired
    Device_Track_MaintanceEntityMapper deviceTrackMaintanceEntityMapper;

    public List<Device_Track_MaintanceEntity> selectAll(String username,int num1,int num2){
        return deviceTrackMaintanceEntityMapper.selectAll(username, num1, num2);
    }

    public int countAll(String username,String startDate,String endDate,String colName,String searchText){
        return deviceTrackMaintanceEntityMapper.countAll(username,startDate,endDate,colName,searchText);
    }

    public int countAllArea(String username){
        return deviceTrackMaintanceEntityMapper.countAllByArea(username);
    }

    public List<Device_Track_MaintanceEntity> selectAllByArea(String adcode,int num1,int num2){
        return deviceTrackMaintanceEntityMapper.selectAllByArea(adcode, num1, num2);
    }
    public List<Device_Track_MaintanceEntity> selectByDateAndColSearch(String username,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode){
        return deviceTrackMaintanceEntityMapper.selectByDateAndColSearch(username, startDate, endDate, colName, searchText, num1, num2,adcode);
    }

    public List<Device_Track_MaintanceEntity> selectAllByAdcode(String adcode,int num1,int num2){
        return deviceTrackMaintanceEntityMapper.selectAllByAdcode(adcode, num1, num2);
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
            deviceList = deviceTrackMaintanceEntityMapper.getDeviceByLocation(adcode, town, searchText);
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
    public List<DeviceTrackMap> getDeviceByManager(String manager,boolean isUseAdcode,String adcode,String detail,String lineName) {
        List<Device_Track_MaintanceEntity> deviceList = null;
        List<DeviceTrackMap> realData = new ArrayList<>();

            if(isUseAdcode){
                deviceList = deviceTrackMaintanceEntityMapper.getDeviceByAdcode(adcode);
            }else{
                if(detail.equals("detail")){
                    deviceList = deviceTrackMaintanceEntityMapper.getDeviceByManagerAndByLineName(manager,lineName);
                }else {
                    deviceList = deviceTrackMaintanceEntityMapper.getDeviceByManager(manager);
                }

            }

            for (Device_Track_MaintanceEntity d:
                 deviceList) {
                String latspilt[] = d.getLatitudeCollect().split(",");
                String longspilt[] = d.getLongtitudeCollect().split(",");

                for (int i = 0; i < latspilt.length; i++) {


                    try {
                        DeviceTrackMap deviceTrackMap = new DeviceTrackMap();
                        deviceTrackMap.setLongitude(Double.valueOf(longspilt[i]));
                        deviceTrackMap.setLatitude(Double.valueOf(latspilt[i]));
                        deviceTrackMap.setLinename(d.getLinename());
                        realData.add(deviceTrackMap);
                    }catch (Exception e){

                    }
                }
            }
        return realData;
    }

    public List<Device> getDeviceByWorker(String worker) {
        List<Device> deviceList = null;
        try {
            deviceList = deviceTrackMaintanceEntityMapper.getDeviceByWorker(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceList;
    }



}
