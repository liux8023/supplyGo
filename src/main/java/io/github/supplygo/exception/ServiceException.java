package io.github.supplygo.exception;

import io.github.supplygo.common.Status;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final Status status;

    /**
     * 使用一个业务状态码来构造异常
     */
    public ServiceException(Status status) {
        // 使用枚举中定义的消息作为异常信息
        super(status.getMessage());
        this.status = status;
    }

    /**
     * 使用一个业务状态码和自定义消息来构造异常
     *
     * @param message 自定义消息
     */
    public ServiceException(Status status, String message) {
        // 使用自定义消息
        super(message);
        this.status = status;
    }

}