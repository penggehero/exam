package com.wp.exam.service.impl;

import com.wp.exam.entity.Grade;
import com.wp.exam.mapper.GradeMapper;
import com.wp.exam.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;


    @Override
    public List<Grade> findAll() throws Exception {
        return gradeMapper.findAllGrade();
    }

    @Override
    public List<Grade> searchGrades(Integer s_id, String s_name, String p_name) throws Exception {
        String s = " ";
        if (s_id == null && (s_name == null || s_name.trim().length() == 0) && (p_name == null || p_name.trim().length() == 0)) {
            return gradeMapper.findAllGrade();
        } else {
            if (s_name == null|| s_name.trim().length() == 0) {
                s_name = s;
            }
            if (p_name == null || p_name.trim().length() == 0) {
                p_name = s;
            }
            if (s_id == null) {
               return gradeMapper.searchGrades("%"  + " %", "%" + s_name + "%", "%" + p_name + "%");
            } else
                return gradeMapper.searchGrades("%" + s_id + "%", "%" + s_name + "%", "%" + p_name + "%");
        }
    }
}

