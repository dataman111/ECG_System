package com.itheima.mapper;

import com.itheima.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 病人日志管理
 */
@Mapper
public interface DeptLogMapper {
    @Insert("insert into dept_log(create_time, description) values(#{createTime},#{description})")
    void insert(DeptLog deptLog);
}
