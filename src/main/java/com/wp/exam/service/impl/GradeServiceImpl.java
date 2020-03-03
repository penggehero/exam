package com.wp.exam.service.impl;

import com.wp.exam.service.GradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {

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
        log.info("deleteGrade end ...");
        return null;
    }

    @Override
    public Map<String, Object> updateGrade(Map<String, Object> param) throws Exception {
        log.info("updateGrade start ...{}", param);
        log.info("updateGrade end ...");
        return null;
    }

    @Override
    public Map<String, Object> findbyConditions(Map<String, Object> param) throws Exception {
        log.info("findbyConditions start ...{}", param);
        log.info("findbyConditions end ...");
        return null;
    }

    @Override
    public Map<String, Object> findAll(Map<String, Object> param) throws Exception {
        log.info("findAll start ...{}", param);
        log.info("findAll end ...");
        return null;
    }
}
