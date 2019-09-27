package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewQrCodeMapper {
    List<User> getProxy();
    List<User> getCity(String adcode);
    List<User> getArea(String adcode);
    List<User> getProxyByCode(String adcode);
    int insertDevice(String id,String proxy,String city,String area,String project,String adcode);
}