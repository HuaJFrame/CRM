package com.huajframe.xycrm.exception.handler;

import com.huajframe.xycrm.common.util.R;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Hua JFarmer
 */
@Slf4j
@RestControllerAdvice
public class AauthorityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public R usernameNotFound(UsernameNotFoundException e){
        log.error("运行时异常-----{}", e.getMessage());
        return R.error(e.getMessage());
    }
}
