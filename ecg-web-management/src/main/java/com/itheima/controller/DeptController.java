package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 科室管理Controller(后续调用seivice)
 */
//@Lazy //延迟到使用时创建实例
//@Scope("singleton") //bean作用域:单例（默认）
//@Scope("prototype") //bean作用域：每次使用创建一个新的实例
@Slf4j //记录日志注解
@RestController
@RequestMapping("/depts") //请求体的公共部分
public class DeptController {

    //日志记录对象
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired //自动注入
    private DeptService deptService;

    /**
     * 查询科室数据
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //指定请求方式
    @GetMapping
    public  Result list(){
        log.info("查询全部科室数据");
        //前端controller收到请求->到service获取数据->mapper实现查询，串联数据库
        //调用service查询科室数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);//返回给前端
        //控制台因为配置了：mybatis的日志指定输出到控制台
    }

    /**
     * 删除科室数据
     * @return
     */
    @Log //加了注解，就会被切入点表达式接收到
    @DeleteMapping("/{id}") //获取路径中的id，绑定给参数
    public Result delete(@PathVariable("id") Integer id) throws Exception {
        //名称一致时,可以简写
        log.info("根据id删除科室：{}",id);
        //调用service删除科室数据
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增科室
     * @param dept
     * @return
     */
    @Log
    @PostMapping  //获取请求传来的封装类（json)
    public Result add(@RequestBody Dept dept){
        log.info("新增科室：{}",dept);
        //调用service新增科室
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result listById(@PathVariable("id") Integer id){
        log.info("根据id查询科室：{}",id);
        //调用service查询科室
        Dept dept = deptService.listById(id);
        return Result.success(dept);
    }

    /**
     * 修改科室
     * @param dept
     * @return
     */
    @Log
    @PutMapping
    public Result updateById(@RequestBody Dept dept){
        log.info("修改科室：{}",dept);
        //调用service修改科室
        deptService.updateById(dept);
        return Result.success();
    }
}
