package io.github.supplygo.modules.system.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DataScopeBO {
    private Long id;
    private Long menuId;
    private String resourceCode;
    private String scopeName;
    private String scopeField;
    private String scopeClass;
    private String scopeColumn;
    private Integer scopeType;
    private String scopeValue;
    private String remark;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 