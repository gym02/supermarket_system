package com.example.supermarket.interceptor;

import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");

        String refresh=request.getHeader("refresh");

        if(request.getMethod().equals("OPTIONS")){
            log.info("OPTIONS请求，放行");
            return  true;
        }
        if(!StringUtils.isEmpty(token)&& JwtUtil.validate(token)){
            if(JwtUtil.isExpire(token)){
            log.info("token有效，放行");
            return true;}
            else if(JwtUtil.isExpire(refresh)){
                log.info("refresh有效，放行");
                token = JwtUtil.createToken(JwtUtil.getUserId(token), JwtUtil.getUserName(token));

                response.setHeader("token",token);
                refresh=JwtUtil.createRefresh(JwtUtil.getUserId(refresh),JwtUtil.getUserName(refresh));
                response.setHeader("refresh",refresh);
                return  true;

            }else{ response.setContentType("text/html;charset=utf-8");
                ResponseResult<String> responseResult=new ResponseResult<>(401,"error","未登录");
                response.getWriter().write(new ObjectMapper().writeValueAsString(responseResult));
                log.info("refresh无效，拦截");
                return false;}

        }else{
            response.setContentType("text/html;charset=utf-8");
            ResponseResult<String> responseResult=new ResponseResult<>(401,"error","未登录");
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseResult));
            log.info("token无效，拦截");
            return false;
        }



    }
}
