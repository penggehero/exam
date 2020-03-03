package com.wp.exam.service;

import java.util.Map;

public interface GradeService {

    Map<String, Object> addGrade(Map<String, Object> param) throws Exception;

    Map<String, Object> deleteGrade(Map<String, Object> param) throws Exception;;

    Map<String, Object> updateGrade(Map<String, Object> param) throws Exception;;

    Map<String, Object> findbyConditions(Map<String, Object> param) throws Exception;

    Map<String, Object> findAll(Map<String, Object> param) throws Exception;
}
