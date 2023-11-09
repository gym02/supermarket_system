package com.example.supermarket.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  public static String JWT_KEY="woniu";
  public static  String JWT_ISSUER="woniuxy";
  public static final int    JWT_TOKEN=30*1000;
    public static  final int    JWT_REFRESH=10000*1000;


    public static String createToken(String userId,String userName){
        String token = JWT.create().setKey(JWT_KEY.getBytes())
                .setIssuer(JWT_ISSUER)
                .setIssuedAt(DateTime.now())
                .setNotBefore(DateTime.now())
                .setExpiresAt(DateTime.now()
                        .offset(DateField.MILLISECOND, JWT_TOKEN))
                .setPayload("userId", userId)
                .setPayload("userName",userName)
                .sign();
        return  token;
    }
    public static String createRefresh(String userId,String userName){
        String token = JWT.create().setKey(JWT_KEY.getBytes())
                .setIssuer(JWT_ISSUER)
                .setIssuedAt(DateTime.now())
                .setNotBefore(DateTime.now())
                .setExpiresAt(DateTime.now()
                        .offset(DateField.MILLISECOND, JWT_REFRESH))
                .setPayload("userId", userId)
                .setPayload("userName",userName)
                .sign();
        return  token;
    }
    public static String getUserId(String token){
        return  JWT.of(token).getPayload("userId").toString();
    }

    public static String getUserName(String token){
        return  JWT.of(token).getPayload("userName").toString();
    }

    //验证是否有效
    public static boolean validate(String token){
        return JWT.of(token).setKey("woniu".getBytes()).verify();
    }

    //验证是否过期
    public static boolean isExpire(String token){
        return JWT.of(token).setKey("woniu".getBytes()).validate(0);
    }
}
