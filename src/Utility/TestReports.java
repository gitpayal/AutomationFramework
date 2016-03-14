/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;
//import com.eviware.soapui.model.testsuite.TestRunner.Status; 
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.json.JSONArray;
/**
 *
 * @author payal.verma
 */
public class TestReports {
     
     
 public void setHeaderCellStyle(org.apache.poi.hssf.usermodel.HSSFSheet sheet,org.apache.poi.ss.usermodel.Cell cell,org.apache.poi.hssf.usermodel.HSSFWorkbook wb) {
    CellStyle s = null;
    s = sheet.getWorkbook().createCellStyle();
    cell.setCellStyle(s);
    
    HSSFFont font = wb.createFont();
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    s.setFont(font);
    s.setAlignment(CellStyle.ALIGN_GENERAL);
    s.setBorderBottom(CellStyle.BORDER_THIN);
    s.setBorderLeft(CellStyle.BORDER_THIN);
    s.setBorderRight(CellStyle.BORDER_THIN);
    s.setBorderTop(CellStyle.BORDER_THIN);
    s.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    s.setAlignment(CellStyle.ALIGN_LEFT);
    s.setFont(font);

}
    
 public void setCellStyle(org.apache.poi.hssf.usermodel.HSSFSheet sheet,org.apache.poi.ss.usermodel.Cell cell,org.apache.poi.hssf.usermodel.HSSFWorkbook wb)
{
   CellStyle s = null;
   s = sheet.getWorkbook().createCellStyle();
   cell.setCellStyle(s);
   for (int i=0; i<10; i++){
   sheet.autoSizeColumn(i);
}

  
}
 
 public void TestReports() {  
     FileOutputStream out = null;
     Date myDate = new Date();
     SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
     String strDate = sm.format(myDate);
     HSSFWorkbook workbook = new HSSFWorkbook();
     HSSFSheet sheet = workbook.createSheet("Test Result");
     Row rowHeader1 = sheet.createRow((short) 0);
     rowHeader1.createCell(0).setCellValue("Test Step Id");
     Cell cell1 = rowHeader1.getCell((short)0);
     setHeaderCellStyle(sheet,cell1,workbook);
     rowHeader1.createCell(1).setCellValue("Test Description");
     Cell cell2 = rowHeader1.getCell((short)1);
     setHeaderCellStyle(sheet,cell2,workbook);
     rowHeader1.createCell(2).setCellValue("Expected Result");
     Cell cell3 = rowHeader1.getCell((short)2);
     setHeaderCellStyle(sheet,cell3,workbook);
     rowHeader1.createCell(3).setCellValue("Actual Result");
     Cell cell4 = rowHeader1.getCell((short)3);
     setHeaderCellStyle(sheet,cell4,workbook);
     rowHeader1.createCell(4).setCellValue("Error Details");
     Cell cell5 = rowHeader1.getCell((short)4);
     setHeaderCellStyle(sheet,cell5,workbook);
     Row rowHeader = sheet.createRow((short) 1);
     Cell cellHeader1 = rowHeader.createCell((short) 0);
     sheet.addMergedRegion(new CellRangeAddress(1,3,0,5));
     cellHeader1.setCellValue("Validate Disclaimer Acceptance - M1 Sign Up Page" +"  " + " Test Date : " + myDate);
     setHeaderCellStyle(sheet,cellHeader1,workbook);
     Row rowHeader2 = sheet.createRow((short) 7);
     Cell cellHeader2 = rowHeader2.createCell((short) 0);
     sheet.addMergedRegion(new CellRangeAddress(7,9,0,5));
     cellHeader2.setCellValue("Validate User Account - M1 Sign Up Page" +"  " + " Test Date : " + myDate);
     setHeaderCellStyle(sheet,cellHeader2,workbook);
     Row rowHeader3 = sheet.createRow((short) 16);
     Cell cellHeader3= rowHeader3.createCell((short) 0);
     sheet.addMergedRegion(new CellRangeAddress(16,18,0,5));
     cellHeader3.setCellValue("Validate Business Information - M1 Sign Up Page" +"  " + " Test Date : " + myDate);
     setHeaderCellStyle(sheet,cellHeader3,workbook);
     Row rowHeader4 = sheet.createRow((short) 34);
     Cell cellHeader4 = rowHeader4.createCell((short) 0);
     sheet.addMergedRegion(new CellRangeAddress(34,36,0,5));
     cellHeader4.setCellValue("Validate Merchant Account Information - M1 Sign Up Page" +"  " + " Test Date : " + myDate);
     setHeaderCellStyle(sheet,cellHeader4,workbook);
}


    
}
 
