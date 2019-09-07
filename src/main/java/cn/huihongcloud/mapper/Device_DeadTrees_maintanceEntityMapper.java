package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface Device_DeadTrees_maintanceEntityMapper {

    int countAll(String username);

    List<Device_DeadTrees_maintanceEntity> selectAll(String username,int num1,int num2);


    int insert(Device_DeadTrees_maintanceEntity record);

    int insertSelective(Device_DeadTrees_maintanceEntity record);
}