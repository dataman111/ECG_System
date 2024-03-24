package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 科室日志类
 */
@Data
@NoArgsConstructor  //注解无参构造
@AllArgsConstructor  //注解有参构造
public class DeptLog {
    private Integer id; //ID
    private LocalDateTime createTime; //创建时间
    private String Description; //描述信息
}
