package cn.huihongcloud.entity.dict;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by 钟晖宏 on 2018/10/1
 */
@Data
@TableName("dict")
public class Dict {

    private Integer id;

    @TableField("`key`")
    private String key;

    @TableField("`value`")
    private String value;

}
