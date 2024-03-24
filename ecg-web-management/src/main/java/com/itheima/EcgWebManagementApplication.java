package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 开启了对servlet组件的支持
@SpringBootApplication
public class EcgWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcgWebManagementApplication.class, args);
    }
//        @Test
//        public void contextLoads() {
//        }

    /*声明第三方bean(举例) 但一般在配置类Config中定义
    @Bean //将方法返回值交给IOC容器管理，成为IOC容器的bean对象
    public SAXReader saxReader(){
        return new SAXRead();
    }
     */

}
