package cn.huihongcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 钟晖宏 on 2018/7/22
 */
@RestController
@RequestMapping("js/a/sys")
public class TestController {

    @RequestMapping("/office/treeData")
    public String treeData() {
        return "[{\"name\":\"济南公司\",\"pId\":\"SD\",\"id\":\"SDJN\",\"title\":\"山东济南公司\"},{\"name\":\"企管部\",\"pId\":\"SDJN\",\"id\":\"SDJN01\",\"title\":\"山东济南企管部\"},{\"name\":\"企管部\",\"pId\":\"SDQD\",\"id\":\"SDQD01\",\"title\":\"山东青岛企管部\"},{\"name\":\"山东公司\",\"pId\":\"0\",\"id\":\"SD\",\"title\":\"山东公司\"},{\"name\":\"财务部\",\"pId\":\"SDJN\",\"id\":\"SDJN02\",\"title\":\"山东济南财务部\"},{\"name\":\"青岛公司\",\"pId\":\"SD\",\"id\":\"SDQD\",\"title\":\"山东青岛公司\"},{\"name\":\"财务部\",\"pId\":\"SDQD\",\"id\":\"SDQD02\",\"title\":\"山东青岛财务部\"},{\"name\":\"研发部\",\"pId\":\"SDJN\",\"id\":\"SDJN03\",\"title\":\"山东济南研发部\"},{\"name\":\"研发部\",\"pId\":\"SDQD\",\"id\":\"SDQD03\",\"title\":\"山东青岛研发部\"}]";
    }
}
