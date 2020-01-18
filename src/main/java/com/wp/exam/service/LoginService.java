package com.wp.exam.service;

import java.util.Map;

public interface LoginService {
    Map<String,Object> login(Map<String,Object> param) throws Exception;
    Map<String,Object> logout(Map<String,Object> param) throws Exception;
    Map<String,Object> info(Map<String,Object> param) throws Exception;

}
