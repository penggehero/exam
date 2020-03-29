package com.wp.exam.service.impl;

import com.wp.exam.mapper.*;
import com.wp.exam.service.AnalysisService;
import com.wp.exam.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
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
    @Autowired
    private WrongQuestionMapper wrongQuestionMapper;

    @Override
    public Map<String, Object> grade(Map<String, Object> param) throws Exception {
        log.info("AnalysisServiceImpl grade start ...{}", param);
        long start = System.currentTimeMillis();
        Map<String, Object> result = new HashMap<>();
        result.put("DoneList", MakeResult1(studentMapper.findbyConditionsCount(param), gradeMapper.searchCount(param)));
        result.put("AVGList", MakeResult2(gradeMapper.analysis(param)));
        List<Map<String, Object>> areaList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        MakeResult3(paperMapper.findById(param)).forEach(map -> {
            HashMap<String, Object> markArea = new HashMap<>();
            markArea.put("MarkArea", sb.append(map.get("start")).append("-").append(map.get("end")).append("分").toString());
            sb.setLength(0);
            markArea.put("StudentNumber", gradeMapper.CountBetween(map));
            areaList.add(markArea);
        });
        result.put("AreaList", areaList);
        System.out.println("总耗时： " + (System.currentTimeMillis() - start));
        log.info("AnalysisServiceImpl grade end ...");
        return result;
    }

    /**
     * 错误率计算 出错题目数量/完成次数
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> wrong(Map<String, Object> param) throws Exception {
        log.info("AnalysisServiceImpl wrong start ...{}", param);
        long start = System.currentTimeMillis();
        int done = gradeMapper.searchCount(param);
        List<Map<String, Object>> question = paperMapper.getQuestion(param);
        if (done > 0) {
            question.forEach(map -> {
                int wrongNum = wrongQuestionMapper.countWrony(map);
                map.put("wrongNum", wrongNum);
                map.put("wrong", percentFormat(wrongNum, done));
            });
            // 对错误率排序
            Collections.sort(question, Comparator.comparing(s -> (int) s.get("wrongNum"), Comparator.reverseOrder()));
        } else {
            question.forEach(map -> {
                map.put("wrong", "0%");
                map.put("wrongNum", 0);
            });
        }
        //前端数据转换
        ServiceUtil.WebFormat(question);
        System.out.println("总耗时： " + (System.currentTimeMillis() - start));
        log.info("AnalysisServiceImpl wrong end ...");
        return ServiceUtil.makeResult(question, null);
    }

    /**
     * 百分比转换
     *
     * @param x
     * @param y
     * @return
     */
    public static String percentFormat(int x, int y) {
        return new DecimalFormat("#.##%").format((double) x / y);
    }

    /**
     * 获取考试完成情况
     *
     * @param studentNumber
     * @param doneNumber
     * @return
     */
    public static List<Map<String, Object>> MakeResult1(int studentNumber, int doneNumber) {
        Map<String, Object> done = new HashMap<>();
        Map<String, Object> notdone = new HashMap<>();
        done.put("完成情况", "已完成人数");
        done.put("人数", doneNumber);
        notdone.put("完成情况", "未完成人数");
        notdone.put("人数", studentNumber - doneNumber > 0 ? studentNumber - doneNumber : 0);
        return Arrays.asList(done, notdone);
    }

    /**
     * 各题型最低最高平均分
     *
     * @param analysis
     * @return
     */
    public static List<Map<String, Object>> MakeResult2(Map<String, Object> analysis) {
        if (analysis==null) return Collections.emptyList();
        Map<String, Object> min = new HashMap<>();
        Map<String, Object> max = new HashMap<>();
        Map<String, Object> avg = new HashMap<>();
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
        return Arrays.asList(min, max, avg);
    }

    /***
     * 各考试分段人数
     * @param param
     * @return
     */
    public static List<Map<String, Object>> MakeResult3(Map<String, Object> param) {
        int mark = Integer.valueOf(param.get("mark").toString());
        List<Map<String, Object>> list = new LinkedList<>();
        int temp = mark % 20 == 0 ? mark / 20 : mark / 20 + 1;
        for (int i = 0, start = 0; i < temp; i++, start += 20) {
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
