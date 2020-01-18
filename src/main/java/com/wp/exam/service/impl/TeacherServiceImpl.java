package com.wp.exam.service.impl;

import com.wp.exam.mapper.LoginMapper;
import com.wp.exam.mapper.TeacherMapper;
import com.wp.exam.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Map<String, Object> addTeacher(Map<String, Object> param) throws Exception {
        log.info("addTeacher start ...{}", param);
        if (loginMapper.check(param) > 0)
            return ServiceUtil.makeResult(null, "用户名或者工号已经被注册!");
        log.info("addTeacher addTeacher ...");
        teacherMapper.addTeacher(param);
        log.info("addTeacher end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> deleteTeacher(Map<String, Object> param) throws Exception {
        log.info("deleteTeacher start ...{}", param);
        teacherMapper.deleteTeacher(param);
        log.info("deleteTeacher end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> updateTeacher(Map<String, Object> param) throws Exception {
        log.info("updateTeacher start ...{}", param);
        teacherMapper.updateTeacher(param);
        log.info("updateTeacher end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> findbyConditions(Map<String, Object> param) throws Exception {
        log.info("findbyConditions start ...{}", param);
        //添加分页配置
        ServiceUtil.addStartAndEnd(param);
        //业务逻辑
        List<Map<String, Object>> dataList = teacherMapper.findbyConditions(param);
        int total = teacherMapper.findbyConditionsCount(param);
        //打印参数
        log.info("findArea" + System.getProperty("line.separator") + "dataList={}" + System.getProperty("line.separator") + "total={}", dataList, total);
        //生成结果
        Map<String, Object> result = ServiceUtil.addTotalAndDatalist(dataList, total);
        log.info("findbyConditions end ...");
        return result;
    }

    @Override
    public Map<String, Object> findAll(Map<String, Object> param) throws Exception {
        log.info("findAll start ...{}", param);
        //业务逻辑
        List<Map<String, Object>> dataList = teacherMapper.findbyConditions(param);
        //打印参数
        log.info("findArea" + System.getProperty("line.separator") + "dataList={}", dataList);
        //生成结果
        Map<String, Object> result = ServiceUtil.addTotalAndDatalist(dataList, -1);
        log.info("findAll end ...");
        return result;
    }

}
