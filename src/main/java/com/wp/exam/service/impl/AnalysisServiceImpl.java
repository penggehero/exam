package com.wp.exam.service.impl;

import com.wp.exam.mapper.GradeMapper;
import com.wp.exam.mapper.PaperMapper;
import com.wp.exam.mapper.StudentMapper;
import com.wp.exam.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AnalysisServiceImpl implements AnalysisService {

    private static final Logger log = LoggerFactory.getLogger(AnalysisServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private PaperMapper paperMapper;

    @Override
    public Map<String, Object> grade(Map<String, Object> param) throws Exception {
        long start = System.currentTimeMillis();
        log.info("AnalysisServiceImpl grade start ...{}", param);
        Map<String, Object> result = new HashMap<>();
        result.put("DoneList", MakeResult1(studentMapper.findbyConditionsCount(param), gradeMapper.searchCount(param)));
        result.put("AVGList", MakeResult2(gradeMapper.analysis(param)));
        List<Map<String, Object>> areaList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> map : MakeResult3(paperMapper.findById(param))) {
            HashMap<String, Object> markArea = new HashMap<>();
            markArea.put("MarkArea", sb.append(map.get("start")).append("-").append(map.get("end")).append("分").toString());
            sb.setLength(0);
            markArea.put("StudentNumber", gradeMapper.CountBetween(map));
            areaList.add(markArea);
        }
        result.put("AreaList", areaList);
        System.out.println("AnalysisServiceImpl grade 消耗时间为" + (System.currentTimeMillis() - start));
        log.info("AnalysisServiceImpl grade end ...");
        return result;
    }

    public static List<Map<String, Object>> MakeResult1(int studentNumber, int doneNumber) {
        Map<String, Object> done = new LinkedHashMap<>();
        Map<String, Object> notdone = new LinkedHashMap<>();
        done.put("完成情况", "已完成人数");
        done.put("人数", doneNumber);
        notdone.put("完成情况", "未完成人数");
        notdone.put("人数", studentNumber - doneNumber);
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(done);
        list.add(notdone);
        return list;
    }

    public static List<Map<String, Object>> MakeResult2(Map<String, Object> analysis) {
        Map<String, Object> min = new LinkedHashMap<>();
        Map<String, Object> max = new LinkedHashMap<>();
        Map<String, Object> avg = new LinkedHashMap<>();
        min.put("Type", "最低分");
        min.put("单选题", analysis.get("min(single_mark)"));
        min.put("多选题", analysis.get("min(double_mark)"));
        min.put("判断题", analysis.get("min(judge_mark)"));
        min.put("总分", analysis.get("min(mark)"));

        max.put("Type", "最高分");
        max.put("单选题", analysis.get("max(single_mark)"));
        max.put("多选题", analysis.get("max(double_mark)"));
        max.put("判断题", analysis.get("max(judge_mark)"));
        max.put("总分", analysis.get("max(mark)"));

        avg.put("Type", "平均分");
        avg.put("单选题", analysis.get("avg(single_mark)"));
        avg.put("多选题", analysis.get("avg(double_mark)"));
        avg.put("判断题", analysis.get("avg(judge_mark)"));
        avg.put("总分", analysis.get("avg(mark)"));
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(min);
        list.add(max);
        list.add(avg);
        return list;
    }

    public static List<Map<String, Object>> MakeResult3(Map<String, Object> param) {
        int mark = Integer.valueOf(param.get("mark").toString());
        List<Map<String, Object>> list = new LinkedList<>();
        int start = 0;
        int temp = mark % 20 == 0 ? mark / 20 : mark / 20 + 1;
        for (int i = 0; i < temp; i++, start += 20) {
            Map<String, Object> map = new HashMap<>();
            if (i == 0) {
                map.put("start", start);
                map.put("end", start + 20);
            } else if (i == temp - 1) {
                map.put("start", start + 1);
                map.put("end", mark);
            } else {
                map.put("start", start + 1);
                map.put("end", start + 20);
            }
            list.add(map);
        }
        return list;

    }
}
