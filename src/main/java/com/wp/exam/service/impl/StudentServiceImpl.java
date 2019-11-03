package com.wp.exam.service.impl;

import com.wp.exam.entity.Student;
import com.wp.exam.mapper.StudentMapper;
import com.wp.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findStudentById(Integer id) throws Exception {
        return studentMapper.findStudentById(id);
    }

    @Override
    public void updateStudent(Student student) throws Exception {
        studentMapper.updateStudent(student);
    }

    @Override
    public List<Student> searchStudents(Integer id, String name) throws Exception {
        if (id == null && (name == null || name.trim().length() == 0)) {
            return studentMapper.findAllStudent();
        }
        if (name == null || name.trim().length() == 0) {
            return studentMapper.findStudentlikeId("%" + id + "%");
        } else if (id == null) {
            return studentMapper.findStudentlikeName("%" + name + "%");
        } else return studentMapper.findStudentlikeIDAndName("%" + id + "%", "%" + name + "%");
    }

    @Override
    public void deleteStudent(Integer id) throws Exception {
        studentMapper.deleteStudentById(id);
    }
}
