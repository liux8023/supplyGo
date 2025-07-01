package io.github.supplygo.modules.system.co;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 菜单命令对象
 */
public class MenuCO {

    public record Create(
            Long parentId,
            @NotBlank(message = "菜单编号不能为空") String code,
            @NotBlank(message = "菜单名称不能为空") String name,
            String path,
            String component,
            String permission,
            @NotNull(message = "菜单类型不能为空") Integer type,
            String icon,
            Integer sort,
            Integer status
    ) {}

    public record Update(
            @NotNull(message = "菜单ID不能为空") Long id,
            Long parentId,
            @NotBlank(message = "菜单编号不能为空") String code,
            @NotBlank(message = "菜单名称不能为空") String name,
            String path,
            String component,
            String permission,
            @NotNull(message = "菜单类型不能为空") Integer type,
            String icon,
            Integer sort,
            Integer status
    ) {}

    public record Query(
            String name,
            Integer status,
            Long parentId,
            Long current,
            Long size
    ) {
        public Query {
            if (current == null) current = 1L;
            if (size == null) size = 10L;
        }
    }
} 