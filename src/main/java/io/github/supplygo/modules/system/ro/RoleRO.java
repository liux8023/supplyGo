package io.github.supplygo.modules.system.ro;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色响应对象
 */
public class RoleRO {

    /**
     * 角色详情
     */
    public record Detail(
            String id,
            String tenantId,
            String code,
            String name,
            String description,
            Integer dataScope,
            String dataScopeName,
            Integer sort,
            Integer status,
            LocalDateTime createTime,
            LocalDateTime updateTime,
            List<Long> menuIds,
            List<Long> deptIds
    ) {}

    /**
     * 角色列表项
     */
    public record ListItem(
            String id,
            String tenantId,
            String code,
            String name,
            String description,
            Integer dataScope,
            String dataScopeName,
            Integer sort,
            Integer status,
            LocalDateTime createTime,
            LocalDateTime updateTime,
            Long userCount
    ) {}
} 