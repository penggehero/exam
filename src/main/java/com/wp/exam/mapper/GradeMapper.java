package com.wp.exam.mapper;

import java.util.List;
import java.util.Map;

public interface GradeMapper {

    void addGrade(Map<String, Object> param);

    void deleteGrade(Map<String, Object> param);

    void updateGrade(Map<String, Object> param);

    List<Map<String, Object>> search(Map<String, Object> param);

    int searchCount(Map<String, Object> param);

    int findbyConditionsCount(Map<String, Object> param);
}
