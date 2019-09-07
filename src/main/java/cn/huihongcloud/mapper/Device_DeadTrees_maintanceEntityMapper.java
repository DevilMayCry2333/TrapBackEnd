package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_DeadTrees_maintanceEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface Device_DeadTrees_maintanceEntityMapper {

    List<Device_DeadTrees_maintanceEntity> selectById(BigInteger id);

    int insert(Device_DeadTrees_maintanceEntity record);

    int insertSelective(Device_DeadTrees_maintanceEntity record);
}