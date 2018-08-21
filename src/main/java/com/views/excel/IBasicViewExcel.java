package com.views.excel;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 基础Excel导出视图处理接口
 * @author heyanzhu
 *
 */
public interface IBasicViewExcel {
	
	void FormatSetting(Sheet Sheet,Workbook Workbook,CellStyle style);
	void ResponseFormat(HttpServletResponse response,String ExcelName);
	void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response);
}
