package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/5/6
 */
@Repository
public interface UserMapper {

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

}
