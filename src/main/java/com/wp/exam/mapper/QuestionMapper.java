package com.wp.exam.mapper;

import java.util.Map;

public interface QuestionMapper {

    void importQuestion(Map<String, Object> param);

    Map<String, Object> getQuestion(String id);
}
