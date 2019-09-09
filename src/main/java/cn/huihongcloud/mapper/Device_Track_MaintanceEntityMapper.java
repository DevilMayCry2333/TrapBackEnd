package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.Device_Track_MaintanceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Device_Track_MaintanceEntityMapper {

    List<Device_Track_MaintanceEntity> selectAll(String username,int num1,int num2);

    int countAll(String username);

    int insert(Device_Track_MaintanceEntity record);

    int insertSelective(Device_Track_MaintanceEntity record);

    int countAllByArea(String username);

    List<Device_Track_MaintanceEntity> selectAllByArea(String username,int num1,int num2);

}