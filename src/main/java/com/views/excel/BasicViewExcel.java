package com.views.excel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 基础Excel视图逻辑处理
 * @author heyanzhu
 *
 */
public class BasicViewExcel implements IBasicViewExcel{

	/**
	 * 全局样式格式化
	 * @param Sheet
	 * @param Workbook
	 */
	@Override
	public void FormatSetting(Sheet Sheet, Workbook Workbook, CellStyle style) {
		Sheet.setDefaultColumnWidth(30);
        style = Workbook.createCellStyle();
        Font font = Workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        font.setBold(true);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
	}

	/**
	 * Response 处理
	 * @param response
	 * @param ExcelName
	 */
	@Override
	public void ResponseFormat(HttpServletResponse response, String ExcelName) {
		try {
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(ExcelName,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        response.setContentType("application/ms-excel; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) {
		String excelName = model.get("TableName").toString()+".xls";//获取文件名称
		ResponseFormat(response,excelName);//response处理
        List<Map<String,Object>> Sheets = (List<Map<String, Object>>) model.get("Sheets");
        for(Map<String,Object> SheetMessage : Sheets){
        	String SheetName = SheetMessage.get("SheetName").toString();//获取工作表名
        	Sheet Sheet = workbook.createSheet(SheetName);
        	CellStyle style = null;
            FormatSetting(Sheet,workbook,style);//全局样式格式化
            List<String[]> SheetData = (List<String[]>) SheetMessage.get("SheetData");
            int RowIndex = 0;//行标记
            for(String[] Line : SheetData){//遍历Excel数据
            	Row header = Sheet.createRow(RowIndex++);
            	for(String Value : Line){
            		int Col = 0;//列标记
            		if(RowIndex==1){//判断第一行写入全局定义的样式
            			header.getCell(Col).setCellStyle(style);
            		}
            		header.createCell(Col++).setCellValue(Value);
            	}
            }
        }
		
	}
	
}
