package cn.huihongcloud.controller.injection;

import cn.huihongcloud.entity.region.Node;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class InjectDist {
    @Autowired
    private UserService userService;

    @Autowired @Qualifier("provinces")
    private String DryProvinces;

    @Autowired @Qualifier("provinces_list")
    private List<Node> DryProvinces_list;

    @Autowired @Qualifier("cities")
    private Map<String, List<Node>> DryCities;

    @Autowired @Qualifier("areas")
    private Map<String, List<Node>> DryAreas;

    @Autowired @Qualifier("streets")
    private Map<String, List<Node>> DryStreets;

    @RequestMapping(value = "DryCities", method = RequestMethod.GET)
    @ApiOperation("获取城市列表")
    public List<Node> cities(@RequestParam("id") String id) {
        return DryCities.get(id);
    }

    @RequestMapping(value = "DryProvinces", method = RequestMethod.GET)
    @ApiOperation("获取所有的省")
    public String provinces() {
        return DryProvinces;
    }

    @RequestMapping(value = "DryAreas", method = RequestMethod.GET)
    @ApiOperation("获取县列表")
    public List<Node> areas(@RequestParam("id") String id) {
        return DryAreas.get(id);
    }

    @RequestMapping(value = "DryStreets", method = RequestMethod.GET)
    @ApiOperation("获取乡列表")
    public List<Node> streets(@RequestParam("id") String id) {
        return DryStreets.get(id);
    }

    @GetMapping("/DryInject/dist/provinces")
    public List<Node> getProvincesByUser(@RequestAttribute String username) {
        User user = userService.getUserByUserName(username);
        if (user.getProvince() != null && !user.getProvince().isEmpty()) {
            String province = user.getProvince();
            List<Node> ret = new ArrayList<>();
            for (Node item: DryProvinces_list) {
                if (item.getName().equals(province)) {
                    ret.add(item);
                    break;
                }
            }
            return ret;
        }
        return DryProvinces_list;
    }

    @GetMapping("/DryInject/dist/cities")
    public List<Node> getCitiesByUser(@RequestAttribute String username, String id) {
        List<Node> mCities = DryCities.get(id);
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

    @GetMapping("/DryInject/dist/areas")
    public List<Node> getAreasByUser(@RequestAttribute String username, String id) {
        List<Node> mAreas = DryAreas.get(id);
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

    @GetMapping("/DryInject/dist/streets")
    public List<Node> getStreetsByUser(@RequestAttribute String username, String id) {
        List<Node> mStreets = DryStreets.get(id);
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
