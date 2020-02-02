package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.GeoMappingEntity;
import cn.huihongcloud.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/5/6
 */
@Repository
public interface UserMapper {

    List<User> getProxyByProvzinces (String province);
    List<User> getProvinceByCode(String code);
    User getUserByUserName(String userName);
    int verifyUserByUserNameAndPassword(@Param("username") String userName, @Param("password") String password);
    int registerUser(@Param("user") User user);
    List<User> searchUserByString(@Param("searchText") String searchText);
    List<User> getProjectsByAdCode(@Param("adcode") String adcode);
    int updateUser(@Param("user") User user);
    int nonActiveDevice(@Param("username") String username);
    int ActiveDevice(@Param("username") String username);
    int forbitToUse(@Param("user") User user);
    List<User> getUserByAdcodeAndTownAndStringAndUserRole(@Param("adcode") String adcode, @Param("town") String town,
                                               @Param("searchText") String searchText, @Param("userRole") int userRole,
                                                          @Param("roleType") Integer roleType, @Param("active") Boolean active,
                                                          @Param("condition") String condition);
    int deleteUserByUsername(@Param("username") String username);

    int resetPassword(@Param("username") String username);

    int changePassword(@Param("username") String username, @Param("currentPassword") String currentPassword,
                       @Param("newPassword") String newPassword);

    List<User> listUserByArea(@Param("province") String province,@Param("city") String city,@Param("area")String area );
    List<User> listWorkerInRegion(@Param("adcode") String adcode, @Param("town") String town);
    List<User> getCurrentAssociatedUser(@Param("deviceId") String deviceId);
    List<User> getCanAssociatedUser(@Param("deviceId") String deviceId);

    List<User> getProjectUsersByAdcode(String adcode);

    List<User> getProjectAdminByAdcode(String adcode);

    int reportTrap(String customProject);

    int reportInject(String customProject);

    int reportEnemy(String customProject);

    int reportDead(String customProject);

    //修改用户状态
    int reportUser(String username);

    int reportTrack(String projectAdmin);

    //修改工人name和phone
    int updateUserNameAndPhone(String username, @Param("name") String name,@Param("phone") String phone,@Param("active") Boolean active);

    int countIfLayerExists(@Param("userid") String userid,@Param("module") String module);

    int insertLayerInfo(@Param("userid") String userid,@Param("module") String module,@Param("workname") String workname,@Param("layername") String layername);

    int updateLayerInfo(@Param("userid") String userid,@Param("module") String module,@Param("workname") String workname,@Param("layername") String layername);

    GeoMappingEntity getLayerInfo(@Param("userid") String userid,@Param("module") String module);



}
