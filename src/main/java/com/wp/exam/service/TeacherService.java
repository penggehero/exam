package com.wp.exam.service;

import java.util.Map;

public interface TeacherService {

    void addTeacher(Map<String, Object> param) throws Exception;

    void deleteTeacher(Map<String, Object> param) throws Exception;;

    void updateTeacher(Map<String, Object> param) throws Exception;;

    Map<String, Object> findbyConditions(Map<String, Object> param) throws Exception;

    Map<String, Object> findAll(Map<String, Object> param) throws Exception;

}
