package com.example.supermarket.util;

import com.example.supermarket.entity.vo.ProductVO;
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

@Component
public class ExcelProductView extends AbstractXlsxView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		Sheet sheet =workbook.createSheet("product info");
		//表头
		Row row =sheet.createRow(0);
		Cell cell0=row.createCell(0);
		cell0.setCellValue("商品编号");
		Cell cell1=row.createCell(1);
		cell1.setCellValue("商品名称");
		Cell cell2=row.createCell(2);
		cell2.setCellValue("商品类别");
		Cell cell3=row.createCell(3);
		cell3.setCellValue("供货商");
		Cell cell4=row.createCell(4);
		cell4.setCellValue("零售价");
		Cell cell5=row.createCell(5);
		cell5.setCellValue("单位");
		Cell cell6=row.createCell(6);
		cell6.setCellValue("生产日期");
		List<ProductVO>products=(List<ProductVO>) model.get("products");
		//遍历
		for (int i=0; i<products.size();i++){
			ProductVO p =products.get(i);
			Row row1 =sheet.createRow(i+1);
			Cell cell10=row1.createCell(0);
			cell10.setCellValue(p.getSpId());
			Cell cell11 = row1.createCell(1);
			cell11.setCellValue(p.getSpName());
			Cell cell12 = row1.createCell(2);
			cell12.setCellValue(p.getZlName());
			Cell cell13 = row1.createCell(3);
			cell13.setCellValue(p.getSuName());
			Cell cell14 = row1.createCell(4);
			cell14.setCellValue( p.getSpPrice().toString());
			Cell cell15 = row1.createCell(5);
			cell15.setCellValue(p.getUnit());
			Cell cell16 = row1.createCell(6);
			cell16.setCellValue(p.getSpProduceDate());

		}
	}
}
