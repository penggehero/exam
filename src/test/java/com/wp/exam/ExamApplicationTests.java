package com.wp.exam;

import com.wp.exam.entity.Grade;
import com.wp.exam.entity.Paper;
import com.wp.exam.entity.Question;
import com.wp.exam.entity.Teacher;
import com.wp.exam.mapper.PaperMapper;
import com.wp.exam.mapper.QuestionMapper;
import com.wp.exam.service.GradeService;
import com.wp.exam.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamApplicationTests {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private PaperMapper paperMapper;

    @Test
    public void contextLoads() throws Exception {


    }

}
