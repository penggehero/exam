package com.wp.exam.service;

import java.util.Map;

public interface QuestionService {

    Map<String, Object> search(Map<String, Object> param) throws Exception;

    Map<String, Object> delete(Map<String, Object> param) throws Exception;

    Map<String, Object> add(Map<String, Object> param) throws Exception;

    Map<String, Object> update(Map<String, Object> param) throws Exception;

}
