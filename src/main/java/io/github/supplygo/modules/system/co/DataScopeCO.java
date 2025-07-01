package io.github.supplygo.modules.system.co;

public class DataScopeCO {

    public record Query(
            Long menuId,
            String resourceCode,
            String scopeName,
            Integer scopeType,
            Integer status,
            Long current,
            Long size
    ) {
        public Query {
            if (current == null) current = 1L;
            if (size == null) size = 10L;
        }
    }
} 