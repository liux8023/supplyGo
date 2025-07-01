package io.github.supplygo.modules.system.ro;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单响应对象
 */
public class MenuRO {

    /**
     * 菜单树/列表项
     */
    public record ListItem(
            String id,
            String name,
            String parentId,
            String parentName,
            String path,
            String component,
            String permission,
            String icon,
            Integer type,
            Integer sort,
            Integer status,
            LocalDateTime createTime,
            String code,
            List<ListItem> children
    ) {}
} 