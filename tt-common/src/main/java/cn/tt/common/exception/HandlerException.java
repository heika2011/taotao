package cn.tt.common.exception;

import cn.tt.common.vo.JSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 无恙
 * @全局异常捕获类
 */
@RestControllerAdvice
public class HandlerException {
    /**
     *  捕获运行时异常
     *   @param
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public JSONResult fail(Throwable e){
        e.printStackTrace();
        return new JSONResult(e);
    }
}
