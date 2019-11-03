package com.wp.exam.service;

import com.wp.exam.entity.Grade;

import java.util.List;

public interface GradeService {
    public List<Grade> findAll() throws Exception;


    public List<Grade> searchGrades(Integer s_id, String s_name,String p_name) throws Exception;
}
