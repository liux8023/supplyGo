package io.github.supplygo.modules.system.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 岗位业务对象
 */
@Data
@NoArgsConstructor
public class PostBO {
    private Long id;
    private String name;
    private String code;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 