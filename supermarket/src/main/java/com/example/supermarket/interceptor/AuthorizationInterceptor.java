package com.example.supermarket.interceptor;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.service.PermissionService;
import com.example.supermarket.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
	@Autowired
	PermissionService permissionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse resp, Object handler) throws Exception {
		//对OPTIONS请求放行
		if(request.getMethod().equals("OPTIONS")){
			log.info("OPTIONS请求，放行");
			return true;
		}
		resp.setContentType("text/html;charset=utf-8");
		String token = request.getHeader("token");
		//请求后的路径，localhost:8080/user/add
		log.info("request.getRequestURI()={}",request.getRequestURI());
		List<String> permissionPaths = permissionService.getPermissionPathsByUserId(Integer.parseInt(JwtUtil.getUserId(token)));
		List<String> filterPaths = permissionPaths.stream().filter(path -> path!=null && request.getRequestURI().contains(path)).collect(Collectors.toList());
		log.info("AuthorizationInterceptor,filter过滤结果:{}",filterPaths);
		if (filterPaths.size()==0){
			log.info("AuthorizationInterceptor没有权限,拦截");
			ResponseResult<String> responseResult=new ResponseResult<>(403,"error","没有权限");
			resp.getWriter().write(new ObjectMapper().writeValueAsString(responseResult));
			return false;
		} else {
			log.info("AuthorizationInterceptor角色权限满足,放行");
			return true;
		}
	}
}