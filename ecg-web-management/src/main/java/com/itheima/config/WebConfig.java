package com.itheima.config;

import com.itheima.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login");//注册配置拦截器
    }

    /*声明第三方bean(举例) 但一般在配置类Config中定义
    @Bean //将方法返回值交给IOC容器管理，成为IOC容器的bean对象
          //通过@Bean注解的name/value属性指定bean名称，如果未指定，默认是方法名
    public SAXReader saxReader(){
        return new SAXRead();
    }
     */
}
