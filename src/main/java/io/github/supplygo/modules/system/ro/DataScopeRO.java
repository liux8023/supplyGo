package io.github.supplygo.modules.system.ro;

import java.time.LocalDateTime;

public class DataScopeRO {

    public record ListItem(
            String id,
            String menuId,
            String resourceCode,
            String scopeName,
            String scopeField,
            String scopeClass,
            String scopeColumn,
            Integer scopeType,
            String scopeValue,
            String remark,
            Integer status,
            LocalDateTime createTime,
            LocalDateTime updateTime
    ) {}
} 