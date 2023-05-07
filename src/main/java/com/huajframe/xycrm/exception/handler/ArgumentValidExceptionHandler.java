package com.huajframe.xycrm.exception.handler;

import com.huajframe.xycrm.common.util.R;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author Hua JFarmer
 */
@RestControllerAdvice
public class ArgumentValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        R r = R.error(400, "参数校验异常, 请检查参数输入！");
        for (FieldError fieldError : fieldErrors) {
            r.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return r;
    }
}
