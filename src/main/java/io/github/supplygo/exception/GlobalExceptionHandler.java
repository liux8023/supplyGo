package io.github.supplygo.exception;

import io.github.supplygo.common.R;
import io.github.supplygo.common.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务层主动抛出的异常
     */
    @ExceptionHandler(ServiceException.class)
    public Mono<ResponseEntity<R<?>>> handleServiceException(ServiceException ex) {
        Status status = ex.getStatus();
        log.warn("业务异常: code={}, message={}", status.getCode(), status.getMessage(), ex);
        // 自定义的业务异常类型，应该始终返回HTTP 200 OK
        return Mono.just(ResponseEntity.ok(R.fail(status)));
    }

    /**
     * 将http异常状态转化为rest返回，http状态码直接赋值
     */
    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<?>> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatusCode statusCode = ex.getStatusCode();
        log.warn("请求异常: status={}, message={}", statusCode.value(), ex.getReason(), ex);
        //http异常状态由框架产生，应该始终返回
        return Mono.just(ResponseEntity.status(statusCode).body(R.fail(ex.getReason())));
    }

    /**
     * 前期将sql异常状态也返回提示到前端便于定位
     */
    @ExceptionHandler(DataAccessException.class)
    public Mono<ResponseEntity<?>> handleResponseStatusException(DataAccessException ex) {
        log.warn("数据库异常:", ex);
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(R.fail(Objects.requireNonNullElse(ex.getRootCause(), ex.getCause()).getMessage())));
    }

    /**
     * 未知错误还是自己处理吧，别抛给前端
     *
     *
     */
    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<R<?>>> handleOtherException(Throwable ex) {
        log.error("未知错误：{}", ex.getMessage(), ex);
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(R.fail("未知错误，请联系管理员")));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R<?> handleDuplicateKey(DuplicateKeyException ex) {
        log.error("主键冲突：{}", ex.getMessage(), ex);
        return R.fail(Status.DUPLICATE_PRIMARY_KEY);
    }

}