package io.github.supplygo.modules.access.co;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 部门命令对象
 */
public class DeptCO {

    public record Create(
            @NotBlank(message = "部门编码不能为空") String code,
            @NotBlank(message = "部门名称不能为空") String name,
            Long parentId,
            Integer sort,
            Integer status
    ) {}

    public record Update(
            @NotNull(message = "部门ID不能为空") Long id,
            String code,
            String name,
            Long parentId,
            Integer sort,
            Integer status
    ) {}

    public record Query(
            String name,
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