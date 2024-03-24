package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 病人实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private Integer id; //ID
    private String username; //用户名
    private String password; //密码
    private String name; //姓名
    private Short gender; //性别 , 1 男, 2 女
    private String image; //图像url
    private Short job; //年龄
    private LocalDate entrydate; //入院日期  别和string比较，例如""
    private Integer deptId; //科室ID
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}

