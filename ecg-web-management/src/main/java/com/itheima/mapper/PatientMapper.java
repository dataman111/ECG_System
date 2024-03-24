package com.itheima.mapper;

import com.itheima.pojo.Patient;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 病人管理
 */
@Mapper
public interface PatientMapper {
    /**
     * 分页查询
     * @return
     */
    //@Select("select * from patient")
    //使用映射文件
    //传入的参数有多个或使用映射时必须使用@param进行标识
    public List<Patient> list(@Param("name") String name,
                          @Param("gender") Short gender,
                          @Param("begin") LocalDate begin,
                          @Param("end") LocalDate end);

    /**
     * 批量删除
     * @param ids
     */
    //复杂时使用映射文件编写sql
    void delete(@Param("ids") List<Integer> ids);

    /**
     * 新增病人
     * @param patient
     */
    @Insert("insert into patient(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            " values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime}) ")
    void insert(Patient patient);

    @Select("select * from patient where id = #{id}")
    Patient getById(Integer id);

    /**
     * 更新病人
     * @param patient
     */
    //使用映射文件
    void updateById(@Param("patient") Patient patient);

    /**
     * 根据账号密码查询
     * @param patient
     * @return
     */
    @Select("select * from patient where username = #{username} " +
            "and password = #{password}")
    Patient getByUsernameAndPassword(Patient patient);

    /**
     * 删除科室下所有病人
     * @param deptId
     */
    @Delete("delete from patient where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);

    //分页查询
//    @Select("select concat(*) from patient")
//    Long count();
//    @Select("select * from patient limit #{start},#{pageSize}")
//    public List<Patient> page(Integer start,Integer pageSize);

}
