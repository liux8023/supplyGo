package io.github.supplygo.modules.access.co;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 岗位命令对象
 */
public class PostCO {

    public record Create(
            @NotBlank(message = "岗位名称不能为空") String name,
            @NotBlank(message = "岗位编码不能为空") String code,
            Integer sort,
            Integer status
    ) {}

    public record Update(
            @NotNull(message = "岗位ID不能为空") Long id,
            @NotBlank(message = "岗位名称不能为空") String name,
            @NotBlank(message = "岗位编码不能为空") String code,
            Integer sort,
            Integer status
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