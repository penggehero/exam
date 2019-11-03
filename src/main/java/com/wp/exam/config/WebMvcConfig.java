package com.wp.exam.config;

import com.wp.exam.filter.LoginInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.MultipartConfigElement;

/*配置静态资源映射*/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 静态资源放行
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 简单的静态映射
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/studentCenter").setViewName("student/studentCenter");
        registry.addViewController("/teacherCenter").setViewName("teacher/teacherCenter");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/test").setViewName("teacher/addPaper");
        registry.addViewController("/student").setViewName("student/student");
        registry.addViewController("/student/toregister").setViewName("student/studentregister");
        registry.addViewController("/teacher/toregister").setViewName("teacher/teacherregister");
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/login","/static/**");
    }
}