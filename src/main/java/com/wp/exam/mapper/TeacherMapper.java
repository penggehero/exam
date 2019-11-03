package com.wp.exam.mapper;

import com.wp.exam.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TeacherMapper {

    @Select("select * from t_teacher where id = #{id} ")
    public Teacher findTeacherById(Integer id) throws Exception;

    @Select("select * from t_teacher")
    public List<Teacher> findAllTeacherById() throws Exception;


    @Insert("insert into t_teacher values(null,#{username},#{password},#{name},#{school},#{college})")
    public void addTeacher(Teacher teacher) throws Exception;

    @Delete("delete from t_teacher where id = #{id}")
    public void deleteTeacher(Integer id) throws Exception;

    @Update("update t_teacher set username=#{username},password=#{password},name=#{name},school=#{school},college=#{college} where id = #{id}")
    public void updateTacher(Teacher teacher) throws Exception;


}
