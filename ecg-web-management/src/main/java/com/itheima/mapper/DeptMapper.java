package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 科室管理
 */
@Mapper
public interface DeptMapper {
    /**
     * 查询全部科室数据
     * @return
     */
    //使用注解方式开发
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 删除科室数据
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 插入科室数据
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 根据id查询科室
     * @param id
     * @return
     */
    @Select("select * from dept where id = #{id}")
    Dept listById(Integer id);

    /**
     * 修改科室
     * @param dept
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void updateById(Dept dept);
}
