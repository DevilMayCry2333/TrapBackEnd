package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_NaturalEnemies_maintanceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Device_NaturalEnemies_maintanceEntityMapper {

    List<Device_NaturalEnemies_maintanceEntity> selectAll(String username,int num1,int num2);

    int countAll(String username);

    int insert(Device_NaturalEnemies_maintanceEntity record);

    int insertSelective(Device_NaturalEnemies_maintanceEntity record);
}