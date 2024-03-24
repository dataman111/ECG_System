package com.itheima.anno;
//标识作用

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解
@Retention(RetentionPolicy.RUNTIME) //运行时生效
@Target(ElementType.METHOD) //作用在方法上
public @interface Log {
}
