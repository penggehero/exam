package com.wp.exam.mapper;

import com.wp.exam.entity.Grade;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GradeMapper {

    //通过sid查询
    @Select("select * from t_grade t where t.s_id=#{sid}")
    public List<Grade> findBySID(Integer sid);


    //通过sid查询
    @Select("select * from t_grade t where t.s_id like #{s_id} or t.s_name like #{s_name} or t.p_name like #{p_name}")
    public List<Grade> searchGrades(@Param("s_id") String s_id,@Param("s_name") String s_name,@Param("p_name") String p_name);


    // 查询
    @Select("select * from t_grade where id= #{id}")
    public Grade findGradeById(Integer id) throws Exception;

    // 查询全部
    @Select("select * from t_grade")
    public List<Grade> findAllGrade() throws Exception;

    // 增加
    @Insert("insert into t_grade  values(null,#{s_id},#{s_name},#{p_id},#{p_name},#{date},#{s_mark},#{d_mark},#{mark})")
    public void addGrade(Grade grade) throws Exception;

    // 删除
    @Delete("delete from t_grade where id= #{id}")
    public void deleteGradeById(Integer id) throws Exception;

    // 修改
    @Update("update t_grade  set  s_id=#{s_id},s_name=#{s_name},p_id=#{p_id},p_name=#{p_name},date=#{date},s_mark=#{s_mark},d_mark=#{d_mark},mark=#{mark} where id= #{id}")
    public void updateGrade(Grade grade) throws Exception;


}
