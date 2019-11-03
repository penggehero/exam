package com.wp.exam.service;


import com.wp.exam.entity.Student;

import java.util.List;

public interface StudentService {

    public Student findStudentById(Integer id) throws Exception;

    public void updateStudent(Student student) throws Exception;

    public List<Student> searchStudents(Integer id, String name) throws Exception;

    public void deleteStudent(Integer id) throws Exception;


}
