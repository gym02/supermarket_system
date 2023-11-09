package com.example.supermarket.config;
import com.example.supermarket.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author author
 * @Create 2023-09-13 16:42
 */
@Configuration
public class CorsConfiguration {
	@Autowired
	LoginInterceptor loginInterceptor;

	@Value("${example.file-path}")
	String basePath;
	//    @Autowired
//    LoginInterceptor loginInterceptor;
//
//    @Autowired
//    AuthorizationInterceptor authorizationInterceptor;
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")//配置允许跨域的请求
						.allowedOrigins("*")      //配置允许跨域的域名
						.allowedHeaders("*")      //配置允许跨域携带请求头信息
						.allowedMethods("*");       //配置允许跨域的请求类型 POST GET DELETE PUT      Restful
			}
			//配置映射路径转换


			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/images/**").addResourceLocations("file:///c:/temp/");
			}

		};
	}

}