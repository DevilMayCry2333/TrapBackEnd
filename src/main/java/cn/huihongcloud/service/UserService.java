package cn.huihongcloud.service;

import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.UserMapper;
import cn.huihongcloud.util.DistUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/7/14
 */
@Service
public class UserService {

    @Autowired
    private DistUtil distUtil;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名获取用户对象
     *
     * @param username 用户名
     * @return 用户对象
     */
    public User getUserByUserName(String username) {
        User user = null;
        try {
            user = userMapper.getUserByUserName(username);
            user.setPassword(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Boolean nonActiveDevice(String username) {
        Boolean result = false;
        try {
            if (userMapper.nonActiveDevice(username) == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean ActiveDevice(String username) {
        Boolean result = false;
        try {
            if (userMapper.nonActiveDevice(username) == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 是否更新成功
     */
    public Boolean updateUser(User user) {
        Boolean result = false;
        try {
            if (userMapper.updateUser(user) == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> getUserByAdcodeAndTownAndStringAndUserRole(String adcode, String town, String searchText, Integer role,
                                                                 Integer roleType, Boolean active, String condition) {
        return userMapper.getUserByAdcodeAndTownAndStringAndUserRole(adcode, town, searchText, role, roleType, active, condition);
    }

    public Boolean forbitToUse(User user) {
        Boolean result = false;
        try {
            if (userMapper.forbitToUse(user) == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> listUserByArea(String province, String city, String area) {
        return userMapper.listUserByArea(province, city, area);
    }

    /**
     * 注册用户
     *
     * @param user 用户对象
     * @return 是否注册成功
     */
    public Boolean registerUser(User user) {
        Boolean result = false;
        try {
            if (userMapper.registerUser(user) == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除用户
     *
     * @param username 用户名
     * @return 是否删除成功
     */
    public Boolean deleteUserByUsername(String username) {
        Boolean result = false;
        try {
            if (userMapper.deleteUserByUsername(username) == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private boolean compareRole(User userA, User userB) {
        return userA.getRole() < userB.getRole();
    }

    public boolean verifyUser(User userA, User userB) {
        // 上级管理员增加下级管理用户角色相同 不过是地区在下级。
        return (compareRole(userA, userB) ||
                (userA.getRole().equals(userB.getRole()) && distUtil.underDist(userA, userB)));
    }

    @Transactional
    public void changePassword(String username, String currentPassword, String newPassword) throws Exception {
        if (userMapper.changePassword(username, currentPassword, newPassword) != 1) {
            throw new Exception("修改失败");
        }
    }

    public void resetPassword(String username) {
        userMapper.resetPassword(username);
    }

    public List<User> getWorkerInRegion(String adcode, String town) {
        return userMapper.listWorkerInRegion(adcode, town);
    }

    public List<User> getCurrentAssociatedUser(String deviceId) {
        return userMapper.getCurrentAssociatedUser(deviceId);
    }

    public List<User> getCanAssociatedUser(String deviceId) {
        return userMapper.getCanAssociatedUser(deviceId);
    }

    public List<User> getProjectsByAdCode(String adcode) {
        return userMapper.getProjectsByAdCode(adcode);
    }
}
