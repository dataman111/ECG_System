package com.itheima.service;

import com.itheima.pojo.Patient;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 病人管理
 */
public interface PatientService {
    PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void save(Patient patient);

    Patient getById(Integer id);

    void updateById(Patient patient);

    Patient login(Patient patient);
}
