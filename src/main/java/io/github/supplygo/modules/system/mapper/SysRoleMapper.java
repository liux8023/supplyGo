package io.github.supplygo.modules.system.mapper;

import io.github.supplygo.modules.system.entity.SysRole;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色Mapper接口
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 分页查询角色列表
     */
    Page<SysRole> selectRolePage(@Param("page") Page<SysRole> page, @Param("query") Map<String, Object> query);

    /**
     * 根据用户ID查询角色列表
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 根据角色编号查询角色
     */
    SysRole selectByCode(@Param("code") String code);

    /**
     * 查询所有启用的角色
     */
    List<SysRole> selectEnabledRoles();

    /**
     * 为角色分配菜单权限
     */
    int assignMenus(@Param("roleId") Long roleId, @Param("menuIds") List<Long> menuIds);

    /**
     * 为角色分配数据权限
     */
    int assignDataScope(@Param("roleId") Long roleId, @Param("dataScope") Integer dataScope, @Param("deptIds") List<Long> deptIds);
} 