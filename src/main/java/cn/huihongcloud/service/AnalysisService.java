package cn.huihongcloud.service;

import cn.huihongcloud.mapper.AnalysisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟晖宏 on 2018/10/3
 */
@Service
public class AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    public Object getSubDist(String username) {

        return null;
    }

    public Object getAnalysisData(String adcode, String town, int year) {
        List<Map<String, Object>> monthlyData = analysisMapper.getMonthlyDataByAdcodeTownAndYear(adcode, town, year);
        System.out.println(monthlyData);
        if (monthlyData.size() == 0)
            return null;
        double mean = 0;
        int firstVal = Integer.parseInt(monthlyData.get(0).get("value").toString());
        int max = firstVal;
        int min = firstVal;
        String max_month = monthlyData.get(0).get("key").toString();
        String min_month = monthlyData.get(0).get("key").toString();
        for (Map<String, Object> map: monthlyData) {
            int val = Integer.parseInt(map.get("value").toString());
            mean += val;
            if (max < val) {
                max = val;
                max_month = map.get("key").toString();
            }
            if (min > val) {
                min = val;
                min_month = map.get("key").toString();
            }
        }
        mean /= monthlyData.size();

        double sig = 0;
        for (Map<String, Object> map: monthlyData) {
            sig += Math.pow(mean - Integer.parseInt(map.get("value").toString()), 2);
        }
        double var = sig / monthlyData.size();
        Map<String, Object> ret = new HashMap<>();
        ret.put("min", min);
        ret.put("max", max);
        ret.put("max_month", max_month);
        ret.put("min_month", min_month);
        ret.put("monthlyData", monthlyData);
        ret.put("var", var);
        ret.put("mean", mean);
        return ret;
    }
}
