package com.wp.exam.controller;

import com.wp.exam.service.GradeService;
import com.wp.exam.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/grade")
public class GradeController {
    private Map<String, Object> result = null;

    @Autowired
    private GradeService gradeService;

    @GetMapping("/search")
    public Map<String, Object> search(@RequestParam Map<String, Object> param) {
        try {
            result = ServiceUtil.makeResult(gradeService.search(param), null);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, Object> param) {
        try {
            result = gradeService.updateGrade(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    @PutMapping("/delete")
    public Map<String, Object> delete(@RequestBody Map<String, Object> param) {
        try {
            result = gradeService.deleteGrade(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }


}
