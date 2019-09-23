package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
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

    public int countAllByArea(String username){
        return deviceDeadTreesMaintanceEntityMapper.countAllByArea(username);
    }

    public List<Device_DeadTrees_maintanceEntity> selectAllByArea(String username,int num1,int num2){
        return deviceDeadTreesMaintanceEntityMapper.selectAllByArea(username, num1, num2);
    }

    public List<Device_DeadTrees_maintanceEntity> selectByDateAndColSearch(String username,String startDate,String endDate,String colName,String searchText,Integer num1,Integer num2,String adcode){
        return deviceDeadTreesMaintanceEntityMapper.selectByDateAndColSearch(username, startDate, endDate, colName, searchText, num1, num2,adcode);
    }
}
