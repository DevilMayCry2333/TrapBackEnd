package cn.huihongcloud.service;

import cn.huihongcloud.entity.dict.Dict;
import cn.huihongcloud.mapper.DictMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 钟晖宏 on 2018/10/1
 */
@Service
public class DictService {

    @Autowired
    private DictMapper dictMapper;

    public void addDict() {
        Dict dict = new Dict();
        dict.setId(1);
        dict.setKey("xx");
        dict.setValue("yy");
        dictMapper.insert(dict);
    }

    public String getValueByKey(String key) {
        QueryWrapper<Dict> qw = new QueryWrapper<>();
        qw.eq("`key`", key);
        return dictMapper.selectOne(qw).getValue();
    }

    public void setValueByKey(String key, String value) {
        Dict dict = new Dict();
        dict.setValue(value);
        dictMapper.update(dict, new QueryWrapper<Dict>().eq("`key`", key));
    }
}
