package com.wp.exam.controller;

import com.wp.exam.service.LoginService;
import com.wp.exam.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    private Map<String, Object> result = null;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> param) {
        try {
            result = loginService.login(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, "发送未知错误!");
        }
        return result;
    }

}
