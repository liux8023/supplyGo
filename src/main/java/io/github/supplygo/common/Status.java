package io.github.supplygo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;

@Getter
@AllArgsConstructor
public enum Status {

    // ---  通用状态码 [0000 ~ 0999] ---
    SUCCESS("COMMON", 200, "操作成功"),
    FAILURE("COMMON", 999, "操作失败"),

    // ---  用户模块 [1000 ~ 1999] ---
    LOGIN_VALID_ERROR("USER", 1001, "账号或密码错误"),
    ACCOUNT_EXISTS("USER", 1002, "账号已存在"),
    USERNAME_EXISTS("USER", 1003, "用户名已存在"),
    EMAIL_EXISTS("USER", 1004, "邮箱已存在"),
    PHONE_EXISTS("USER", 1005, "手机号已存在"),
    USER_NOT_EXIST("USER", 1006, "用户不存在"),
    TOKEN_EXPIRED("USER", 1007, "登录已过期"),
    ACCOUNT_DISABLED("USER", 1008, "账号已被禁用"),

    // ---  参数错误 [2000 ~ 2999] ---
    PARAM_MISS("PARAM", 2001, "缺少必要的请求参数"),
    PARAM_TYPE_ERROR("PARAM", 2002, "请求参数类型错误"),
    PARAM_BIND_ERROR("PARAM", 2003, "请求参数绑定错误"),
    PARAM_VALID_ERROR("PARAM", 2004, "参数校验失败"),
    MSG_NOT_READABLE("PARAM", 2005, "消息无法读取"),
    MISSING_REQUEST_HEADER("PARAM", 2006, "请求头缺失"),
    INVALID_ENUM_VALUE("PARAM", 2007, "枚举值不合法"),
    REQUEST_BODY_EMPTY("PARAM", 2008, "请求体为空"),

    // ---  业务错误 [3000 ~ 3999] ---
    BUSINESS_OPERATION_FAILED("BUSINESS", 3001, "业务操作失败"),
    DATA_NOT_EXIST("BUSINESS", 3002, "数据不存在"),
    DATA_ALREADY_EXIST("BUSINESS", 3003, "数据已存在"),
    RECORD_CONFLICT("BUSINESS", 3004, "记录冲突"),
    DATA_CONSTRAINT_VIOLATION("BUSINESS", 3005, "数据约束冲突"),
    REPEAT_OPERATION("BUSINESS", 3006, "请勿重复操作"),
    DELETE_WITH_CHILDREN_EXIST("BUSINESS", 3007, "删除失败，存在关联子数据，请先删除子数据"),

    // ---  权限/认证相关 [4000 ~ 4999] ---
    UNAUTHORIZED("AUTH", 4001, "未认证，请登录"),
    FORBIDDEN("AUTH", 4003, "权限不足"),
    NO_DATA_PERMISSION("AUTH", 4004, "没有数据权限"),

    // ---  系统内部错误 [5000 ~ 5999] ---
    SYSTEM_ERROR("SYSTEM", 5000, "系统异常"),
    SERVICE_UNAVAILABLE("SYSTEM", 5001, "服务暂不可用"),
    DATABASE_ERROR("SYSTEM", 5002, "数据库操作异常"),
    DUPLICATE_PRIMARY_KEY("SYSTEM", 5003, "不能重复添加"),
    REMOTE_SERVICE_ERROR("SYSTEM", 5004, "远程服务异常"),
    ASYNC_PROCESS_ERROR("SYSTEM", 5005, "异步处理失败");

    private final String module;
    private final int code;
    private final String message;

    public String getMessage(Locale locale) {
        // TODO 国际化扩展
        return message;
    }
}
