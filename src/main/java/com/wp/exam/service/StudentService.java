package com.wp.exam.service;

import java.util.Map;

public interface StudentService {

    Map<String, Object> addStudent(Map<String, Object> param) throws Exception;

    Map<String, Object> deleteStudent(Map<String, Object> param) throws Exception;;

    Map<String, Object> updateStudent(Map<String, Object> param) throws Exception;;

    Map<String, Object> findbyConditions(Map<String, Object> param) throws Exception;

    Map<String, Object> findAll(Map<String, Object> param) throws Exception;

}
