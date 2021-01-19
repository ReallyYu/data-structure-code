package com.really.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.activation.FileDataSource;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiany
 * Date: 2020/8/14
 */

public class ExcelUtils {


    public static HSSFWorkbook writeExcel(WriteTiltle writeTiltle, List<T> data) throws IllegalAccessException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");

        int n = writeTiltle.writeTitle(sheet);

        for (int i = 0; i < data.size(); i++) {
            HSSFRow row = sheet.createRow(n+i);
            Field[] fields = T.class.getDeclaredFields();

            //序号
            row.createCell(0).setCellValue(i+1);
            for (int j = 0; j < fields.length; j++) {
                System.out.println(fields[j]);
                fields[j].setAccessible(true);
                Object obj = fields[j].get(data.get(i));
//                if (obj instanceof Date) {
//                    row.createCell(i+1).setCellValue(1);
//                } else if (obj instanceof Double) {
//                    row.createCell(i+1).setCellValue((Double)obj);
//                } else if (obj instanceof Calendar) {
//                    row.createCell(i+1).setCellValue((Calendar)obj);
//                } else {
//                    row.createCell(i+1).setCellValue(obj.toString());
//                }
                row.createCell(i+1).setCellValue(obj.toString());
            }
        }
        return workbook;
    }

    public static HSSFWorkbook writeExcel(String[] titles, writeRow writeRow) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");

        HSSFRow first = sheet.createRow(0);


        for (int i=0; i< titles.length; i++) {
            first.createCell(i).setCellValue(titles[i]);
        }

        writeRow.writeRow(sheet);
        workbook.createSheet("sheet2");
        return workbook;
    }

    public interface WriteTiltle {

        int writeTitle(HSSFSheet sheet);
    }

    public interface writeRow {

        void writeRow(HSSFSheet sheet);
    }
}
