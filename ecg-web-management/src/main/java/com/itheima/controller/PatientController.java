package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Patient;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 病人管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps") //请求体的公共部分
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询，参数：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用service分页查询
        PageBean pageBean = patientService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    /**
     * 删除病人
     * @param ids
     * @return
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除,ids:{}",ids);
        patientService.delete(ids);
        return Result.success();
    }

    /**
     * 新增病人
     * @param patient
     * @return
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Patient patient){
        log.info("新增病人：{}",patient);
        patientService.save(patient);
        return Result.success();
    }

    /**
     * 查询病人
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询病人信息，id：{}",id);
        Patient patient = patientService.getById(id);
        return Result.success(patient);
    }

    /**
     * 修改病人
     * @param patient
     * @return
     */
    @Log
    @PutMapping
    public Result updateById(@RequestBody Patient patient){
        log.info("修改病人信息：{}",patient);
        patientService.updateById(patient);
        return Result.success();
    }
}
