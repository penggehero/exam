package com.wp.exam.service;

import java.util.Map;

public interface AnalysisService {

    Map<String, Object> grade(Map<String, Object> param) throws Exception;

    Map<String, Object> wrong(Map<String, Object> param) throws Exception;
}
