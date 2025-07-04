package io.github.supplygo.modules.access.mapper;

import com.mybatisflex.core.BaseMapper;
import io.github.supplygo.modules.access.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单Mapper
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色ID查询菜单列表
     */
    List<Menu> selectMenusByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据用户ID查询菜单列表
     */
    List<Menu> selectMenusByUserId(@Param("userId") Long userId);

    /**
     * 根据父级ID查询子菜单
     */
    List<Menu> selectMenusByParentId(@Param("parentId") Long parentId);
} 