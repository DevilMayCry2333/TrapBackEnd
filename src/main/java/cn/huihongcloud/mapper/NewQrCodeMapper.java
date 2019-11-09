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
    int assginDeviceByManager(String appval,long id,long scanId,String CustomRegion,String prefix,long serial,String username,String manager,String city,String area,String adcode);

    List<Device> getMaxAvaDevice(String province);

    List<Device> getMaxDeviceId(String adcode,String appval);

    List<Device> getMaxScanId(String province);

    int deleteScanId(String scanId);

    int updateScanId(String customProject,String customSerial,String scanId);



    List<Device> selectByConditions(String username,String colName,String searchText,int num1,int num2);

    List<Device> selectByConditions1(String colName,String searchText,int num1,int num2);

    int countByCond(String username,String colName,String searchText);

    int countByCond1(String colName,String searchText);
    int countByNOassign(String isManagerAssign,String province);


}
