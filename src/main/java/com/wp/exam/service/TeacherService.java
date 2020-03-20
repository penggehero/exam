package com.wp.exam.service;

import java.util.Map;

public interface TeacherService {

    Map<String, Object> addTeacher(Map<String, Object> param) throws Exception;

    Map<String, Object> deleteTeacher(Map<String, Object> param) throws Exception;

    Map<String, Object> updateTeacher(Map<String, Object> param) throws Exception;

    Map<String, Object> findbyConditions(Map<String, Object> param) throws Exception;

    Map<String, Object> searchPerson(Map<String, Object> param) throws Exception;

    Map<String, Object> findAll(Map<String, Object> param) throws Exception;

}
