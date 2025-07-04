package io.github.supplygo.modules.access.co;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户命令对象
 */
public class UserCO {

    public record Create(
            @NotBlank(message = "账号不能为空") String account,
            @NotBlank(message = "密码不能为空") String password,
            @NotBlank(message = "昵称不能为空") String name,
            String realName,
            String email,
            String phone,
            LocalDate birthday,
            Integer sex,
            Long deptId,
            Long postId,
            List<Long> roleIds,
            Integer status
    ) {}

    public record Update(
            @NotNull(message = "用户ID不能为空") Long id,
            @NotBlank(message = "账号不能为空") String account,
            @NotBlank(message = "昵称不能为空") String name,
            String realName,
            String email,
            String phone,
            LocalDate birthday,
            Integer sex,
            Long deptId,
            Long postId,
            List<Long> roleIds,
            Integer status
    ) {}

    public record ChangePassword(
            @NotNull(message = "用户ID不能为空") Long id,
            @NotBlank(message = "原密码不能为空") String oldPassword,
            @NotBlank(message = "新密码不能为空") String newPassword
    ) {}

    public record Query(
            String account,
            String name,
            String realName,
            String phone,
            Integer status,
            Long deptId,
            LocalDateTime createTimeStart,
            LocalDateTime createTimeEnd,
            Long current,
            Long size
    ) {
        public Query {
            if (current == null) current = 1L;
            if (size == null) size = 10L;
        }
    }
}