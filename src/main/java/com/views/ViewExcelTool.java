package com.views;

import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.View;
import com.views.excel.ViewXlsComponent;
import com.views.excel.ViewXlsxComponent;

public class ViewExcelTool {

	/**
	 * 处理excel数据大于60000条部分数据无法写入xls文件问题
	 * @param Model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static View GetExcelView(Map<String,Object> Model){
		int MaxCount = 0;//最大条数
		//计算单表最大条数
		List<Map<String, Object>> Sheets = (List<Map<String, Object>>) Model.get("Sheets");
		for(Map<String, Object> SheetMessage :Sheets){
			List<String[]> SheetData = (List<String[]>) SheetMessage.get("SheetData");
			int SheetDataMaxCount = SheetData.size();
			if(SheetDataMaxCount>MaxCount){
				MaxCount = SheetDataMaxCount;
			}
		}
		if(MaxCount>=60000){
			return new ViewXlsxComponent();
		}else{
			return new ViewXlsComponent();
		}
	}
}
