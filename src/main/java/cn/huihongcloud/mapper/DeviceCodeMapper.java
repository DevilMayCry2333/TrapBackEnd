package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.device.DeviceCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/11/11
 */
@Repository
public interface DeviceCodeMapper {

    List<DeviceCode> findById(String id);

    Long deviceCount();

}
