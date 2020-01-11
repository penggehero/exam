package com.wp.exam.controller;

import com.wp.exam.service.TeacherService;
import com.wp.exam.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class AdminController {
    private Map<String, Object> result = null;
    @Autowired
    private TeacherService teacherService;

    /**
     * 搜索教师
     *
     * @param param
     * @return
     */
    @GetMapping("/search")
    public Map<String, Object> findArea(@RequestParam Map<String, Object> param) {
        try {
            result = ServiceUtil.makeResult(teacherService.findbyConditions(param), null);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    /**
     * 添加教师
     * @param param
     * @return
     */
    @PostMapping("/add")
    public Map<String, Object> addArea(@RequestBody Map<String, Object> param) {
        try {
            teacherService.addTeacher(param);
            result = ServiceUtil.makeResult(null, null);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    /**
     * 删除教师
     * @param param
     * @return
     */
    @PutMapping("/delete")
    public Map<String, Object> deleteArea(@RequestBody Map<String, Object> param) {
        try {
            teacherService.deleteTeacher(param);
            result = ServiceUtil.makeResult(null, null);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    /**
     * 更改教师
     * @param param
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> updateArea(@RequestBody Map<String, Object> param) {
        try {
            teacherService.updateTeacher(param);
            result = ServiceUtil.makeResult(null, null);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

}