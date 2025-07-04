package io.github.supplygo.modules.access.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import io.github.supplygo.modules.access.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户Mapper接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询用户列表
     */
    Page<User> selectUserPage(@Param("page") Page<User> page, @Param("query") Map<String, Object> query);

    /**
     * 根据账号查询用户
     */
    User selectByAccount(@Param("account") String account);

    /**
     * 根据用户ID查询用户详情（包含角色、部门、岗位信息）
     */
    User selectUserDetail(@Param("id") Long id);

    /**
     * 根据部门ID查询用户列表
     */
    List<User> selectByDeptId(@Param("deptId") Long deptId);

    /**
     * 根据角色ID查询用户列表
     */
    List<User> selectByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据岗位ID查询用户列表
     */
    List<User> selectByPostId(@Param("postId") Long postId);

    /**
     * 根据数据权限查询用户列表
     */
    List<User> selectUsersByDataScope(@Param("userId") Long userId, @Param("deptIds") List<Long> deptIds);

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