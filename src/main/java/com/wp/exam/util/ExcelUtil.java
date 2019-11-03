package com.wp.exam.util;


import org.apache.commons.lang.enums.EnumUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

public class ExcelUtil {

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

    public static Map<String, String> check(File file) throws Exception {
        int snumber=0;
        int smark=0;
        int dnumber=0;
        int dmark=0;
        Map<String, String> map = new HashMap<String, String>();
     /*   try {
            inputStream = new FileInputStream(file);
            poifsFileSystem = new POIFSFileSystem(inputStream);
            workbook = new HSSFWorkbook(poifsFileSystem);
            HSSFSheet sSheet = workbook.getSheet("单选题");
            if (sSheet == null)
                throw new RuntimeException("文件格式错误!");
            //遍历行row
            for (int rowNum = 1; rowNum <= sSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = sSheet.getRow(rowNum);
                if (hssfRow == null) continue;
                //遍历列cell
                for (int rowCell = 0; rowCell < hssfRow.getLastCellNum(); rowCell++) {
                    HSSFCell hssfCell = hssfRow.getCell(rowCell);
                    if (rowCell == 0) {
                        //题号
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("题号为空!");
                        snumber++;
                    } else if (rowCell == 1) {
                        //分值
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("分值为空!");
                        smark+=hssfCell.getNumericCellValue();
                    } else if (rowCell == 2) {
                        //题目描述
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("题目描述为空!");
                    } else if (rowCell == 3) {
                        //A选项
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("A选项为空!");
                    } else if (rowCell == 4) {
                        //B选项
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("B选项为空!");
                    } else if (rowCell == 5) {
                        //C选项
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("C选项为空!");
                    } else if (rowCell == 6) {
                        //D选项
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("D选项为空1");
                    } else if (rowCell == 7) {
                        //正确答案
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案为空!");
                        if (!strings.contains(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案格式不正确");
                    } else {
                        throw new RuntimeException("超出长度!");
                    }
                }
            }
            HSSFSheet dSheet = workbook.getSheet("多选题");
            if (dSheet == null)
                throw new RuntimeException("文件格式错误!");
            //遍历行row
            for (int rowNum2 = 1; rowNum2 <= dSheet.getLastRowNum(); rowNum2++) {
                HSSFRow hssfRow2 = dSheet.getRow(rowNum2);
                if (hssfRow2 == null) continue;
                //遍历列cell
                for (int rowCell = 0; rowCell < hssfRow2.getLastCellNum(); rowCell++) {
                    HSSFCell hssfCell = hssfRow2.getCell(rowCell);
                    if (rowCell == 0) {
                        //题号
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("题号为空!");
                        dnumber++;
                    } else if (rowCell == 1) {
                        //分值
                        if (hssfCell.getNumericCellValue() == 0)
                            throw new RuntimeException("分值为空!");
                        dmark+=hssfCell.getNumericCellValue();
                    } else if (rowCell == 2) {
                        //题目描述
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("题目描述为空!");
                    } else if (rowCell == 3) {
                        //A选项
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("A选项为空!");
                    } else if (rowCell == 4) {
                        //B选项
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("B选项为空!");
                    } else if (rowCell == 5) {
                        //C选项
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("C选项为空!");
                    } else if (rowCell == 6) {
                        //D选项
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("D选项为空1");
                    } else if (rowCell == 7) {
                        //正确答案
                        if (isBlank(hssfCell.getStringCellValue()))
                            throw new RuntimeException("正确答案为空!");
                        String[] split = hssfCell.getStringCellValue().split(",");
                        if (split.length <= 1)
                            throw new RuntimeException("正确答案格式不正确");
                        for (String s : split) {
                            if (!strings.contains(s))
                                throw new RuntimeException("正确答案格式不正确");
                        }
                    } else {
                        throw new RuntimeException("超出长度!");
                    }
                }
            }
            map.put("state", "succeed");
            System.out.println("单选题有"+snumber+"道");
            System.out.println("单选题有"+smark+"分");
            System.out.println("多选题有"+dnumber+"道");
            System.out.println("多选题有"+dmark+"分");
            System.out.println("总共有"+(snumber+dnumber)+"道");
            System.out.println("总分:"+(smark+dmark)+"分");
        } catch (Exception e) {
            map.put("state", "fail");
            map.put("reason", e.getMessage());
            e.printStackTrace();
        } finally {
            poifsFileSystem.close();
            inputStream.close();
            workbook.close();
        }*/
        return map;
    }


  public static boolean isBlank(String str){
       return (str == null || str.trim().length() == 0);
   }



   /* private static List<Singleselect> singleselectList = null;
    private static Singleselect singleselect = null;
    private static HashSet<Character> characterHashMap = new HashSet<>();

    static {
        characterHashMap.add('A');
        characterHashMap.add('B');
        characterHashMap.add('C');
        characterHashMap.add('D');
    }

    public static List<Singleselect> createPaper(File file,Integer paperid) throws Exception {
        FileInputStream inputStream = null;
        POIFSFileSystem poifsFileSystem;
        try {
            inputStream = new FileInputStream(file);
            poifsFileSystem = new POIFSFileSystem(inputStream);
        } finally {
            inputStream.close();
        }
        HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
        HSSFSheet singleselectSheet = workbook.getSheet("单选题");
        singleselectList=new LinkedList<>();
        if (singleselectSheet == null)
            throw new RuntimeException("xls文件格式错误!");
        //遍历行row
        for (int rowNum = 1; rowNum <= singleselectSheet.getLastRowNum(); rowNum++) {
            singleselect = new Singleselect();
            singleselect.setPaperid(paperid);
            HSSFRow hssfRow = singleselectSheet.getRow(rowNum);
            if (hssfRow == null) continue;
            //遍历列cell
            for (int rowCell = 0; rowCell <= hssfRow.getLastCellNum(); rowCell++) {
                HSSFCell hssfCell = hssfRow.getCell(rowCell);
                if (hssfCell == null) continue;
                //如果是第一列,获取数字
                if (rowCell == 0) {
                    singleselect.setNumber(hssfCell.getNumericCellValue());
                } else if (rowCell == 1) {
                    singleselect.setTdescribe(hssfCell.getStringCellValue());
                } else if (rowCell == 2) {
                    singleselect.setCa(hssfCell.getStringCellValue());
                } else if (rowCell == 3) {
                    singleselect.setCb(hssfCell.getStringCellValue());
                } else if (rowCell == 4) {
                    singleselect.setCc(hssfCell.getStringCellValue());
                } else if (rowCell == 5) {
                    singleselect.setCd(hssfCell.getStringCellValue());
                } else if (rowCell == 6) {
                    singleselect.setAnswer(hssfCell.getStringCellValue());
                } else if (rowCell == 7) {
                    if (characterHashMap.contains(hssfCell.getStringCellValue().charAt(0))) {
                        singleselect.setAnswer(hssfCell.getStringCellValue());
                    } else throw new RuntimeException("xls文件格式错误!");
                }

            }
            singleselectList.add(singleselect);
        }
        poifsFileSystem.close();
        return singleselectList;
    }*/
}
