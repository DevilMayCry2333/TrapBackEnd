package cn.huihongcloud.controller;

import cn.huihongcloud.entity.region.Node;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/4/26.
 */
@RestController
@Api("DistController API")
public class DistController {

    @Autowired
    private UserService userService;

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

    @RequestMapping(value = "cities", method = RequestMethod.GET)
    @ApiOperation("获取城市列表")
    public List<Node> cities(@RequestParam("id") String id) {
        return cities.get(id);
    }

    @RequestMapping(value = "provinces", method = RequestMethod.GET)
    @ApiOperation("获取所有的省")
    public String provinces() {
        return provinces;
    }

    @RequestMapping(value = "areas", method = RequestMethod.GET)
    @ApiOperation("获取县列表")
    public List<Node> areas(@RequestParam("id") String id) {
        return areas.get(id);
    }

    @RequestMapping(value = "streets", method = RequestMethod.GET)
    @ApiOperation("获取乡列表")
    public List<Node> streets(@RequestParam("id") String id) {
        return streets.get(id);
    }

    @GetMapping("/auth_api/dist/provinces")
    public List<Node> getProvincesByUser(@RequestAttribute String username) {
        User user = userService.getUserByUserName(username);
        if (user.getProvince() != null && !user.getProvince().isEmpty()) {
            String province = user.getProvince();
            List<Node> ret = new ArrayList<>();
            for (Node item: provinces_list) {
                if (item.getName().equals(province)) {
                    ret.add(item);
                    break;
                }
            }
            return ret;
        }
        return provinces_list;
    }

    @GetMapping("/auth_api/dist/cities")
    public List<Node> getCitiesByUser(@RequestAttribute String username, String id) {
        List<Node> mCities = cities.get(id);
        User user = userService.getUserByUserName(username);
        if (user.getCity() != null && !user.getCity().isEmpty()) {
            String city = user.getCity();
            List<Node> ret = new ArrayList<>();
            for (Node item: mCities) {
                if (item.getName().equals(city)) {
                    ret.add(item);
                    break;
                }
            }
            return ret;
        }
        return mCities;
    }

    @GetMapping("/auth_api/dist/areas")
    public List<Node> getAreasByUser(@RequestAttribute String username, String id) {
        List<Node> mAreas = areas.get(id);
        User user = userService.getUserByUserName(username);
        if (user.getArea() != null && !user.getArea().isEmpty()) {
            String area = user.getArea();
            List<Node> ret = new ArrayList<>();
            for (Node item: mAreas) {
                if (item.getName().equals(area)) {
                    ret.add(item);
                    break;
                }
            }
            return ret;
        }
        return mAreas;
    }

    @GetMapping("/auth_api/dist/streets")
    public List<Node> getStreetsByUser(@RequestAttribute String username, String id) {
        List<Node> mStreets = streets.get(id);
        User user = userService.getUserByUserName(username);
        if (user.getTown() != null && !user.getTown().isEmpty()) {
            String town = user.getTown();
            List<Node> ret = new ArrayList<>();
            for (Node item: mStreets) {
                if (item.getName().equals(town)) {
                    ret.add(item);
                    break;
                }
            }
            return ret;
        }
        return mStreets;
    }

}
