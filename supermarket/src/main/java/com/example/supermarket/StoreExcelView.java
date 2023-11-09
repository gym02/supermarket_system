package com.example.supermarket;



import com.example.supermarket.entity.vo.StoresVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

//excelView
@Component
public class StoreExcelView extends AbstractXlsxView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Sheet sheet = workbook.createSheet("employee info");
		//表头
		Row row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("货物名称");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("货物数量");
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("货物单位");
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("存货仓库");
		Cell cell4 = row.createCell(4);
		cell4.setCellValue("仓位");


		//取出map中的数据
		List<StoresVO> stores = (List<StoresVO>) model.get("stores");
		//遍历
		for (int i = 0; i < stores.size(); i++) {
			StoresVO e = stores.get(i);
			//为每一个对象创建一个行
			Row row1 = sheet.createRow(i + 1);
			Cell cell20 = row1.createCell(0);
			cell20.setCellValue(e.getCargo().getCargoName());
			Cell cell21 = row1.createCell(1);
			cell21.setCellValue(e.getCgCount());
			Cell cell12 = row1.createCell(2);
			cell12.setCellValue(e.getCgUnit());
			Cell cell13 = row1.createCell(3);
			cell13.setCellValue(e.getPwareHouse().getWhName());
			Cell cell14 = row1.createCell(4);
			cell14.setCellValue(e.getWareHouse().getWhName());





		}
	}
}
