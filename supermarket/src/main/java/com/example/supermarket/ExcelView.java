package com.example.supermarket;



import com.example.supermarket.entity.vo.EmployeeVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

//excelView
@Component
public class ExcelView extends AbstractXlsxView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Sheet sheet = workbook.createSheet("employee info");
		//表头
		Row row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("员工号");
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("姓名");
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("性别");
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("门店");
		Cell cell4 = row.createCell(4);
		cell4.setCellValue("电话号码");
		Cell cell5 = row.createCell(5);
		cell5.setCellValue("添加日期");

		//取出map中的数据
		List<EmployeeVO> emps = (List<EmployeeVO>) model.get("emps");
		//遍历
		for (int i = 0; i < emps.size(); i++) {
			EmployeeVO e = emps.get(i);
			//为每一个对象创建一个行
			Row row1 = sheet.createRow(i + 1);
			Cell cell20 = row1.createCell(0);
			cell20.setCellValue(e.getEmpId());
			Cell cell21 = row1.createCell(1);
			cell21.setCellValue(e.getEmpName());
			Cell cell12 = row1.createCell(2);
			cell12.setCellValue(e.getEmpGender());
			Cell cell13 = row1.createCell(3);
			cell13.setCellValue(e.getShop().getMdName());
			Cell cell14 = row1.createCell(4);
			cell14.setCellValue(e.getEmpPhone());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String dateString = sdf.format(e.getEmpDate());
			Cell cell15 = row1.createCell(5);
			cell15.setCellValue(dateString);

		}
	}
}
