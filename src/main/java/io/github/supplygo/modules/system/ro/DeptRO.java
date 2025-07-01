package io.github.supplygo.modules.system.ro;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门响应对象
 */
public class DeptRO {

    /**
     * 部门树/列表项
     */
    public record ListItem(
            String id,
            String parentId,
            String name,
            String code,
            Integer sort,
            Integer status,
            String parentName,
            LocalDateTime createTime,
            LocalDateTime updateTime,
            List<ListItem> children
    ) {}
} 