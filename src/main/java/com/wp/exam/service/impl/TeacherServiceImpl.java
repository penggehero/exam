package com.wp.exam.service.impl;

import com.wp.exam.entity.Teacher;
import com.wp.exam.mapper.TeacherMapper;
import com.wp.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher findTeacherById(Integer id) throws Exception {
        return teacherMapper.findTeacherById(id);
    }

    @Override
    public void addTeacher(Teacher teacher) throws Exception {
        teacherMapper.addTeacher(teacher);
    }

    @Override
    public void deleteTeacher(Integer id) throws Exception {
        teacherMapper.deleteTeacher(id);
    }

    @Override
    public void updateTacher(Teacher teacher) throws Exception {
        teacherMapper.updateTacher(teacher);
    }
}
