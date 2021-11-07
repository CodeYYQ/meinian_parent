package com.yyq.meinian;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @title: POITest
 * @Author yyq
 * @Date: 2021/11/1 21:09
 * @Version 1.0
 */
public class POITest {

    public void readExcelFileOne() throws IOException {
        //获取工作簿对象,读取文件
        XSSFWorkbook xssf = new XSSFWorkbook("C:\\Users\\卿\\Desktop\\学生信息.xlsx");

        //获取工作表对象(sheet)
        XSSFSheet sheet = xssf.getSheetAt(0);
        //获取组成工作表的行(row)
        for (Row row : sheet) {
            //获取组成行的单元格(cell)
            for (Cell cell : row) {
                System.out.print(cell.getStringCellValue()+"\t\t");
            }
            System.out.println();
        }
        //关闭工作簿
        xssf.close();

    }


    public void readExcelFileTwo() throws IOException {
        //获取工作簿对象,读取文件
        XSSFWorkbook xssf = new XSSFWorkbook("C:\\Users\\卿\\Desktop\\学生信息.xlsx");

        //获取工作表对象(sheet)
        XSSFSheet sheet = xssf.getSheetAt(0);

        //获取最后一行行数（从0开始），真实列-1
        int lastRowNum = sheet.getLastRowNum();

        //通过最后一行行数获取每一行
        for (int i = 0; i <= lastRowNum; i++) {
            //通过行数获取每一行
            XSSFRow row = sheet.getRow(i);
            //获取每一行最后一列列数（从0开始）,真实列
            short lastCellNum = row.getLastCellNum();
            //通过列数循环每一列
            for (int j = 0; j < lastCellNum; j++) {
                //通过列数获取某一列
                XSSFCell cell = row.getCell(j);
                System.out.print(cell.getStringCellValue()+"\t\t");
            }
            System.out.println();

        }

    }

    public void WriteExcelFile() throws IOException {
        //创建一个工作簿对象
        XSSFWorkbook xssf = new XSSFWorkbook();
        //创建一个工作行对象
        XSSFSheet sheet = xssf.createSheet();
        //创建第一行
        XSSFRow row0 = sheet.createRow(0);
        //创建一行的单元格并赋值
        row0.createCell(0).setCellValue("姓名");
        row0.createCell(1).setCellValue("年龄");
        row0.createCell(2).setCellValue("性别");

        //创建第二行
        XSSFRow row1 = sheet.createRow(1);
        //创建一行的单元格并赋值
        row1.createCell(0).setCellValue("yyq");
        row1.createCell(1).setCellValue("21");
        row1.createCell(2).setCellValue("男");

        //创建第三行
        XSSFRow row2 = sheet.createRow(2);
        //创建一行的单元格并赋值
        row2.createCell(0).setCellValue("IU");
        row2.createCell(1).setCellValue("30");
        row2.createCell(2).setCellValue("女");

        //将工作簿写出到文件中
        xssf.write(new FileOutputStream("C:\\Users\\卿\\Desktop\\studentInfo.xlsx"));
        xssf.close();
    }

}
