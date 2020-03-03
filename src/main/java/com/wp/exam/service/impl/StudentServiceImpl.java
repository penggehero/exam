package com.wp.exam.service.impl;

import com.wp.exam.mapper.LoginMapper;
import com.wp.exam.mapper.StudentMapper;
import com.wp.exam.service.StudentService;
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
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public Map<String, Object> addStudent(Map<String, Object> param) throws Exception {
        log.info("addStudent start ...{}", param);
        if (loginMapper.check(param) > 0)
            return ServiceUtil.makeResult(null, "用户名或者工号已经被注册!");
        studentMapper.addStudent(param);
        log.info("addStudent end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> deleteStudent(Map<String, Object> param) throws Exception {
        log.info("deleteStudent start ...{}", param);
        studentMapper.deleteStudent(param);
        log.info("deleteStudent end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> updateStudent(Map<String, Object> param) throws Exception {
        log.info("updateStudent start ...{}", param);
        studentMapper.updateStudent(param);
        log.info("updateStudent end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> findbyConditions(Map<String, Object> param) throws Exception {
        log.info("findbyConditions start ...{}", param);
        //添加分页配置
        ServiceUtil.addStartAndEnd(param);
        //业务逻辑
        List<Map<String, Object>> dataList = studentMapper.findbyConditions(param);
        int total = studentMapper.findbyConditionsCount(param);
        //打印参数
        log.info("findbyConditions" + System.getProperty("line.separator") + "dataList={}" + System.getProperty("line.separator") + "total={}", dataList, total);
        //生成结果
        Map<String, Object> result = ServiceUtil.addTotalAndDatalist(dataList, total);
        log.info("findbyConditions end ...");
        return result;
    }

    @Override
    public Map<String, Object> findAll(Map<String, Object> param) throws Exception {
        log.info("findAll start ...{}", param);
        //业务逻辑
        List<Map<String, Object>> dataList = studentMapper.findbyConditions(param);
        //打印参数
        log.info("findArea" + System.getProperty("line.separator") + "dataList={}", dataList);
        //生成结果
        Map<String, Object> result = ServiceUtil.addTotalAndDatalist(dataList, -1);
        log.info("findAll end ...");
        return result;
    }
}
