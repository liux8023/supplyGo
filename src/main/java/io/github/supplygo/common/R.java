package io.github.supplygo.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 通用响应结果
 */
@Data
@Accessors(chain = true)
public class R<T> {

    private Integer code;
    private String message;
    private T data;
    private Long timestamp;

    private R(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    private R(Status status) {
        this(status.getCode(), status.getMessage());
    }

    public static <T> R<T> ok() {
        return new R<>(Status.SUCCESS);
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>(Status.SUCCESS);
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok(T data, String message) {
        R<T> r = new R<>(Status.SUCCESS.getCode(), message);
        r.setData(data);
        return r;
    }

    public static <T> R<T> okMessage(String message) {
        R<T> r = new R<>(Status.SUCCESS);
        r.setMessage(message);
        return r;
    }

    public static <T> R<T> fail(String message) {
        return new R<>(Status.FAILURE.getCode(), message);
    }

    public static <T> R<T> fail(Status status) {
        return new R<>(status);
    }

    public static <T> R<T> fail(Status status, String message) {
        return new R<>(status.getCode(), message);
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

    public R<T> message(String message) {
        this.message = message;
        return this;
    }

    public R<T> code(Integer code) {
        this.code = code;
        return this;
    }
} 