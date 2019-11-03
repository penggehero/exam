package com.wp.exam.controller;


import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
  /*  @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @RequestMapping("/login")
    @ResponseBody()
    public Map login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        Teacher teacher = teacherMapper.findByUserName(username);
        if (teacher != null && password.equals(teacher.getPassword())) {
            request.getSession().setAttribute("username", teacher.getUsername());
            map.put("role", "teacher");
            map.put("msg", "success");
            return map;
        }
        Student student = studentMapper.findByUserName(username);
        if (student != null && password.equals(student.getPassword())) {
            request.getSession().setAttribute("username", student.getUsername());
            map.put("role", "student");
            map.put("msg", "success");
            return map;
        }
        map.put("msg", "用户名或密码错误!");
        return map;
    }

    @RequestMapping("/layout")
    public String layout(HttpServletRequest request) {
        //清空session的所有信息
        request.getSession().invalidate();
        return "redirect:/";
    }*/



}
