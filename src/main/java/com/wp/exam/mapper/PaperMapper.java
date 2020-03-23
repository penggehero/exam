package com.wp.exam.mapper;

import java.util.List;
import java.util.Map;

public interface PaperMapper {

    void importPaper(Map<String, Object> param);

    void updatePaper(Map<String, Object> param);

    void updateDelQuestion(Map<String, Object> param);

    void updateQuestion(Map<String, Object> param);

    void updateAddQuestion(Map<String, Object> param);

    List<Map<String, Object>> search(Map<String, Object> param);

    List<Map<String, Object>> findByTeacher(Map<String, Object> param);

    int searchCount(Map<String, Object> param);

    int findByTeacherCount(Map<String, Object> param);

    Map<String, Object> findById(Map<String, Object> param);


    List<Map<String, Object>> getQuestion(Map<String, Object> param) throws Exception;

    Map<String, Object> getTimeAndName(Map<String, Object> param) throws Exception;

}
