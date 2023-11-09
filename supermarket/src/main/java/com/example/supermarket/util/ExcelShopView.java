package com.example.supermarket.util;

import com.example.supermarket.entity.Shop;
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
public class ExcelShopView extends AbstractXlsxView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		Sheet sheet =workbook.createSheet("shop info");
		//表头
		Row row =sheet.createRow(0);
		Cell cell0=row.createCell(0);
		cell0.setCellValue("门店编号");
		Cell cell1=row.createCell(1);
		cell1.setCellValue("门店名");
		Cell cell2=row.createCell(2);
		cell2.setCellValue("地址");
		Cell cell3=row.createCell(3);
		cell3.setCellValue("电话");
		Cell cell4=row.createCell(4);
		cell4.setCellValue("店长");
		Cell cell5=row.createCell(5);
		cell5.setCellValue("备注");
		List<Shop>shops=(List<Shop>) model.get("shops");
		//遍历
		for (int i=0; i<shops.size();i++){
			Shop s =shops.get(i);
			Row row1 =sheet.createRow(i+1);
			Cell cell10=row1.createCell(0);
			cell10.setCellValue(s.getMdId());
			Cell cell11 = row1.createCell(1);
			cell11.setCellValue(s.getMdName());
			Cell cell12 = row1.createCell(2);
			cell12.setCellValue(s.getMdAddress());
			Cell cell13 = row1.createCell(3);
			cell13.setCellValue(s.getMdPhone());
			Cell cell14 = row1.createCell(4);
			cell14.setCellValue(s.getMdCheif());
			Cell cell15 = row1.createCell(5);
			cell15.setCellValue(s.getMdDescribe());

		}
	}
}
