package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.device.DeviceImg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/8/9
 */
@Repository
public interface DeviceImgMapper {

    int insert(DeviceImg deviceImg);

    int insert2(DeviceImg deviceImg);


    List<DeviceImg> selectByDeviceId(String deviceId);

    String selectImgNameByImgName(String imgName);
}
