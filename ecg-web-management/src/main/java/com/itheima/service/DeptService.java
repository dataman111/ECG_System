package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * 科室管理
 */
public interface DeptService {
    /**
     * 查询全部科室数据
     * @return
     */
    List<Dept> list();

    /**
     * 删除科室数据
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 新增科室
     * @param dept
     */
    void add(Dept dept);

    /**
     * 查询科室
     * @param id
     * @return
     */
    Dept listById(Integer id);

    /**
     * 修改科室
     * @param dept
     */
    void updateById(Dept dept);
}
