package io.github.supplygo.modules.access.ro;

import java.time.LocalDateTime;

/**
 * 岗位响应对象
 */
public class PostRO {

    /**
     * 岗位列表/详情项
     */
    public record ListItem(
            String id,
            String name,
            String code,
            Integer sort,
            Integer status,
            LocalDateTime createTime,
            LocalDateTime updateTime
    ) {}
} 