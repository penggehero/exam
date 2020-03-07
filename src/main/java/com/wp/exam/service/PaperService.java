package com.wp.exam.service;

import java.io.File;
import java.util.Map;

public interface PaperService {

     Map<String,Object> importPaper(File file,Map<String, Object> param) throws Exception;

     Map<String,Object> search(Map<String, Object> param) throws Exception;

     Map<String,Object> findByTeacher(Map<String, Object> param) throws Exception;

     Map<String,Object> submit(Map<String, Object> param) throws Exception;

     Map<String,Object> getQuestion(Map<String, Object> param) throws Exception;


}
