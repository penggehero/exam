package com.wp.exam.service.impl;

import com.wp.exam.entity.Paper;
import com.wp.exam.entity.Question;
import com.wp.exam.mapper.PaperMapper;
import com.wp.exam.mapper.QuestionMapper;
import com.wp.exam.service.PaperService;
import com.wp.exam.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {

    private static FileInputStream inputStream = null;
    private static POIFSFileSystem poifsFileSystem = null;
    private static HSSFWorkbook workbook = null;
    private static HashSet<String> strings = new HashSet<>();
    static {
        strings.add("A");
        strings.add("C");
        strings.add("D");
        strings.add("B");
    }

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Transactional
    @Override
    public String addPaper(File file, String name, Integer time,String teacherName) throws Exception {
        //插入试卷
        Paper paper=new Paper();
        paper.setName(name);
        paper.setTime(time);
        paper.setTeacher(teacherName);
        paperMapper.addPaper(paper);
        Integer id = paper.getId();
        //插入试题
        int snumber=0;
        int smark=0;
        int dnumber=0;
        int dmark=0;
        try {
            inputStream = new FileInputStream(file);
            poifsFileSystem = new POIFSFileSystem(inputStream);
            workbook = new HSSFWorkbook(poifsFileSystem);
            HSSFSheet sSheet = workbook.getSheet("单选题");
            int flag=0;
            if (sSheet == null)
                throw new RuntimeException("文件格式错误!");
            //遍历行row
            for (int rowNum = 1; rowNum <= sSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = sSheet.getRow(rowNum);
                if (hssfRow == null) continue;
                Question question=new Question();
                //设置id和单选标志
                question.setP_id(id);
                question.setFlag(0);
                //遍历列cell
                for (int rowCell = 0; rowCell < hssfRow.getLastCellNum(); rowCell++) {
                    HSSFCell hssfCell = hssfRow.getCell(rowCell);
                    if (rowCell == 0) {
                        //题号
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("题号为空!");
                        question.setNumber((int)hssfCell.getNumericCellValue());
                        snumber++;
                    } else if (rowCell == 1) {
                        //分值
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("分值为空!");
                        question.setMark((int)hssfCell.getNumericCellValue());
                        smark+=hssfCell.getNumericCellValue();
                    } else if (rowCell == 2) {
                        //题目描述
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("题目描述为空!");
                        question.setName(hssfCell.getStringCellValue());
                    } else if (rowCell == 3) {
                        //A选项
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("A选项为空!");
                        question.setQ_A(hssfCell.getStringCellValue());
                    } else if (rowCell == 4) {
                        //B选项
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("B选项为空!");
                        question.setQ_B(hssfCell.getStringCellValue());
                    } else if (rowCell == 5) {
                        //C选项
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("C选项为空!");
                        question.setQ_C(hssfCell.getStringCellValue());
                    } else if (rowCell == 6) {
                        //D选项
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("D选项为空1");
                        question.setQ_D(hssfCell.getStringCellValue());
                    } else if (rowCell == 7) {
                        //正确答案
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案为空!");
                        if (!strings.contains(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案格式不正确");
                        question.setAnswer(hssfCell.getStringCellValue());
                    } else {
                        throw new RuntimeException("超出长度!");
                    }
                }
                //插入试题
                questionMapper.addQuestion(question);
            }
            HSSFSheet dSheet = workbook.getSheet("多选题");
            if (dSheet == null)
                throw new RuntimeException("文件格式错误!");
            //遍历行row
            for (int rowNum2 = 1; rowNum2 <= dSheet.getLastRowNum(); rowNum2++) {
                HSSFRow hssfRow2 = dSheet.getRow(rowNum2);
                if (hssfRow2 == null) continue;
                //遍历列cell
                Question dquestion=new Question();
                //设置id和单选标志
                dquestion.setP_id(id);
                dquestion.setFlag(1);
                for (int rowCell = 0; rowCell < hssfRow2.getLastCellNum(); rowCell++) {
                    HSSFCell hssfCell = hssfRow2.getCell(rowCell);
                    if (rowCell == 0) {
                        //题号
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("题号为空!");
                        dquestion.setNumber((int)hssfCell.getNumericCellValue());
                        dnumber++;
                    } else if (rowCell == 1) {
                        //分值
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("分值为空!");
                        dquestion.setMark((int)hssfCell.getNumericCellValue());
                        dmark+=hssfCell.getNumericCellValue();
                    } else if (rowCell == 2) {
                        //题目描述
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("题目描述为空!");
                        dquestion.setName(hssfCell.getStringCellValue());
                    } else if (rowCell == 3) {
                        //A选项
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("A选项为空!");
                        dquestion.setQ_A(hssfCell.getStringCellValue());
                    } else if (rowCell == 4) {
                        //B选项
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("B选项为空!");
                        dquestion.setQ_B(hssfCell.getStringCellValue());
                    } else if (rowCell == 5) {
                        //C选项
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("C选项为空!");
                        dquestion.setQ_C(hssfCell.getStringCellValue());
                    } else if (rowCell == 6) {
                        //D选项
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("D选项为空1");
                        dquestion.setQ_D(hssfCell.getStringCellValue());
                    } else if (rowCell == 7) {
                        //正确答案
                        if (ExcelUtil.isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案为空!");
                        String[] split = hssfCell.getStringCellValue().split(",");
                        if (split.length <= 1)
                            throw new RuntimeException("正确答案格式不正确");
                        for (String s : split) {
                            if (!strings.contains(s))
                                throw new RuntimeException("正确答案格式不正确");
                        }
                        dquestion.setAnswer(hssfCell.getStringCellValue());

                    } else {
                        throw new RuntimeException("超出长度!");
                    }
                }
                //插入试题
                questionMapper.addQuestion(dquestion);
            }
            System.out.println("单选题有"+snumber+"道");
            System.out.println("单选题有"+smark+"分");
            System.out.println("多选题有"+dnumber+"道");
            System.out.println("多选题有"+dmark+"分");
            System.out.println("总共有"+(snumber+dnumber)+"道");
            System.out.println("总分:"+(smark+dmark)+"分");
            paper.setMark((smark+dmark));
            paper.setNumber((snumber+dnumber));
            paperMapper.updatePaper(paper);
            return "succeed";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            poifsFileSystem.close();
            inputStream.close();
            workbook.close();
        }
    }
}
