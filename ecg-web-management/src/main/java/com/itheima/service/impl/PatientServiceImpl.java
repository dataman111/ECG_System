package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.PatientMapper;
import com.itheima.pojo.Patient;
import com.itheima.pojo.PageBean;
import com.itheima.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize,String name,
                         Short gender, LocalDate begin, LocalDate end){
//        Long count = patientMapper.count();
//        Integer start = (page-1)*pageSize;
//        List<Patient> patientList = patientMapper.page(start,pageSize);
        //设置分页参数
        PageHelper.startPage(page,pageSize);//设置好参数，插件自动帮忙
        //执行查询
        List<Patient> patientList = patientMapper.list(name,gender,begin,end);
        Page<Patient> p  = (Page<Patient>) patientList; //强转成Page类
        //封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        patientMapper.delete(ids);
    }

    @Override
    public void save(Patient patient) {
        //先补全属性
        patient.setCreateTime(LocalDateTime.now());
        patient.setUpdateTime(LocalDateTime.now());
        //id自动建，name必须有，无需补全
        patientMapper.insert(patient);
    }

    @Override
    public Patient getById(Integer id) {
        return patientMapper.getById(id);
    }

    @Override
    public void updateById(Patient patient) {
        //先补全属性
        patient.setUpdateTime(LocalDateTime.now());
        patientMapper.updateById(patient);
    }

    @Override
    public Patient login(Patient patient) {
        return patientMapper.getByUsernameAndPassword(patient); //不要用login命名
    }
}
