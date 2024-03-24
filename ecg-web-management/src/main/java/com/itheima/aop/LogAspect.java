package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

//切面类
@Component
@Aspect //切面类
@Slf4j
public class LogAspect {
    @Autowired
    private HttpServletRequest request; //注入请求信息

    @Autowired
    private OperateLogMapper operateLogMapper;

    //定义通知方法
    //环绕通知
    @Around("@annotation(com.itheima.anno.Log)") //切入点表达式(当方法上有Log注解时) 即在对应方法加上Log注解就会触发
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable { //调用原始目标方法来运行
        //操作人ID-当前登录员工ID
        //获取请求头重的jwt令牌，解析令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);//解析
        Integer operateUser = (Integer) claims.get("id");

        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();;
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = joinPoint.proceed(); //aroung环绕通知，方法运行前后的逻辑都可以执行
        long end = System.currentTimeMillis();

        //方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        Long costTime = end - begin;

        //记录操作日志(首位 id 自增，不用赋值)
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,
                methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志：{}",operateLog);

        return result;
    }
}
