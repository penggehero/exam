package com.wp.exam.service.impl;

import com.wp.exam.mapper.LoginMapper;
import com.wp.exam.service.LoginService;
import com.wp.exam.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    private static final String ERRORNAME = "用户名错误!";
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
        String username = param.get("username").toString();
        String password = param.get("password").toString();
        Map<String, Object> admin = loginMapper.loginAdmin(username);
        if (admin != null) {
            if (admin.get("password").equals(password))
                return ServiceUtil.makeResult(1, null);
            else
                return ServiceUtil.makeResult(null, ERRORPWD);
        }
        Map<String, Object> teacher = loginMapper.loginTeacher(username);
        if (teacher != null) {
            if (teacher.get("password").equals(password))
                return ServiceUtil.makeResult(2, null);
            else
                return ServiceUtil.makeResult(null, ERRORPWD);
        }
        Map<String, Object> student = loginMapper.loginStudent(username);
        if (student != null) {
            if (student.get("password").equals(password))
                return ServiceUtil.makeResult(3, null);
            else
                return ServiceUtil.makeResult(null, ERRORPWD);
        }
        log.info("login end ...");
        return ServiceUtil.makeResult(null, ERRORNAME);
    }

}
