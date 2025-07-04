package io.github.supplygo.modules.access.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import io.github.supplygo.modules.access.entity.DataScope;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据权限Mapper接口
 */
@Mapper
public interface DataScopeMapper extends BaseMapper<DataScope> {

    /**
     * 分页查询数据权限列表
     */
    Page<DataScope> selectDataScopePage(Page<DataScope> page, @Param("query") Map<String, Object> query);

    /**
     * 根据菜单ID查询数据权限列表
     */
    List<DataScope> selectByMenuId(@Param("menuId") Long menuId);

    /**
     * 根据资源编号查询数据权限
     */
    DataScope selectByResourceCode(@Param("resourceCode") String resourceCode);

    /**
     * 根据数据权限类型查询列表
     */
    List<DataScope> selectByScopeType(@Param("scopeType") Integer scopeType);

    /**
     * 根据用户ID查询数据权限配置
     */
    List<DataScope> selectDataScopesByUserId(@Param("userId") Long userId);

    /**
     * 根据角色ID列表查询数据权限配置
     */
    List<DataScope> selectDataScopesByRoleIds(@Param("roleIds") List<Long> roleIds);
} 