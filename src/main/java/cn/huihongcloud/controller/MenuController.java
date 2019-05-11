package cn.huihongcloud.controller;

import cn.huihongcloud.entity.menu.MenuItem;
import cn.huihongcloud.entity.menu.MenuObject;
import cn.huihongcloud.entity.menu.MenuRelation;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.MenuMapper;
import cn.huihongcloud.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 钟晖宏 on 2018/5/13
 */
@RestController
@RequestMapping("/auth_api/menu")
@Api("MenuController API")
public class MenuController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    List<MenuObject> menuObjects;

    List<MenuItem> menuItems;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("获取菜单")
    public List<MenuObject> getMenu(@RequestAttribute("username") String username) {
        User currentUser = userMapper.getUserByUserName(username);
        List<MenuRelation> menuRelations = menuMapper.getMenuRelationByRoleId(currentUser.getRole());
        menuItems = new ArrayList<>();
        for (MenuRelation menuRelation : menuRelations) {
            menuItems.add(menuMapper.getMenuItemById(menuRelation.getMenuId()));
        }
        menuObjects = new ArrayList<>();
        makeMenu(null, -1);
        return menuObjects;
    }

    private void makeMenu(MenuObject root, int parentId) {
        if (root == null && parentId == -1) {
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getParent() == null) {
                    MenuObject menuObject = new MenuObject();
                    menuObject.setName(menuItem.getName());
                    menuObject.setPath(menuItem.getPath());
                    menuObject.setSubMenus(new ArrayList<>());
                    menuObject.setId(menuItem.getId());
                    menuObjects.add(menuObject);
                    makeMenu(menuObject, menuObject.getId());
                }
            }
        } else {
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getParent() != null && menuItem.getParent() == parentId) {
                    MenuObject menuObject = new MenuObject();
                    menuObject.setName(menuItem.getName());
                    menuObject.setPath(menuItem.getPath());
                    menuObject.setSubMenus(new ArrayList<>());
                    menuObject.setId(menuItem.getId());
                    root.getSubMenus().add(menuObject);
                    makeMenu(menuObject, menuObject.getId());
                }
            }
        }
    }
}
