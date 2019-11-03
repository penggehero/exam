package com.wp.exam.controller;


import com.wp.exam.entity.Grade;
import com.wp.exam.entity.PageBean;
import com.wp.exam.entity.Student;
import com.wp.exam.service.GradeService;
import com.wp.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StuentController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    /**
     * 查询成绩
     *
     * @param model
     * @param start
     * @return
     * @throws Exception
     */
    @RequestMapping("/grade")
    public String showGrades(HttpSession session, Model model, @RequestParam(value = "start", required = false, defaultValue = "1") int start) throws Exception {
        List<Grade> list = (List<Grade>) session.getAttribute("list");
        if (list == null) {
            session.setAttribute("list", gradeService.findAll());
            list = gradeService.findAll();
        }
        PageBean<Grade> grades = new PageBean<Grade>(start, 5, list);
        model.addAttribute("grades", grades);
        System.out.println(grades.toString());
        return "student/studentGrade";
    }

    /**
     * 个人信息修改
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/info")
    public String showInfo(HttpSession session, Model model, @RequestParam(value = "sid", required = false) Integer sid) throws Exception {
        if (sid == null) {
            Integer id = (Integer) session.getAttribute("id");
            model.addAttribute("student", studentService.findStudentById(id));
            return "student/studentInfo";
        } else {
            session.removeAttribute("studentList");
            model.addAttribute("student", studentService.findStudentById(sid));
            return "teacher/studentInfo";
        }
    }

    /**
     * 更新
     *
     * @param student
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    @ResponseBody()
    public Map updateInfo(Student student) throws Exception {
        Map<String, String> map = new HashMap<>();
        try {
            studentService.updateStudent(student);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "更新失败!");
        }
        return map;
    }





    @RequestMapping("/layout")
    private String layout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/";
    }

   /* private SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private StudentService studentService;

    @Autowired
    private SingleselectService singleselectService;

    *//**
     * 学生注册
     *
     * @param student
     * @return
     *//*
    @RequestMapping("/register")
    @ResponseBody()
    public Map register(Student student, String invitecode) {
        System.out.println(student);
        Map<String, String> map = new HashMap<>();
        if (invitecode == null || !"abc123".equals(invitecode)) {
            map.put("msg", "邀请码错误或者为空!");
            return map;
        }
        try {
            studentService.insert(student);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "用户名已存在!");
        }
        return map;
    }

    *//**
     * 获取考试完成时间
     *
     * @return
     * @throws ParseException
     *//*
    @RequestMapping("/getTime")
    @ResponseBody()
    public Map getTime() throws ParseException {
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", String.valueOf(ft.parse("2019-07-28 11:06:48").getTime()));
        return map;
    }

    *//**
     * 前往考试中心,并获取考试情况
     *
     * @param model
     * @param request
     * @return
     *//*
    @RequestMapping("/center")
    public String register(Model model, HttpServletRequest request) {
        Student curcent = studentService.findByStudentName((String) request.getSession().getAttribute("username"));
        request.getSession().setAttribute("studentid", curcent.getId());
        model.addAttribute("paperdone", studentService.getPaperDoneByStudentId(curcent.getId()));
        return "student/student";
    }

    @RequestMapping("/exam")
    public String exam(Model model) {
        List<Singleselect> singleselectList = singleselectService.findSingleselectsByPaperId(2);
        model.addAttribute(singleselectList);
        return "student/doexam";
    }*/

}
