package com.lou.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author Biggfish
 */
//@Configuration
//public class SpringBootWebMvcConfigurer implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //registry.addResourceHandler("/files/**").addResourceLocations("file:D:\\upload\\");
//        registry.addResourceHandler("/files/**").addResourceLocations("file:/home/project/upload/");
//    }
//
//}
