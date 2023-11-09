package com.example.supermarket.controller;


import com.example.supermarket.entity.PasswordChange;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.exception.VerifyCodeErrorException;
import com.example.supermarket.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PasswordController {
    @Autowired
    PasswordService passwordService;
    @Autowired
    RedisTemplate redisTemplate;
    @PostMapping("/changePassword")
    public ResponseResult<Void> changePassword(@RequestBody PasswordChange passwordChange){
        if(!passwordChange.getCode().equals(redisTemplate.opsForValue().get(passwordChange.getEmail()))){
            throw new VerifyCodeErrorException("验证码错误");
        }else{
            redisTemplate.delete(passwordChange.getEmail());
        }
        System.out.println(passwordChange.getPassword());
        String s = DigestUtils.md5DigestAsHex(passwordChange.getPassword().getBytes());
        passwordService.change(passwordChange.getEmail(),s);
        return  new ResponseResult(200,"succedss",null);
    }
}
