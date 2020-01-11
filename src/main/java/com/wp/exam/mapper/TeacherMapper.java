package com.wp.exam.mapper;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {

    void addTeacher(Map<String, Object> param);

    void deleteTeacher(Map<String, Object> param);

    void updateTeacher(Map<String, Object> param);

    List<Map<String, Object>> findbyConditions(Map<String, Object> param);

    int findbyConditionsCount(Map<String, Object> param);
}
