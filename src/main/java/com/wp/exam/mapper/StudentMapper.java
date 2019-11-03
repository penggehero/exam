package com.wp.exam.mapper;

import com.wp.exam.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

    // 查询
    @Select("select * from t_student where id= #{id}")
    public Student findStudentById(Integer id) throws Exception;

    // 查询
    @Select("select * from t_student where id like #{id}")
    public  List<Student> findStudentlikeId(String id) throws Exception;

    // 查询
    @Select("select * from t_student where name like #{name}")
    public List<Student> findStudentlikeName(String name) throws Exception;

    // 查询
    @Select("select * from t_student where name like #{name} or id like #{id}")
    public List<Student> findStudentlikeIDAndName(@Param("id") String id, @Param("name") String name) throws Exception;

    // 查询全部
    @Select("select * from t_student")
    public List<Student> findAllStudent() throws Exception;

    // 增加
    @Insert("insert into t_student  values(null,#{username},#{password},#{name},#{age},#{school},#{college},#{major},#{grade})")
    public void addStudent(Student student) throws Exception;

    // 删除
    @Delete("delete from t_student where id= #{id}")
    public void deleteStudentById(Integer id) throws Exception;

    // 修改
    @Update("update t_student  set  username=#{username},password=#{password},name=#{name},age=#{age},school=#{school},college=#{college},major=#{major},grade=#{grade} where id= #{id}")
    public void updateStudent(Student student) throws Exception;


}
