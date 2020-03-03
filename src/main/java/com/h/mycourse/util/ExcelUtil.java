package com.h.mycourse.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {
    //总行数
    private static int totalRows = 0;
    //总条数
    private static int totalColumns = 0;

    public static Map<String, Double> getExcelInfo(MultipartFile Mfile){
        String fileName = Mfile.getOriginalFilename();
        File path = new File("E:\\receiveFiles\\temp");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!path.exists()) path.mkdirs();
        //新建一个文件
        File convertFile = new File("E:\\receiveFiles\\temp\\" + new Date().getTime() + ".xlsx");
        //将上传的文件写入新建的文件中
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
            fileOutputStream.write(Mfile.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Double> info = new HashMap<>();
        //初始化输入流
        InputStream is = null;
        try{
            //根据文件名判断文件是2003版本还是2007版本
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003 = false;
            }
            //根据新建的文件实例化输入流
            is = new FileInputStream(convertFile);
            //根据excel里面的内容读取客户信息
            info = getExcelInfo(is, isExcel2003);
            convertFile.delete();
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return info;
    }

    private static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    private static Map<String, Double> getExcelInfo(InputStream is,boolean isExcel2003){
        Map<String, Double> info=null;
        try{
            //根据版本选择创建Workbook的方式
            Workbook wb;
            //当excel是2003时
            if(isExcel2003){
                wb = new HSSFWorkbook(is);
            }
            else{//当excel是2007时
                wb = new XSSFWorkbook(is);
            }
            info=readExcelValue(wb);
        }
        catch (IOException e)  {
            e.printStackTrace();
        }
        return info;
    }

    private static Map<String, Double> readExcelValue(Workbook wb){
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);

        //得到Excel的行数
        totalRows=sheet.getPhysicalNumberOfRows();

        //得到Excel的列数(前提是有行数)
        if(totalRows>=1 && sheet.getRow(0) != null){
            totalColumns =sheet.getRow(0).getPhysicalNumberOfCells();
        }

        Map<String, Double> info = new HashMap<>();
        for(int r=0;r<totalRows;r++){
            Row row = sheet.getRow(r);
            if (row == null) continue;
            String number = "";
            double grade = 0;
            //循环Excel的列
            for(int c = 0; c <totalColumns; c++){

                Cell cell = row.getCell(c);
                if (null != cell){
                    if(c==0){
                        number=cell.getStringCellValue();
                    }else if(c==1){
                        grade=cell.getNumericCellValue();
                    }
                }
            }
            info.put(number,grade);
        }
        return info;
    }
}
