package ru.rsatu;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.List;

public class ExcelHandler {


    public void saveWbToFile(Workbook wb , String fileName) {
        try (OutputStream fileOut = new FileOutputStream(fileName + ".xls")) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Workbook createNewWb(){
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("sheet 1");
        Sheet sheet2 = wb.createSheet("sheet 2");
        Sheet sheet3 = wb.createSheet("sheet 3");

        return wb;
    }


    public Workbook editWb(Workbook wb){
        return wb;
    }

    public Workbook addForwardTransform(Workbook wb, List<double[]> data){

        Sheet curSheet = wb.getSheetAt(0);


        int index = 0;
        for (double[] mass : data) {
            Row row = curSheet.createRow(index);
            for (int j = 0; j<mass.length; j++){
                Cell cell = row.createCell(j, CellType.NUMERIC);

                cell.setCellValue(mass[j]);
            }

            index++;

        }

        return wb;
    }


    public Workbook addList(Workbook wb, List<double[]> data, int sheetNumb){

        Sheet curSheet = wb.getSheetAt(sheetNumb);


        int index = 0;
        for (double[] mass : data) {
            Row row = curSheet.createRow(index);
            for (int j = 0; j<mass.length; j++){
                Cell cell = row.createCell(j, CellType.NUMERIC);

                cell.setCellValue(mass[j]);
            }

            index++;

        }

        return wb;
    }


}
