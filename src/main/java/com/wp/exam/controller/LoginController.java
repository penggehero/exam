package com.wp.exam.controller;

import com.wp.exam.service.LoginService;
import com.wp.exam.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private  Map<String, Object> result=null;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> param) {
        try {
            result = loginService.login(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        System.out.println(result);
        return result;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestBody(required = false) Map<String, Object> param) {
        try {
            result = loginService.logout(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        System.out.println(result);
        return result;
    }


    @GetMapping("/info")
    public Map<String, Object> info(@RequestParam Map<String, Object> param) {
        try {
            result = loginService.info(param);
        } catch (Exception e) {
            e.printStackTrace();
            result = ServiceUtil.makeResult(null, ServiceUtil.UNKNOWNERROR);
        }
        return result;
    }



}
