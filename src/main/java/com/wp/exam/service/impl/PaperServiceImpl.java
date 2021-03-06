package com.wp.exam.service.impl;

import com.wp.exam.mapper.GradeMapper;
import com.wp.exam.mapper.PaperMapper;
import com.wp.exam.mapper.QuestionMapper;
import com.wp.exam.mapper.WrongQuestionMapper;
import com.wp.exam.service.PaperService;
import com.wp.exam.util.ServiceUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@Service
@Transactional
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private WrongQuestionMapper wrongQuestionMapper;


    private static final Logger log = LoggerFactory.getLogger(PaperServiceImpl.class);

    private static FileInputStream inputStream = null;
    private static POIFSFileSystem poifsFileSystem = null;
    private static HSSFWorkbook workbook = null;
    private static HashSet<String> strings = new HashSet<>();
    private static HashSet<String> judgestrings = new HashSet<>();

    static {
        strings.add("A");
        strings.add("C");
        strings.add("D");
        strings.add("B");
        judgestrings.add("对");
        judgestrings.add("错");
    }

    @Override
    public Map<String, Object> importPaper(InputStream inputStream, Map<String, Object> param) throws Exception {
        log.info("importPaper start ...{}", param);

        //插入试题
        int snumber = 0;
        int smark = 0;
        int jnumber = 0;
        int jmark = 0;
        int dnumber = 0;
        int dmark = 0;
        paperMapper.importPaper(param);
        //试卷编号
        String paper_id = param.get("id").toString();
        try {
            poifsFileSystem = new POIFSFileSystem(inputStream);
            workbook = new HSSFWorkbook(poifsFileSystem);
            HSSFSheet sSheet = workbook.getSheet("单选题");
            if (sSheet == null)
                throw new RuntimeException("文件格式错误!");
            //遍历行row
            for (int rowNum = 1; rowNum <= sSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = sSheet.getRow(rowNum);
                if (hssfRow == null) continue;
                HashMap<String, Object> single = new HashMap<>();
                //设置id和单选标志
                single.put("paper_id", paper_id);
                single.put("flag", 0);
                //遍历列cell
                for (int rowCell = 0; rowCell < hssfRow.getLastCellNum(); rowCell++) {
                    HSSFCell hssfCell = hssfRow.getCell(rowCell);
                    if (rowCell == 0) {
                        //题号
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("题号为空!");
                        single.put("number", hssfCell.getNumericCellValue());
                        snumber++;
                    } else if (rowCell == 1) {
                        //分值
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("分值为空!");
                        single.put("mark", hssfCell.getNumericCellValue());
                        smark += hssfCell.getNumericCellValue();
                    } else if (rowCell == 2) {
                        //题目描述
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("题目描述为空!");
                        single.put("name", hssfCell.getStringCellValue());
                    } else if (rowCell == 3) {
                        //A选项
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("A选项为空!");
                        single.put("q_A", hssfCell.getStringCellValue());
                    } else if (rowCell == 4) {
                        //B选项
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("B选项为空!");
                        single.put("q_B", hssfCell.getStringCellValue());
                    } else if (rowCell == 5) {
                        //C选项
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("C选项为空!");
                        single.put("q_C", hssfCell.getStringCellValue());
                    } else if (rowCell == 6) {
                        //D选项
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("D选项为空1");
                        single.put("q_D", hssfCell.getStringCellValue());
                    } else if (rowCell == 7) {
                        //正确答案
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案为空!");
                        if (!strings.contains(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案格式不正确");
                        single.put("answer", hssfCell.getStringCellValue());
                    } else {
                        throw new RuntimeException("超出长度!");
                    }
                }
                //插入试题
                questionMapper.importQuestion(single);
            }

            HSSFSheet jSheet = workbook.getSheet("判断题");
            if (jSheet == null)
                throw new RuntimeException("文件格式错误!");

            //遍历行row
            for (int rowNum = 1; rowNum <= jSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = jSheet.getRow(rowNum);
                if (hssfRow == null) continue;
                HashMap<String, Object> judge = new HashMap<>();
                //设置id和单选标志
                judge.put("paper_id", paper_id);
                judge.put("flag", 2);
                //遍历列cell
                for (int rowCell = 0; rowCell < hssfRow.getLastCellNum(); rowCell++) {
                    HSSFCell hssfCell = hssfRow.getCell(rowCell);
                    if (rowCell == 0) {
                        //题号
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("题号为空!");
                        judge.put("number", hssfCell.getNumericCellValue());
                        jnumber++;
                    } else if (rowCell == 1) {
                        //分值
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("分值为空!");
                        judge.put("mark", hssfCell.getNumericCellValue());
                        jmark += hssfCell.getNumericCellValue();
                    } else if (rowCell == 2) {
                        //题目描述
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("题目描述为空!");
                        judge.put("name", hssfCell.getStringCellValue());
                    } else if (rowCell == 3) {
                        //正确答案
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案为空!");
                        if (!judgestrings.contains(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案格式不正确");
                        Integer answer=hssfCell.getStringCellValue().equals("对")?1:0;
                        judge.put("answer", answer);
                    } else {
                        throw new RuntimeException("超出长度!");
                    }
                }
                //插入试题
                questionMapper.importQuestion(judge);
            }


            HSSFSheet dSheet = workbook.getSheet("多选题");
            if (dSheet == null)
                throw new RuntimeException("文件格式错误!");
            //遍历行row
            for (int rowNum2 = 1; rowNum2 <= dSheet.getLastRowNum(); rowNum2++) {
                HSSFRow hssfRow2 = dSheet.getRow(rowNum2);
                if (hssfRow2 == null) continue;
                //遍历列cell
                HashMap<String, Object> dquestion = new HashMap<>();
                //设置id和单选标志
                dquestion.put("paper_id", paper_id);
                dquestion.put("flag", 1);
                for (int rowCell = 0; rowCell < hssfRow2.getLastCellNum(); rowCell++) {
                    HSSFCell hssfCell = hssfRow2.getCell(rowCell);
                    if (rowCell == 0) {
                        //题号
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("题号为空!");
                        dquestion.put("number", hssfCell.getNumericCellValue());
                        dnumber++;
                    } else if (rowCell == 1) {
                        //分值
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("分值为空!");
                        dquestion.put("mark", hssfCell.getNumericCellValue());
                        dmark += hssfCell.getNumericCellValue();
                    } else if (rowCell == 2) {
                        //题目描述
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("题目描述为空!");
                        dquestion.put("name", hssfCell.getStringCellValue());
                    } else if (rowCell == 3) {
                        //A选项
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("A选项为空!");
                        dquestion.put("q_A", hssfCell.getStringCellValue());
                    } else if (rowCell == 4) {
                        //B选项
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("B选项为空!");
                        dquestion.put("q_B", hssfCell.getStringCellValue());
                    } else if (rowCell == 5) {
                        //C选项
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("C选项为空!");
                        dquestion.put("q_C", hssfCell.getStringCellValue());
                    } else if (rowCell == 6) {
                        //D选项
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("D选项为空1");
                        dquestion.put("q_D", hssfCell.getStringCellValue());
                    } else if (rowCell == 7) {
                        //正确答案
                        if (StringUtils.isEmpty(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案为空!");
                        String[] split = hssfCell.getStringCellValue().split(",");
                        HashSet<String> stringHashSet = new HashSet<>();
                        if (split.length < 2)
                            throw new RuntimeException("正确答案格式不正确!");
                        for (String s : split) {
                            if (!strings.contains(s))
                                throw new RuntimeException("正确答案格式不正确");
                            if (!stringHashSet.contains(s))
                                stringHashSet.add(s);
                            else
                                throw new RuntimeException("正确答案格式不正确");

                        }
                        dquestion.put("answer", hssfCell.getStringCellValue());
                    } else {
                        throw new RuntimeException("超出长度!");
                    }
                }
                //插入试题
                questionMapper.importQuestion(dquestion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (poifsFileSystem != null)
                poifsFileSystem.close();
            if (inputStream != null)
                inputStream.close();
            if (workbook != null)
                workbook.close();
        }
        System.out.println("单选题有" + snumber + "道");
        System.out.println("单选题有" + smark + "分");
        System.out.println("判断题有" + jnumber + "道");
        System.out.println("判断题有" + jmark + "分");
        System.out.println("多选题有" + dnumber + "道");
        System.out.println("多选题有" + dmark + "分");
        System.out.println("总共有" + (snumber + dnumber + jnumber) + "道");
        System.out.println("总分:" + (smark + dmark + jmark) + "分");
        HashMap<String, Object> update = new HashMap<>();
        update.put("number", snumber + dnumber + jnumber);
        update.put("mark", smark + dmark + jmark);
        update.put("paper_id", paper_id);
        paperMapper.updatePaper(update);
        log.info("importPaper end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> search(Map<String, Object> param) throws Exception {
        log.info("search start ...{}", param);
        //添加分页配置
        ServiceUtil.addStartAndEnd(param);
        //业务逻辑
        List<Map<String, Object>> dataList = paperMapper.search(param);
        int total = paperMapper.searchCount(param);
        //打印参数
        log.info("search" + System.getProperty("line.separator") + "dataList={}" + System.getProperty("line.separator") + "total={}", dataList, total);
        //生成结果
        Map<String, Object> result = ServiceUtil.addTotalAndDatalist(dataList, total);
        log.info("search end ...");
        return result;
    }

    @Override
    public Map<String, Object> getQuestion(Map<String, Object> param) throws Exception {
        log.info("getQuestion start ...{}", param);
        List<Map<String, Object>> dataList = paperMapper.getQuestion(param);
        Map<String, Object> result = ServiceUtil.makeResult(dataList, null);
        result.putAll(paperMapper.getTimeAndName(param));
        log.info("getQuestion end ...");
        return result;
    }

    /**
     * 试卷提交,自动改卷
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> submit(Map<String, Object> param) throws Exception {
        log.info("submit start ...{}", param);
        int singleMark = 0;
        int dobleMark = 0;
        int judgeMark = 0;
        // 单选题
        List<String> singleList = (List<String>) param.get("single");
        // 判断题
        List<String> judgeList = (List<String>) param.get("judge");
        // 多选题
        List<String> doubleList = (List<String>) param.get("double");
        for (String s : singleList) {
            String[] split = s.split("\\|");
            Map<String, Object> question = questionMapper.getQuestion(split[0]);
            if (split[1].equals(question.get("answer"))) {
                singleMark += Integer.valueOf(question.get("mark").toString());
            } else {
                addParam(question, param, split);
                wrongQuestionMapper.addWrongQuestion(question);

            }
        }
        for (String s : judgeList) {
            String[] split = s.split("\\|");
            Map<String, Object> question = questionMapper.getQuestion(split[0]);
            if (split[1].equals(question.get("answer"))) {
                judgeMark += Integer.valueOf(question.get("mark").toString());
            } else {
                addParam(question, param, split);
                wrongQuestionMapper.addWrongQuestion(question);
            }
        }
        for (String s : doubleList) {
            String[] split = s.split("\\|");
            Map<String, Object> question = questionMapper.getQuestion(split[0]);
            String[] answers = question.get("answer").toString().split(",");
            if (isRight(split, answers)) {
                dobleMark += Integer.valueOf(question.get("mark").toString());
            } else {
                addParam(question, param, split);
                wrongQuestionMapper.addWrongQuestion(question);
            }
        }
        param.put("single_mark", singleMark);
        param.put("double_mark", dobleMark);
        param.put("judge_mark", judgeMark);
        param.put("mark", singleMark + dobleMark + judgeMark);
        gradeMapper.addGrade(param);
        log.info("submit end ...");
        return ServiceUtil.makeResult(null, null);
    }

    @Override
    public Map<String, Object> findByTeacher(Map<String, Object> param) throws Exception {
        log.info("findByTeacher start ...{}", param);
        //添加分页配置
        ServiceUtil.addStartAndEnd(param);
        //业务逻辑
        List<Map<String, Object>> dataList = paperMapper.findByTeacher(param);
        int total = paperMapper.findByTeacherCount(param);
        //打印参数
        log.info("findByTeacher" + System.getProperty("line.separator") + "dataList={}" + System.getProperty("line.separator") + "total={}", dataList, total);
        //生成结果
        Map<String, Object> result = ServiceUtil.addTotalAndDatalist(dataList, total);
        log.info("findByTeacher end ...");
        return result;
    }

    /**
     * 多选题答案是否正确
     *
     * @param split
     * @param answers
     * @return
     */
    public static boolean isRight(String[] split, String[] answers) {
        if (split.length != answers.length + 1) return false;
        int i = 0, j;
        for (; i < answers.length; i++) {
            for (j = 1; j < split.length; j++) {
                if (split[j].equals(answers[i]))
                    break;
            }
            if (j == split.length) return false;
        }
        return true;
    }

    public static void addParam(Map<String, Object> question, Map<String, Object> param, String[] str) {
        question.putAll(param);
        question.put("wrong_answer", str[1]);
    }

}
