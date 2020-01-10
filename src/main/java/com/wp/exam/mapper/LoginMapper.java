package com.wp.exam.mapper;

import java.util.Map;

public interface LoginMapper {
    Map<String,Object> loginAdmin(String username);
    Map<String,Object> loginTeacher(String username);
    Map<String,Object> loginStudent(String username);
}
