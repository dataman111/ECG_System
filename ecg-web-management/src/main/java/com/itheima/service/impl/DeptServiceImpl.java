package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.PatientMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private DeptLogService deptLogService;

    //在实现类中实现接口方法
    @Override
    public List<Dept> list() {
        return deptMapper.list(); //调用mapper中的查询数据方法
    }

    //@Transactional(rollbackFor = Exception.class) //指定遇到所有异常都回档
    @Transactional //spring事务管理
    @Override
    public void delete(Integer id) throws Exception {
        try {
            deptMapper.deleteById(id); //调用mapper中的删除数据方法
            //删除科室时把病人也删了
            //int i = 1/0; //模拟抛出异常，此时删除了科室，但没执行删除病人，因为动作不同步（由此引出事务）
            patientMapper.deleteByDeptId(id);
        } finally {
            //无论是否删除成功（即使异常）都要记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散科室的操作，解散"+id+"号科室");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        //先补全属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //id自动建，name必须有，无需补全

        deptMapper.insert(dept);//调用mapper中的插入数据方法
    }

    @Override
    public Dept listById(Integer id) {
        return deptMapper.listById(id); //调用mapper的查询方法
    }

    @Override
    public void updateById(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dept);//调用mapper修改科室
    }
}
