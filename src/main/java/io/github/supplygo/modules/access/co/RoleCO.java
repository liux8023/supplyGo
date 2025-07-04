package io.github.supplygo.modules.access.co;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 角色命令对象
 */
public class RoleCO {

    public record Create(
            @NotBlank(message = "角色名称不能为空") String name,
            @NotBlank(message = "角色标识不能为空") String code,
            String description,
            Integer dataScope,
            Integer sort,
            Integer status
    ) {}

    public record Update(
            @NotNull(message = "角色ID不能为空") Long id,
            @NotBlank(message = "角色名称不能为空") String name,
            @NotBlank(message = "角色标识不能为空") String code,
            String description,
            Integer dataScope,
            Integer sort,
            Integer status
    ) {}

    public record AssignPermission(
            @NotNull(message = "角色ID不能为空") Long id,
            List<Long> menuIds,
            List<Long> deptIds
    ) {}

    public record Query(
            String name,
            String code,
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