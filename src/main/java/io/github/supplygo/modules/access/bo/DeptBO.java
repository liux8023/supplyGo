package io.github.supplygo.modules.access.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门业务对象
 */
@Data
@NoArgsConstructor
public class DeptBO {

    // Direct fields from SysDept
    private Long id;
    private Long parentId;
    private String code;
    private String name;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Associated or calculated fields
    private String parentName;
    private List<DeptBO> children;
} 