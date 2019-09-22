package cn.huihongcloud;

import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.util.DistUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 钟晖宏 on 2018/5/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistTest {
    @Autowired
    DistUtil distUtil;
    @Test
    public void testAdcode() {
        System.out.println(distUtil);
        String ret[] = distUtil.getNames("350681", "350681100");
        for (String s : ret) {
            System.out.println(s);
        }
    }

    @Test
    public void testUnder() {
        User userA = new User();
        User userB = new User();
        userA.setAdcode("350681");
        userA.setTown("榜山镇");
        userB.setTown(null);
        userB.setAdcode("3506");
        Assert.assertFalse(distUtil.underDist(userA, userB));
    }
}
