package com.wp.exam.controller;

import com.wp.exam.service.GradeService;
import com.wp.exam.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
