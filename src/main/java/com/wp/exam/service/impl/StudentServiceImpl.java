package com.wp.exam.service.impl;

import com.wp.exam.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Map<String, Object> addStudent(Map<String, Object> param) throws Exception {
        log.info("addStudent start ...{}", param);
        log.info("addStudent end ...");
        return null;
    }

    @Override
    public Map<String, Object> deleteStudent(Map<String, Object> param) throws Exception {
        log.info("deleteStudent start ...{}", param);
        log.info("deleteStudent end ...");
        return null;
    }

    @Override
    public Map<String, Object> updateStudent(Map<String, Object> param) throws Exception {
        log.info("updateStudent start ...{}", param);
        log.info("updateStudent end ...");

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
