package cn.huihongcloud.service;

import cn.huihongcloud.entity.region.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/11/25
 */
@Service
public class RegionService {

    @Autowired
    @Qualifier("provinces")
    private String provinces;

    @Autowired @Qualifier("provinces_list")
    private List<Node> provinces_list;

    @Autowired @Qualifier("cities")
    private Map<String, List<Node>> cities;

    @Autowired @Qualifier("areas")
    private Map<String, List<Node>> areas;

    @Autowired @Qualifier("streets")
    private Map<String, List<Node>> streets;

    public Map<String, String> convert(String towncode) {
        return null;
    }
}
