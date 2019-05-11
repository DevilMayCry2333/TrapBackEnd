package cn.huihongcloud.entity.menu;

/**
 * Created by 钟晖宏 on 2018/5/12
 */
public class MenuRelation {

    private int id;
    private int menuId;
    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
