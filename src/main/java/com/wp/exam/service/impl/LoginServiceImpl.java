package com.wp.exam.service.impl;

import com.wp.exam.mapper.LoginMapper;
import com.wp.exam.service.LoginService;
import com.wp.exam.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    private static final String ERRORNAME = "用户名错误或者不存在!";
    private static final String ERRORPWD = "密码错误!";
    @Autowired
    private LoginMapper loginMapper;

    /**
     * 用户登录
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> login(Map<String, Object> param) throws Exception {
        log.info("login start ...{}", param);
        //return makeResult(1, null);
        String  username=param.get("username").toString();;
        String password = param.get("password").toString();
        Map<String, Object> admin = loginMapper.loginAdmin(username);
        if (admin != null) {
            if (admin.get("password").equals(password))
                return makeResult(1, Integer.valueOf(admin.get("id").toString()), username, null);
            else
                return makeResult(null, 0, null, ERRORPWD);
        }
        Map<String, Object> teacher = loginMapper.loginTeacher(username);
        if (teacher != null) {
            username=teacher.get("name").toString();
            if (teacher.get("password").equals(password))
                return makeResult(2, teacher.get("work_id"), username, null);
            else
                return makeResult(null, 0, null, ERRORPWD);
        }
        Map<String, Object> student = loginMapper.loginStudent(username);
        if (student != null) {
            username=student.get("name").toString();
            if (student.get("password").equals(password))
                return makeResult(3, student.get("school_id").toString(), username, null);
            else
                return makeResult(null, 0, null, ERRORPWD);
        }
        log.info("login end ...");
        return makeResult(null, 0, null, ERRORNAME);
    }

    /**
     * 用户信息获取
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> info(Map<String, Object> param) throws Exception {
        log.info("info start ...{}", param);
        Map<String, Object> map = new HashMap<>();
        map.put("1", "admin");
        map.put("2", "teacher");
        map.put("3", "student");
        List<String> roleList = new LinkedList<>();
        roleList.add(map.get(param.get("token").toString()).toString());
        map.clear();
        map.put("roles", roleList);
        map.put("introduction", "Java Web Exam System!");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        log.info("info end ...");
        return map;
    }

    public static Map<String, Object> makeResult(Object token,Object id, Object username, String errmsg) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> result = new HashMap<>();
        if (null == errmsg) {
            result.put("status", 1);
            result.put("token", token);
            result.put("id", id);
            result.put("username", username);
            result.put("timestamp", format.format(new Date()));
        } else {
            result.put("msg", errmsg);
            result.put("status", 0);
            result.put("token", null);
            result.put("username", null);
            result.put("timestamp", format.format(new Date()));
        }
        return result;
    }

    @Override
    public Map<String, Object> logout(Map<String, Object> param) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "success");
        return result;
    }
}
