package com.wp.exam.service;

import com.wp.exam.entity.Teacher;

public interface TeacherService {

    public Teacher findTeacherById(Integer id) throws Exception;

    public void addTeacher(Teacher teacher) throws Exception;


    public void deleteTeacher(Integer id) throws Exception;

    public void updateTacher(Teacher teacher) throws Exception;

}
