package com.wp.exam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/choose")
public class ChooseController {


    @RequestMapping("/do")
    public String h2(Model model, @RequestParam(value = "start",required = false,defaultValue = "1") int start){
       /* Paper paper = chooseMapper.findById(1);
        List<Choose> chooses = chooseMapper.findByPaperid(1);
        model.addAttribute(paper);
        model.addAttribute(chooses);*/
        return "h2";
    }
}
