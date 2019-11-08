package cn.huihongcloud.entity.beetle;

import lombok.Data;

/**
 * Created by 钟晖宏 on 2019/1/16
 */
@Data
public class BeetleInfo {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
