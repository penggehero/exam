package com.wp.exam.mapper;


import com.wp.exam.entity.Paper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PaperMapper {
    // 查询
    @Select("select * from t_paper where id= #{id}")
    public Paper findPaperById(Integer id) throws Exception;

    // 查询全部
    @Select("select * from t_paper")
    public List<Paper> findAllPaper() throws Exception;

    // 增加 ,@Options 使插入实体获取返回的id
    @Insert("insert into t_paper  values(null,#{paper.name},#{paper.mark},#{paper.number},#{paper.time},#{paper.teacher},#{paper.ext})")
    @Options(useGeneratedKeys =true,keyProperty = "paper.id" ,keyColumn = "id")
    public void  addPaper(@Param("paper") Paper paper)  throws Exception;

    // 删除
    @Delete("delete from t_paper where id= #{id}")
    public void  deletePaperById(Integer id) throws Exception;

    // 修改
    @Update("update t_paper  set  name=#{name},mark=#{mark},number=#{number},time=#{time},teacher=#{teacher},ext=#{ext} where id= #{id}")
    public void  updatePaper(Paper paper)  throws Exception;



}
