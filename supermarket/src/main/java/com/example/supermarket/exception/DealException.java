package com.example.supermarket.exception;

import com.example.supermarket.util.ResponseEntity;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 高垚淼
 * @version 1.0
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DealException {

    //    顾客信息不存在异常（不存在该顾客，无法进行会员添加）
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(CustomerNotFundException.class)
    public ResponseEntity dealCustomerNotFundException(CustomerNotFundException e) {
        return new ResponseEntity(400, "会员不存在异常", null);
    }

    //    会员已注册异常
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(VipIsExitsException.class)
    public ResponseEntity dealVipIsExitsException(VipIsExitsException e) {
        return new ResponseEntity(405, "会员已注册异常", null);
    }

    //    空值批量删除异常（如果未选中任何数据，不进入sql语句执行）
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(NullVipIdsException.class)
    public ResponseEntity dealNullVipIdsException() {
        return new ResponseEntity(400, "会员批量删除空集合异常", null);
    }

    //    输入数据格式验证不合法异常
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity doMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //后端验证
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(item -> errors.put(item.getField(), item.getDefaultMessage()));
        return new ResponseEntity(401, "输入数据非法", errors);
    }

    //    会员找回密码，验证码错误异常
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(FindPasswordCodeErrorException.class)
    public ResponseEntity dealFindPasswordCodeErrorException() {
        return new ResponseEntity(402, "验证码错误", null);
    }

    //    用户修改密码，两次密码不一致异常
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(CheckPasswordNotSameException.class)
    public ResponseEntity dealCheckPasswordNotSameException() {
        return new ResponseEntity(400, "两次密码输入不一致", null);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(VipIsNullException.class)
    public ResponseEntity VipIsNullException() {
        return new ResponseEntity(400, "余额充值会员账号不存在", null);
    }

    //    用户积分余额不足，兑换不了异常
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(CustomerPointNotEnoughException.class)
    public ResponseEntity dealCustomerPointNotEnoughException() {
        return new ResponseEntity(401, "余额不足，无法兑换积分商品", null);
    }

    //    剩余异常处理
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(Exception.class)
    public void dealException(Exception e) {
        e.printStackTrace();
    }

}
