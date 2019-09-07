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
}
