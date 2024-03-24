package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 科室实体类
 */
@Data //注解get set string
@NoArgsConstructor  //注解无参构造
@AllArgsConstructor  //注解有参构造
//驼峰命名
public class Dept {
    private Integer id; //ID
    private String name; //科室名称
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
