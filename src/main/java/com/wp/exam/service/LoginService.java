package com.wp.exam.service;

import java.util.Map;

public interface LoginService {
    Map<String,Object> login(Map<String,Object> param) throws Exception;
}
