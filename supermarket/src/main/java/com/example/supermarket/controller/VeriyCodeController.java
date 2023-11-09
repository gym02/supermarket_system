package com.example.supermarket.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.codec.Base64;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.VerifyCodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class VeriyCodeController {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @RequestMapping("/verifycode")
    public ResponseResult<VerifyCodeVO> createVerifyCode(HttpServletResponse response){
        ICaptcha captcha= CaptchaUtil.createLineCaptcha(200,100,4,5);
        String verifycode = captcha.getCode();
        log.info("verifycode={}",verifycode);
        String uuid = UUID.randomUUID().toString().replace("-", "");
         redisTemplate.opsForValue().set(uuid,verifycode,10, TimeUnit.MINUTES);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
           captcha.write(outputStream);
            String verifycodesrc = Base64.encode(outputStream.toByteArray());
            return new ResponseResult<>(200,"success",new VerifyCodeVO(verifycodesrc,uuid));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
