package com.wp.exam.controller;

import com.wp.exam.service.QuestionService;
import com.wp.exam.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    private Map<String, Object> result = null;

    @GetMapping("/search")
    public Map<String, Object> search(@RequestParam Map<String, Object> param) {
        try {
            result = questionService.search(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    @PutMapping("/delete")
    public Map<String, Object> delete(@RequestBody Map<String, Object> param) {
        try {
            return questionService.delete(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Map<String, Object> param) {
        try {
            return questionService.add(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, Object> param) {
        try {
            return questionService.update(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }
}
