package com.wp.exam.service;


import java.io.InputStream;
import java.util.Map;

public interface PaperService {

     Map<String,Object> importPaper(InputStream inputStream, Map<String, Object> param) throws Exception;

     Map<String,Object> search(Map<String, Object> param) throws Exception;

     Map<String,Object> findByTeacher(Map<String, Object> param) throws Exception;

     Map<String,Object> submit(Map<String, Object> param) throws Exception;

     Map<String,Object> getQuestion(Map<String, Object> param) throws Exception;


}
