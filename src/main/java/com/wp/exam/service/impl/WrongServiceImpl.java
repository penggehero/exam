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
        ServiceUtil.WebFormat(dataList);
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
