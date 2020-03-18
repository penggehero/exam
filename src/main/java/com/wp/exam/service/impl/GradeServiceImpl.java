package com.wp.exam.service.impl;

import com.wp.exam.mapper.GradeMapper;
import com.wp.exam.service.GradeService;
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
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    private static final Logger log = LoggerFactory.getLogger(GradeServiceImpl.class);

    @Override
    public Map<String, Object> addGrade(Map<String, Object> param) throws Exception {
        log.info("addGrade start ...{}", param);
        log.info("addGrade end ...");
        return null;
    }

    @Override
    public Map<String, Object> deleteGrade(Map<String, Object> param) throws Exception {
        log.info("deleteGrade start ...{}", param);
        gradeMapper.deleteGrade(param);
        log.info("deleteGrade end ...");
        return ServiceUtil.makeResult(null,null);
    }

    @Override
    public Map<String, Object> updateGrade(Map<String, Object> param) throws Exception {
        log.info("updateGrade start ...{}", param);
        gradeMapper.updateGrade(param);
        log.info("updateGrade end ...");
        return ServiceUtil.makeResult(null,null);
    }

    @Override
    public Map<String, Object> search(Map<String, Object> param) throws Exception {
        log.info(" Grade search start ...{}", param);
        //添加分页配置
        ServiceUtil.addStartAndEnd(param);
        //业务逻辑
        List<Map<String, Object>> dataList = gradeMapper.search(param);
        int total = gradeMapper.searchCount(param);
        //打印参数
        log.info("Grade search" + System.getProperty("line.separator") + "dataList={}" + System.getProperty("line.separator") + "total={}", dataList, total);
        //生成结果
        Map<String, Object> result = ServiceUtil.addTotalAndDatalist(dataList, total);
        log.info("Grade search end ...");
        return result;
    }

    @Override
    public Map<String, Object> findAll(Map<String, Object> param) throws Exception {
        log.info("findAll start ...{}", param);
        log.info("findAll end ...");
        return null;
    }
}
