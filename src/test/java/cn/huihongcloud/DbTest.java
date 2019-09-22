package cn.huihongcloud;

import cn.huihongcloud.entity.menu.MenuItem;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.MenuMapper;
import cn.huihongcloud.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/5/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testDb() {
        User user =userMapper.getUserByUserName("zhh");
        System.out.println(user.getName());
    }

    @Test
    public void testLogin() {
        System.out.println(userMapper.verifyUserByUserNameAndPassword("zhh", "123"));
    }

    @Test
    @Transactional
    public void testRegister() {
        User user = new User();
        user.setActive(true);
        user.setUsername("xx");
        user.setPassword("");
        user.setName("");
        user.setRole(0);
        userMapper.registerUser(user);
        User user1 = userMapper.getUserByUserName("xx");
        Assert.assertEquals(user.getName(), user1.getName());
        Assert.assertEquals(user.getActive(), user1.getActive());
    }

    @Test
    public void testSearchUserByString() {
        Page<Object> objects = PageHelper.offsetPage(1, 2);
        List<User> userList = userMapper.searchUserByString("");
        System.out.println(objects.getTotal());
        System.out.println(objects.getPages());
        System.out.println(userList.size());
        System.out.println(userList.get(0).getName());
        Assert.assertTrue(userList.size() > 0);
    }

    @Test
    @Transactional
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("zhh");
        user.setEmail("zhh_2007@163.com");
        userMapper.updateUser(user);
        user = userMapper.getUserByUserName("zhh");
        System.out.println(user.getEmail());
        Assert.assertNotNull(user.getEmail());
    }

    @Test
    public void testGetUserByAdcodeAndTownAndString() {
        List<User> userList = userMapper.getUserByAdcodeAndTownAndStringAndUserRole("350681", "石码镇", "", 0, null, null, null);
        System.out.println(userList.get(0).getName());
        Assert.assertTrue(userList.size() > 1);
    }

    @Test
    public void testGetMenuById() {
        MenuItem menuItem = menuMapper.getMenuItemById(0);
        System.out.println(menuItem.getParent());
    }

    @Test
    public void testGetMenuRelationByRoleId() {
        System.out.println(menuMapper.getMenuRelationByRoleId(0).size());
    }
}
