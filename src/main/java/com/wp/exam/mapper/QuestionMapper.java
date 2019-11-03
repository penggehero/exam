package com.wp.exam.mapper;

import com.wp.exam.entity.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface QuestionMapper {
    // 查询
    @Select("select * from t_question where id= #{id}")
    public Question findQuestionById(Integer id) throws Exception;

    // 查询全部
    @Select("select * from t_question")
    public List<Question> findAllQuestions() throws Exception;

    // 增加
    @Insert("insert into t_question  values(null,#{p_id},#{number},#{name},#{flag},#{mark},#{q_A},#{q_B},#{q_C},#{q_D},#{answer})")
    public void addQuestion(Question question) throws Exception;

    // 删除
    @Delete("delete from t_question where id= #{id}")
    public void deleteQuestionById(Integer id) throws Exception;

    // 修改
    @Update("update t_question  set  p_id=#{p_id},number=#{number},name=#{name},flag=#{flag},mark=#{mark},q_A=#{q_A},q_B=#{q_B},q_C=#{q_C},q_D=#{q_D},answer=#{answer} where id= #{id}")
    public void updateQuestion(Question question) throws Exception;


}
