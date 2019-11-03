package com.wp.exam.controller;

import com.wp.exam.entity.Question;
import com.wp.exam.mapper.QuestionMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

@Controller
public class Test {
    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping("/test")
    public String test(Model model)  throws Exception{
        List<Question> allQuestions = questionMapper.findAllQuestions();
        Collections.sort(allQuestions);
        model.addAttribute("questions",allQuestions);
        return "examTest";
    }


}
