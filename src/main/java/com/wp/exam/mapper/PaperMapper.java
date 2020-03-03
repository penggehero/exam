package com.wp.exam.mapper;

import java.util.List;
import java.util.Map;

public interface PaperMapper {

    void importPaper(Map<String, Object> param);

    void updatePaper(Map<String, Object> param);

    Map<String,Object> search(Map<String, Object> param) throws Exception;

    List<Map<String, Object>> getQuestion(Map<String, Object> param) throws Exception;

    Map<String, Object> getTimeAndName(Map<String, Object> param) throws Exception;

}
