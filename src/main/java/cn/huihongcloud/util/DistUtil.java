package cn.huihongcloud.util;

import cn.huihongcloud.entity.region.Node;
import cn.huihongcloud.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/5/13
 *
 *
 *
 */
@Component
public class DistUtil {

    @Autowired @Qualifier("provinces")
    private String provinces;

    @Autowired @Qualifier("provinces_list")
    private List<Node> provinces_list;

    @Autowired @Qualifier("cities")
    private Map<String, List<Node>> cities;

    @Autowired @Qualifier("areas")
    private Map<String, List<Node>> areas;

    @Autowired @Qualifier("streets")
    private Map<String, List<Node>> streets;

    /**
     * 分割adcode
     * @param adcode
     * @return
     */
    public String[] splitAdcode(String adcode) {
        String[] ret = new String[3];
        if (adcode.length() >= 2)
            ret[0] = adcode.substring(0, 2);
        if (adcode.length() >= 4)
            ret[1] = adcode.substring(0, 4);
        if (adcode.length() >= 6)
            ret[2] = adcode.substring(0, 6);
        return ret;
    }

    /**
     * 获取adcode + towncode的文字信息
     * @param adcode
     * @param towncode
     * @return
     */
    public String[] getNames(String adcode, String towncode) {
        String[] ret = new String[4];
        ret[0] = "";
        ret[1] = "";
        ret[2] = "";
        String[] splitedAdcode = splitAdcode(adcode);
        if (splitedAdcode[0] != null) {
            for (Node node : provinces_list) {
                if (node.getCode() == Integer.parseInt(splitedAdcode[0])) {
                    ret[0] = node.getName();
                    break;
                }
            }
        }
        if (splitedAdcode[1] != null) {
            List<Node> list = cities.get(splitedAdcode[0]);
            for (Node node : list) {
                if (node.getCode() == Integer.parseInt(splitedAdcode[1])) {
                    ret[1] = node.getName();
                    break;
                }
            }
        }
        if (splitedAdcode[2] != null) {
            List<Node> list = areas.get(splitedAdcode[1]);
            for (Node node : list) {
                if (node.getCode() == Integer.parseInt(splitedAdcode[2])) {
                    ret[2] = node.getName();
                    break;
                }
            }
        }
        if (towncode != null) {
            List<Node> list = streets.get(splitedAdcode[2]);
            for (Node node : list) {
                if (node.getCode() == Integer.parseInt(towncode)) {
                    ret[3] = node.getName();
                }
            }
        }
        return ret;
    }

    /**
     * 判断用户B是否是用户A的下级
     * @param userA
     * @param userB
     * @return
     */
    public boolean underDist(User userA, User userB) {
        boolean ret = true;
        //用户B的adcode不以用户A的adcode开头
        if (userA.getAdcode() != null && !userB.getAdcode().startsWith(userA.getAdcode())) {
            ret = false;
        }
        //用户A的adcode为空，且不为根用户
        if ((userA.getAdcode() == null || userA.getAdcode().isEmpty()) && !(userA.getRole() == 0)) {
            ret = false;
        }
        //用户A与B的adcode是否相等
        if (userA.getAdcode() != null && userB.getAdcode() != null && userA.getAdcode().equals(userB.getAdcode())) {
            //是否是不同的镇
            if (userA.getTown() != null && userB.getTown() != null && userA.getTown().equals(userB.getTown())) {
                ret = false;
            }
            //用户A属于town 用户B属于area
            if (userA.getTown() != null && userB.getTown() == null) {
                ret = false;
            }
        }
        return ret;
    }
}
