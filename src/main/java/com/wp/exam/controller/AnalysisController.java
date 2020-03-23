package com.wp.exam.controller;

import com.wp.exam.service.AnalysisService;
import com.wp.exam.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    private Map<String, Object> result = null;
    @Autowired
    private AnalysisService analysisService;
    @GetMapping("/grade")
    public Map<String, Object> grade(@RequestParam Map<String, Object> param) {
        try {
            result = ServiceUtil.makeResult(analysisService.grade(param), null);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }
}
