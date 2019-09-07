package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_Injection_maintanceEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface Device_Injection_maintanceEntityMapper {

    int CountAll(String username);

    List<Device_Injection_maintanceEntity> selectAll(int page1,int page2,String username);

    List<Device_Injection_maintanceEntity> selectById(BigInteger id);

    int insert(Device_Injection_maintanceEntity record);

    int insertSelective(Device_Injection_maintanceEntity record);
}