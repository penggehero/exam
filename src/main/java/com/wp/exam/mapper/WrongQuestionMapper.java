package com.wp.exam.mapper;

import java.util.List;
import java.util.Map;

public interface WrongQuestionMapper {

    void addWrongQuestion(Map<String, Object> param);

    List<Map<String, Object>> search(Map<String, Object> param);

    List<Map<String, Object>> findPaperID(Map<String, Object> param);

    Map<String, Object> findPaperNameById(Map<String, Object> param);

    int countWrony(Map<String, Object> param);

    int searchCount(Map<String, Object> param);

}
