package com.wp.exam.mapper;

import java.util.List;
import java.util.Map;

public interface QuestionMapper {

    void importQuestion(Map<String, Object> param);

    void insertQuestion(Map<String, Object> param);

    Map<String, Object> getQuestion(String id);

    void deleteQuestion(Map<String, Object> param);

    void updateQuestion(Map<String, Object> param);

    int getQuestionByIdAndNum(Map<String, Object> param);

    List<Map<String, Object>> search(Map<String, Object> param);

    int searchCount(Map<String, Object> param);
}
