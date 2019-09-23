package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_Track_MaintanceEntity;
import cn.huihongcloud.mapper.Device_Track_MaintanceEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {
    @Autowired
    Device_Track_MaintanceEntityMapper deviceTrackMaintanceEntityMapper;

    public List<Device_Track_MaintanceEntity> selectAll(String username,int num1,int num2){
        return deviceTrackMaintanceEntityMapper.selectAll(username, num1, num2);
    }

    public int countAll(String username){
        return deviceTrackMaintanceEntityMapper.countAll(username);
    }

    public int countAllArea(String username){
        return deviceTrackMaintanceEntityMapper.countAllByArea(username);
    }

    public List<Device_Track_MaintanceEntity> selectAllByArea(String username,int num1,int num2){
        return deviceTrackMaintanceEntityMapper.selectAllByArea(username, num1, num2);
    }
    public List<Device_Track_MaintanceEntity> selectByDateAndColSearch(String username,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode){
        return deviceTrackMaintanceEntityMapper.selectByDateAndColSearch(username, startDate, endDate, colName, searchText, num1, num2,adcode);
    }

    public List<Device_Track_MaintanceEntity> selectAllByAdcode(String adcode,int num1,int num2){
        return deviceTrackMaintanceEntityMapper.selectAllByAdcode(adcode, num1, num2);
    }

}
