package io.github.supplygo.modules.system.mapper;

import io.github.supplygo.modules.system.entity.SysUser;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户Mapper接口
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 分页查询用户列表
     */
    Page<SysUser> selectUserPage(@Param("page") Page<SysUser> page, @Param("query") Map<String, Object> query);

    /**
     * 根据账号查询用户
     */
    SysUser selectByAccount(@Param("account") String account);

    /**
     * 根据用户ID查询用户详情（包含角色、部门、岗位信息）
     */
    SysUser selectUserDetail(@Param("id") Long id);

    /**
     * 根据部门ID查询用户列表
     */
    List<SysUser> selectByDeptId(@Param("deptId") Long deptId);

    /**
     * 根据角色ID查询用户列表
     */
    List<SysUser> selectByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据岗位ID查询用户列表
     */
    List<SysUser> selectByPostId(@Param("postId") Long postId);

    /**
     * 根据数据权限查询用户列表
     */
    List<SysUser> selectUsersByDataScope(@Param("userId") Long userId, @Param("deptIds") List<Long> deptIds);

    /**
     * 根据用户ID查询角色ID列表
     */
    List<Long> selectRoleIdsByUserId(@Param("userId") Long userId);

    /**
     * 根据角色ID列表查询数据权限配置
     */
    List<Integer> selectDataScopesByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 查询所有部门ID
     */
    List<Long> selectAllDeptIds();

    /**
     * 根据角色ID列表查询自定义部门ID
     */
    List<Long> selectCustomDeptIdsByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据用户ID查询当前部门ID
     */
    Long selectDeptIdByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询当前部门及以下部门ID
     */
    List<Long> selectDeptAndSubDeptIdsByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询部门ID列表（用于数据权限）
     */
    List<Long> selectDeptIdsByUserId(@Param("userId") Long userId);
} 