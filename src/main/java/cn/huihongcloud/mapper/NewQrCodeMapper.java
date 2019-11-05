package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewQrCodeMapper {
    List<User> getProxy();
    List<User> getCity(String adcode);
    List<User> getArea(String adcode);
    List<User> getProxyByCode(String adcode);
    int insertDevice(String id,String proxy);
    int assginDeviceByManager(String appval,long id,long scanId,String CustomRegion,String prefix,long serial,String username,String manager);

    List<Device> getMaxAvaDevice(String province);

    List<Device> getMaxDeviceId(String adcode,String appval);

    List<Device> selectByConditions(String colName,String searchText,int num1,int num2);

    int countByCond(String colName,String searchText);

}
