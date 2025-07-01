package io.github.supplygo.modules.system.mapper;

import io.github.supplygo.modules.system.entity.SysDataScope;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据权限Mapper接口
 */
@Mapper
public interface SysDataScopeMapper extends BaseMapper<SysDataScope> {

    /**
     * 分页查询数据权限列表
     */
    Page<SysDataScope> selectDataScopePage(Page<SysDataScope> page, @Param("query") Map<String, Object> query);

    /**
     * 根据菜单ID查询数据权限列表
     */
    List<SysDataScope> selectByMenuId(@Param("menuId") Long menuId);

    /**
     * 根据资源编号查询数据权限
     */
    SysDataScope selectByResourceCode(@Param("resourceCode") String resourceCode);

    /**
     * 根据数据权限类型查询列表
     */
    List<SysDataScope> selectByScopeType(@Param("scopeType") Integer scopeType);

    /**
     * 根据用户ID查询数据权限配置
     */
    List<SysDataScope> selectDataScopesByUserId(@Param("userId") Long userId);

    /**
     * 根据角色ID列表查询数据权限配置
     */
    List<SysDataScope> selectDataScopesByRoleIds(@Param("roleIds") List<Long> roleIds);
} 