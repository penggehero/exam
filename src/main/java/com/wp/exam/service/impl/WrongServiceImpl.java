package com.wp.exam.service.impl;

import com.wp.exam.mapper.WrongQuestionMapper;
import com.wp.exam.service.WrongService;
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
public class WrongServiceImpl implements WrongService {

    @Autowired
    private WrongQuestionMapper wrongQuestionMapper;

    private static final Logger log = LoggerFactory.getLogger(WrongServiceImpl.class);

    @Override
    public Map<String, Object> search(Map<String, Object> param) throws Exception {
        log.info("WrongServiceImpl search start ...{}", param);
        //添加分页配置
        ServiceUtil.addStartAndEnd(param);
        //业务逻辑
        List<Map<String, Object>> dataList = wrongQuestionMapper.search(param);
        int total = wrongQuestionMapper.searchCount(param);
        //前端数据转换
        for (int i = 0; i < dataList.size(); i++) {
            String string = dataList.get(i).get("flag").toString();
            if (string.equals("0"))
                dataList.get(i).put("flag", "单选题");
            else if (string.equals("1"))
                dataList.get(i).put("flag", "多选题");
            else if (string.equals("2")) {
                dataList.get(i).put("flag", "判断题");
                if (dataList.get(i).get("answer").toString().equals("0"))
                    dataList.get(i).put("answer", "错");
                else if (dataList.get(i).get("answer").toString().equals("1"))
                    dataList.get(i).put("answer", "对");

                if (dataList.get(i).get("wrong_answer").toString().equals("0"))
                    dataList.get(i).put("wrong_answer", "错");
                else if (dataList.get(i).get("wrong_answer").toString().equals("1"))
                    dataList.get(i).put("wrong_answer", "对");
            }
        }
        //打印参数
        log.info("WrongServiceImpl search" + System.getProperty("line.separator") + "dataList={}" + System.getProperty("line.separator") + "total={}", dataList, total);
        //生成结果
        Map<String, Object> result = ServiceUtil.addTotalAndDatalist(dataList, total);
        List<Map<String, Object>> papers = wrongQuestionMapper.findPaperID(param);
        for (Map<String, Object> map : papers) {
            map.putAll(wrongQuestionMapper.findPaperNameById(map));
        }
        result.put("papers", papers);
        log.info("WrongServiceImpl search end ...");
        return result;
    }
}
