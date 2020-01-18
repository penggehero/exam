package com.wp.exam.mapper;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

    void addStudent(Map<String, Object> param);

    void deleteStudent(Map<String, Object> param);

    void updateStudent(Map<String, Object> param);

    List<Map<String, Object>> findbyConditions(Map<String, Object> param);

    int findbyConditionsCount(Map<String, Object> param);
}
