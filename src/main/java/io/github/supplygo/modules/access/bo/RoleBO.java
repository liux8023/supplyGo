package io.github.supplygo.modules.access.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色业务对象
 */
@Data
@NoArgsConstructor
public class RoleBO {

    // Direct fields from SysRole
    private Long id;
    private String tenantId;
    private String code;
    private String name;
    private String description;
    private Integer dataScope;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Associated or calculated fields to be filled by service
    private String dataScopeName;
    private Long userCount;
    private List<Long> menuIds;
    private List<Long> deptIds;

} 