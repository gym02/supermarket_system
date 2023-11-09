package com.example.supermarket.controller;


import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.exception.PasswordNotException;
import com.example.supermarket.exception.PhoneExistsException;
import com.example.supermarket.exception.UserNameNotExistException;
import com.example.supermarket.exception.VerifyCodeErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @Order(Ordered.HIGHEST_PRECEDENCE)
public ResponseResult doException(Exception e){
    log.info("exception={}", e.getClass());
    log.info("exception={}", e.getMessage());
      e.printStackTrace();
    return new ResponseResult(500,"error",e.getMessage());

}
    @ExceptionHandler(PhoneExistsException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseResult doPhoneNotExistException(PhoneExistsException e){


        return new ResponseResult(100,"error",e.getMessage());
    }
    @ExceptionHandler(UserNameNotExistException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseResult doEnameNotExistException(UserNameNotExistException e){


        return new ResponseResult(500,"error",e.getMessage());
    }
    @ExceptionHandler(PasswordNotException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseResult doPasswordNotException(PasswordNotException e){


        return new ResponseResult(500,"error",e.getMessage());
    }
    @ExceptionHandler(VerifyCodeErrorException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseResult doVerifyCodeErrorException(VerifyCodeErrorException e){


        return new ResponseResult(500,"error",e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseResult doMethodArgumentNotValidException(MethodArgumentNotValidException e){
        //后端验证
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> errors=new HashMap<>();
//        if (bindingResult.getFieldErrorCount()>0) {
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            for(FieldError fieldError : fieldErrors){
//                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
//            }
        bindingResult.getFieldErrors().forEach(item -> errors.put(item.getField(), item.getDefaultMessage()));
        return new ResponseResult(100,"valid error",errors);
//        }
    }
}
