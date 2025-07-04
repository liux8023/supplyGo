package io.github.supplygo.modules.access.ro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户响应对象
 */
public class UserRO {

    /**
     * 用户详情响应
     */
    public record Detail(
        String id,
        String code,
        String account,
        String name,
        String realName,
        String avatar,
        String email,
        String phone,
        LocalDate birthday,
        Integer sex,
        String deptId,
        String postId,
        Integer status,
        List<String> roleIds,
        String deptName,
        String postName,
        List<String> roleNames,
        LocalDateTime createTime,
        LocalDateTime updateTime
    ) {}

    /**
     * 用户列表项响应
     */
    public record ListItem(
        String id,
        String code,
        String account,
        String name,
        String realName,
        String avatar,
        String email,
        String phone,
        LocalDate birthday,
        Integer sex,
        String deptId,
        String postId,
        Integer status,
        List<String> roleIds,
        String deptName,
        String postName,
        List<String> roleNames,
        LocalDateTime createTime,
        LocalDateTime updateTime
    ) {}
    
    /**
     * 简化用户信息响应
     */
    public record Simple(
        String id,
        String name
    ) {}
} 