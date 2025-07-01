package io.github.supplygo.modules.system.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户业务对象 (可变POJO)
 * 用于在Service层流转、组合和处理业务数据
 */
@Data
@NoArgsConstructor
public class UserBO {

    // 直接从SysUser映射过来的字段
    private Long id;
    private String tenantId;
    private String code;
    private String account;
    private String name;
    private String realName;
    private String avatar;
    private String email;
    private String phone;
    private LocalDate birthday;
    private Integer sex;
    private Long deptId;
    private Long postId;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 从关联对象计算或映射得到的字段
    private String deptName;
    private String postName;
    private List<String> roleNames;
    private List<Long> roleIds;

    // 可以在Service层动态填充的业务字段
    // private boolean isPasswordExpired;
    // private int loginFailureCount;
} 