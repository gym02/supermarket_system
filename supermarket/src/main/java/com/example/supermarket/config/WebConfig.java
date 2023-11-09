package com.example.supermarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Configuration
public class WebConfig {
	@Bean
	public ViewResolver viewResolver(){
		BeanNameViewResolver viewResolver = new BeanNameViewResolver();
		return viewResolver;
	}
}
