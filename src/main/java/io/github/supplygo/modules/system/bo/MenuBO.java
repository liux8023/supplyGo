package io.github.supplygo.modules.system.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单业务对象
 */
@Data
@NoArgsConstructor
public class MenuBO {

    // Direct fields from SysMenu
    private Long id;
    private Long parentId;
    private String code;
    private String name;
    private String path;
    private String component;
    private String permission;
    private Integer type;
    private String icon;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // Associated or calculated fields
    private String parentName;
    private List<MenuBO> children = new ArrayList<>();
} 