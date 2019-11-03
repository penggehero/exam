package com.wp.exam;
import com.wp.exam.entity.Question;
import com.wp.exam.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) {
       List<Question> questionList=new ArrayList<>();
        Question question = new Question();
        question.setNumber(2);
        Question question1 = new Question();
        question1.setNumber(1);
        Question question2 = new Question();
        question2.setNumber(0);

        questionList.add(question);
        questionList.add(question1);
        questionList.add(question2);




/*        FileInputStream inputStream = null;
        POIFSFileSystem poifsFileSystem=null;
        HSSFWorkbook workbook=null;
        HashSet<String> strings=new HashSet<>();
        strings.add("A");
        strings.add("C");
        strings.add("D");
        strings.add("B");
        try {
            inputStream = new FileInputStream(new File("D:\\template.xls"));
            poifsFileSystem = new POIFSFileSystem(inputStream);
            workbook = new HSSFWorkbook(poifsFileSystem);
            HSSFSheet sSheet = workbook.getSheet("单选题");
            if (sSheet == null)
                throw new RuntimeException("文件格式错误!");
            //遍历行row
            for (int rowNum = 0; rowNum <= sSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = sSheet.getRow(rowNum);
                if (hssfRow == null) continue;
                if (rowNum==0){
                    for (int rowCell = 0; rowCell < hssfRow.getLastCellNum(); rowCell++) {
                        HSSFCell hssfCell = hssfRow.getCell(rowCell);
                        if (rowCell!=hssfRow.getLastCellNum()-1)
                        System.out.print(hssfCell.getStringCellValue()+"  ");
                        else
                            System.out.println(hssfCell.getStringCellValue()+"  ");
                    }
                    continue;
                }
                //遍历列cell
                for (int rowCell = 0; rowCell < hssfRow.getLastCellNum(); rowCell++) {
                    HSSFCell hssfCell = hssfRow.getCell(rowCell);
                    if (rowCell == 0) {
                        System.out.print("题号;"+hssfCell.getNumericCellValue());
                    } else if (rowCell == 1) {
                        System.out.print("  分值; "+hssfCell.getNumericCellValue());
                    } else if (rowCell == 2) {
                        System.out.print( "  题目描述: "+hssfCell.getStringCellValue());
                    } else if (rowCell == 3) {
                        System.out.print("  A选项 "+hssfCell.getStringCellValue());
                    } else if (rowCell == 4) {
                        System.out.print("  B选项 "+hssfCell.getStringCellValue());
                    } else if (rowCell == 5) {
                        System.out.print("  C选项 "+hssfCell.getStringCellValue());
                    } else if (rowCell == 6) {
                        System.out.print("  D选项 "+hssfCell.getStringCellValue());
                    } else if (rowCell == 7) {
                        System.out.println("  正确答案: "+hssfCell.getStringCellValue());

                    }
                }
            }
        } finally {
            poifsFileSystem.close();
            inputStream.close();
            workbook.close();
        }*/

/*

        //定义一个工作簿
        Workbook workbook=new HSSFWorkbook();
        //创建第一个的sheet页
        Sheet singleselect = workbook.createSheet("单选题");
        //创建第二个的sheet页
        Sheet multiselect=workbook.createSheet("多选题");
        //创建第一行
        Row row = singleselect.createRow(0);
        //创建改行的第一个单元格并赋值
        Cell cell = row.createCell(0);
        cell.setCellValue("哈哈");
        //创建改行的第二个单元格并赋值
        row.createCell(1).setCellValue("呵呵");
        Row row1 = singleselect.createRow(1);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("jack");
        FileOutputStream fileOutputStream=new FileOutputStream("d:\\我创建的excel.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("测试成功!");
        File file=new File("d:\\我创建的excel.xls");
*/


    }
}
