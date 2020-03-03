package com.wp.exam.controller;

import com.wp.exam.service.StudentService;
import com.wp.exam.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    private Map<String, Object> result = null;
    @Autowired
    private StudentService studentService;

    /**
     * 搜索学生
     * @param param
     * @return
     */
    @GetMapping("/search")
    public Map<String, Object> search(@RequestParam Map<String, Object> param) {
        try {
            result = ServiceUtil.makeResult(studentService.findbyConditions(param), null);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    /**
     * 添加学生
     * @param param
     * @return
     */
    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Map<String, Object> param) {
        try {
            return studentService.addStudent(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    /**
     * 删除学生
     * @param param
     * @return
     */
    @PutMapping("/delete")
    public Map<String, Object> delete(@RequestBody Map<String, Object> param) {
        try {
            return   studentService.deleteStudent(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    /**
     * 更改学生
     * @param param
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, Object> param) {
        try {
            return studentService.updateStudent(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

}
