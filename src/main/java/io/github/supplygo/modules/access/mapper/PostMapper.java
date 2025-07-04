package io.github.supplygo.modules.access.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import io.github.supplygo.modules.access.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 岗位Mapper接口
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 分页查询岗位列表
     */
    Page<Post> selectPostPage(@Param("page") Page<Post> page, @Param("query") Map<String, Object> query);

    /**
     * 根据编号查询岗位
     */
    Post selectByCode(@Param("code") String code);

    /**
     * 根据用户ID查询岗位
     */
    Post selectPostByUserId(@Param("userId") Long userId);

    /**
     * 检查岗位下是否有用户
     */
    Long countUsersByPostId(@Param("postId") Long postId);

    /**
     * 查询所有启用的岗位
     */
    List<Post> selectEnabledPosts();
} 