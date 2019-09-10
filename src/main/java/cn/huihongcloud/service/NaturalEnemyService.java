package cn.huihongcloud.service;

import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
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

    public int countAll(String username){
        return deviceNaturalEnemiesMaintanceEntityMapper.countAll(username);
    }

    public int countAllByArea(String username){
        return deviceNaturalEnemiesMaintanceEntityMapper.countAllByArea(username);
    }

    public List<Device_NaturalEnemies_maintanceEntity> selectAllByArea(String username,int num1,int num2){
        return deviceNaturalEnemiesMaintanceEntityMapper.selectAllByArea(username, num1, num2);
    }
}
