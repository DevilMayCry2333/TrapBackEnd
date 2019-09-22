package cn.huihongcloud.mapper;

import cn.huihongcloud.entity.menu.MenuItem;
import cn.huihongcloud.entity.menu.MenuRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 钟晖宏 on 2018/5/8
 */
@Repository
public interface MenuMapper {

    MenuItem getMenuItemById(int id);
    List<MenuRelation> getMenuRelationByRoleId(@Param("roleId") int roleId);

}
