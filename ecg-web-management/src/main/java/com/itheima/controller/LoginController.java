package com.itheima.controller;

import com.itheima.pojo.Patient;
import com.itheima.pojo.Result;
import com.itheima.service.PatientService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/login")
    public Result login(@RequestBody Patient patient){
        log.info("病人登录：{}",patient);
        Patient e = patientService.login(patient);
        //登录成功，生成令牌，下发令牌
        if(e != null){
            Map<String,Object> claims = new HashMap<>(); //存放内容
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的病人信息
            return Result.success(jwt);
        }
        //登录失败，返回错误信息
        return Result.error("诊疗卡号或密码错误");
    }
}
