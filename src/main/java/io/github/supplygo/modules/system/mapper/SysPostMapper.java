package io.github.supplygo.modules.system.mapper;

import io.github.supplygo.modules.system.entity.SysPost;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 岗位Mapper接口
 */
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {

    /**
     * 分页查询岗位列表
     */
    Page<SysPost> selectPostPage(@Param("page") Page<SysPost> page, @Param("query") Map<String, Object> query);

    /**
     * 根据编号查询岗位
     */
    SysPost selectByCode(@Param("code") String code);

    /**
     * 根据用户ID查询岗位
     */
    SysPost selectPostByUserId(@Param("userId") Long userId);

    /**
     * 检查岗位下是否有用户
     */
    Long countUsersByPostId(@Param("postId") Long postId);

    /**
     * 查询所有启用的岗位
     */
    List<SysPost> selectEnabledPosts();
} 