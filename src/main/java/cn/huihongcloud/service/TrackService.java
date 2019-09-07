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
}
