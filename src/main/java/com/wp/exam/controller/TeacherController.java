package com.wp.exam.controller;


import com.wp.exam.entity.Grade;
import com.wp.exam.entity.PageBean;
import com.wp.exam.entity.Student;
import com.wp.exam.service.GradeService;
import com.wp.exam.service.PaperService;
import com.wp.exam.service.StudentService;
import com.wp.exam.util.ExcelUtil;
import javafx.print.PaperSource;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;

    @Autowired
    private PaperService paperService;

    /**
     * 学生信息管理
     *
     * @param model
     * @param session
     * @param start
     * @param id
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping("/manage")
    public String manage(Model model, HttpSession session,
                         @RequestParam(value = "start", required = false, defaultValue = "1") Integer start,
                         @RequestParam(value = "id", required = false) Integer id,
                         @RequestParam(value = "name", required = false) String name) throws Exception {
        PageBean<Student> page = null;
        List<Student> students = null;
        if (session.getAttribute("studentList") == null) {
            //第一次加载
            if (name == null)
                page = new PageBean<Student>(start, 5, new ArrayList<>());
            else {
                //搜索时
                students = studentService.searchStudents(id, name);
                page = new PageBean<Student>(start, 5, students);
                session.setAttribute("studentList", students);
                session.setAttribute("id", id);
                session.setAttribute("name", name);
            }
        } else {
            //翻页时
            if (id == null && name == null && name == null) {
                students = (List<Student>) session.getAttribute("studentList");
                page = new PageBean<Student>(start, 5, students);
            } else {
                //搜索时
                session.removeAttribute("studentList");
                students = studentService.searchStudents(id, name);
                session.setAttribute("studentList", students);
                page = new PageBean<Student>(start, 5, students);
                session.setAttribute("id", id);
                session.setAttribute("name", name);
            }
        }
        model.addAttribute("page", page);
        return "teacher/teacherManage";
    }


    /**
     * 删除学生
     *
     * @param sid
     * @return
     * @throws Exception
     */
    @RequestMapping("/delstudent")
    @ResponseBody()
    public Map deleteStudent(HttpSession session, Integer sid) throws Exception {
        Map<String, String> map = new HashMap<>();
        try {
            studentService.deleteStudent(sid);
            map.put("msg", "success");
            session.removeAttribute("studentList");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "删除失败!");
        }
        return map;
    }

    /**
     * 学生成绩查询
     *
     * @param model
     * @param session
     * @param start
     * @param s_id
     * @param s_name
     * @param p_name
     * @return
     * @throws Exception
     */
    @RequestMapping("/grade")
    public String grade(Model model, HttpSession session,
                        @RequestParam(value = "start", required = false, defaultValue = "1") Integer start,
                        @RequestParam(value = "s_id", required = false) Integer s_id,
                        @RequestParam(value = "s_name", required = false) String s_name,
                        @RequestParam(value = "p_name", required = false) String p_name) throws Exception {
        PageBean<Grade> page = null;
        List<Grade> grades = null;
        if (session.getAttribute("gradeList") == null) {
            //第一次加载
            if (s_name == null && p_name == null) {
                System.out.println("第一次加载");
                page = new PageBean<Grade>(start, 5, new ArrayList<>());
            } else {
                //搜索时
                System.out.println("第一次搜索时");
                grades = gradeService.searchGrades(s_id, s_name, p_name);
                session.setAttribute("gradeList", grades);
                page = new PageBean<Grade>(start, 5, grades);
                session.setAttribute("s_id", s_id);
                session.setAttribute("s_name", s_name);
                session.setAttribute("p_name", p_name);
            }
        } else {
            //翻页时
            if (s_id == null && s_name == null && p_name == null) {
                System.out.println("以后翻页时");
                grades = (List<Grade>) session.getAttribute("gradeList");
                page = new PageBean<Grade>(start, 5, grades);
            } else {
                //搜索时
                System.out.println("以后搜索时");
                session.removeAttribute("gradeList");
                grades = gradeService.searchGrades(s_id, s_name, p_name);
                session.setAttribute("gradeList", grades);
                page = new PageBean<Grade>(start, 5, grades);
                session.setAttribute("s_id", s_id);
                session.setAttribute("s_name", s_name);
                session.setAttribute("p_name", p_name);
            }
        }
        model.addAttribute("page", page);
        return "teacher/studentGrade";
    }

    /**
     * 模板下载
     *
     * @param filename
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/download", produces = "application/octet-stream;charset=UTF-8")
    public ResponseEntity<byte[]> download(@RequestParam("filename") String filename) throws IOException {
        String path = TeacherController.class.getClassLoader().getResource("static").getPath() + File.separator + "file" + File.separator + filename;
        File file = new File(path);
        filename = URLEncoder.encode(filename, "UTF-8");
        /*String fileName=new String(file.getName().getBytes("utf-8"),"iso-8859-1"); //解决中文乱码问题*/
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        HttpStatus statusCode = HttpStatus.OK;
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, statusCode);
    }

    //上传试题并创建试卷
    @PostMapping("/fileUpload")
    String upload(@RequestParam("files") MultipartFile file, String name, Integer time, String teacher, Model model) {
        File newFile = null;
        Map<String, String> map = null;
        String state = null;
        String reason = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-");
        String fileName = file.getOriginalFilename();
        //创建新文件 通过时间和UUID唯一标识
        String path = TeacherController.class.getClassLoader().getResource("static").getPath() + File.separator + "file" + File.separator + simpleDateFormat.format(new Date()) + UUID.randomUUID().toString() + fileName;
        newFile = new File(path);
        //加将上传的文件保存到新文件
        try {
            file.transferTo(newFile);
            state = paperService.addPaper(newFile, name, time, teacher);
        } catch (Exception e) {
            state = "fail";
            reason = e.getMessage();
        } finally {
            if (newFile.exists())
                newFile.delete();
        }
        if (null != state) {
            if (state.equals("fail")) {
                System.out.println("失败原因:" + reason);
                model.addAttribute("msg", "fail");
                model.addAttribute("reason", reason);
                model.addAttribute("name", name);
                model.addAttribute("time", time);
                model.addAttribute("teacher", teacher);
            } else {
                model.addAttribute("msg", state);
            }
        }
        return "teacher/addPaper";
    }





  /*  @Autowired
    private TeacherMapper teacherMapper;


    @RequestMapping("/register")
    @ResponseBody()
    public Map register(Teacher teacher,String invitecode) {
        Map<String,String> map=new HashMap<>();
        if (invitecode == null || !"abc123".equals(invitecode)) {
            map.put("msg", "邀请码错误或者为空!");
            return map;
        }
        try {
            teacherMapper.insert(teacher);
            map.put("msg","success");
        }catch (DuplicateKeyException e){
            e.printStackTrace();
            map.put("msg","用户名已存在!");
        }
        return map;
    }*/

}
