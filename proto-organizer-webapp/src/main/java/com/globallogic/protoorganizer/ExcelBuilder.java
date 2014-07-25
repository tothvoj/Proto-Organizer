package com.globallogic.protoorganizer;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.globallogic.protoorganizer.model.Device;

public class ExcelBuilder extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CreationHelper createHelper = workbook.getCreationHelper();
		// create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Košice devices");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("Device");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("Platform");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Imei/SN");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Status");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("Project");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("Owner");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("Last modified");
        header.getCell(6).setCellStyle(style);
                        
        header.createCell(7).setCellValue("Date");
        header.getCell(7).setCellStyle(style);
        
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "devices.xls" + "\"");
        
        List<Device> deviceList = (List<Device>) model.get("devicesList");
        
        
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
     // create data rows
        int rowCount = 1;
         
        for (Device device : deviceList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(device.getDevice());
            aRow.createCell(1).setCellValue(device.getPlatform());
            aRow.createCell(2).setCellValue(device.getImei());
            aRow.createCell(3).setCellValue(device.getStatus());
            aRow.createCell(4).setCellValue(device.getProject());
            aRow.createCell(5).setCellValue(device.getOwner());
            aRow.createCell(6).setCellValue(device.getLast_modified());
            if (device.getDate() != null){
            	aRow.createCell(7).setCellValue(device.getDate());
            	aRow.getCell(7).setCellStyle(dateCellStyle);
            }            
        }
		
	}

}
