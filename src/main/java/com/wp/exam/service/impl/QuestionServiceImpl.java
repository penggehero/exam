package com.wp.exam.service.impl;

import com.wp.exam.mapper.PaperMapper;
import com.wp.exam.mapper.QuestionMapper;
import com.wp.exam.service.QuestionService;
import com.wp.exam.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private PaperMapper paperMapper;

    private static final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Override
    public Map<String, Object> search(Map<String, Object> param) throws Exception {
        log.info("search start ...{}", param);
        //添加分页配置
        ServiceUtil.addStartAndEnd(param);
        //业务逻辑
        List<Map<String, Object>> dataList = questionMapper.search(param);
        //前端数据转换
        long start = System.currentTimeMillis();
        for (int i = 0; i < dataList.size(); i++) {
            String string = dataList.get(i).get("flag").toString();
            if (string.equals("0")) {
                dataList.get(i).put("flag", "单选题");

            } else if (string.equals("1")) {
                dataList.get(i).put("flag", "多选题");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时间为;  " + (end - start));
        int total = questionMapper.searchCount(param);
        //打印参数
        log.info("Question search" + System.getProperty("line.separator") + "dataList={}" + System.getProperty("line.separator") + "total={}", dataList, total);
        //生成结果
        Map<String, Object> result = ServiceUtil.addTotalAndDatalist(dataList, total);
        log.info("search end ...");
        return result;
    }

    @Override
    public Map<String, Object> delete(Map<String, Object> param) throws Exception {
        log.info("delete start ...{}", param);
        paperMapper.updateDelQuestion(param);
        questionMapper.deleteQuestion(param);
        log.info("delete end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> add(Map<String, Object> param) throws Exception {
        log.info("QuestionServiceImpl add start ...{}", param);
        if (questionMapper.getQuestionByIdAndNum(param) > 0)
            return ServiceUtil.makeResult(null, "题目编号已存在!");
        questionMapper.insertQuestion(param);
        paperMapper.updateAddQuestion(param);
        log.info("QuestionServiceImpl add end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> update(Map<String, Object> param) throws Exception {
        log.info("QuestionServiceImpl update start ...{}", param);
        if (questionMapper.getQuestionByIdAndNum(param) > 0)
            return ServiceUtil.makeResult(null, "题目编号已存在!");
        questionMapper.updateQuestion(param);
        paperMapper.updateQuestion(param);
        log.info("QuestionServiceImpl update end ...");
        return ServiceUtil.makeResult(null, null);
    }
}
