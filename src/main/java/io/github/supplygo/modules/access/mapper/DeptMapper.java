package io.github.supplygo.modules.access.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import io.github.supplygo.modules.access.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门Mapper接口
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 分页查询部门列表
     */
    Page<Dept> selectDeptPage(@Param("page") Page<Dept> page, @Param("query") Map<String, Object> query);

    /**
     * 根据父级ID查询子部门列表
     */
    List<Dept> selectByParentId(@Param("parentId") Long parentId);

    /**
     * 查询部门树结构
     */
    List<Dept> selectDeptTree(@Param("parentId") Long parentId);

    /**
     * 根据编号查询部门
     */
    Dept selectByCode(@Param("code") String code);

    /**
     * 查询部门及其子部门ID列表
     */
    List<Long> selectDeptAndSubDeptIds(@Param("deptId") Long deptId);

    /**
     * 根据用户ID查询所属部门
     */
    Dept selectDeptByUserId(@Param("userId") Long userId);

    /**
     * 查询根部门列表
     */
    List<Dept> selectRootDepts();

    /**
     * 检查部门下是否有用户
     */
    Long countUsersByDeptId(@Param("deptId") Long deptId);

    /**
     * 检查部门下是否有子部门
     */
    Long countSubDeptsByDeptId(@Param("deptId") Long deptId);
} 