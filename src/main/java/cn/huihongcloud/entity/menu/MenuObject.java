package cn.huihongcloud.entity.menu;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/5/13
 */
public class MenuObject {

    private int id;
    private String path;
    private String name;
    private List<MenuObject> subMenus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuObject> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuObject> subMenus) {
        this.subMenus = subMenus;
    }
}
