package com.example.supermarket.util;

import com.example.supermarket.entity.vo.IndentVO;
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
public class ExcelIndentView extends AbstractXlsxView {
	//导出excel数据配置
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		//创建工作表和行
		Sheet sheet =workbook.createSheet("indent info");
		Row row =sheet.createRow(0);

		//表头
		Cell cell0=row.createCell(0);
		cell0.setCellValue("订单号");
		Cell cell1=row.createCell(1);
		cell1.setCellValue("商品名");
		Cell cell2=row.createCell(2);
		cell2.setCellValue("商品图片");
		Cell cell3=row.createCell(3);
		cell3.setCellValue("商品类型");
		Cell cell4=row.createCell(4);
		cell4.setCellValue("商品单价");
		Cell cell5=row.createCell(5);
		cell5.setCellValue("购买数量");
		Cell cell6=row.createCell(6);
		cell6.setCellValue("买家");
		Cell cell7=row.createCell(7);
		cell7.setCellValue("支付方式");
		Cell cell8=row.createCell(8);
		cell8.setCellValue("下单时间");
		Cell cell9=row.createCell(9);
		cell9.setCellValue("送货方式");
		Cell cell10=row.createCell(10);
		cell10.setCellValue("交易状态");
		//取出map里的数据s
		List<IndentVO> indents = (List<IndentVO>) model.get("indents");
		//遍历
		for (int i=0; i<indents.size();i++){
			IndentVO in =indents.get(i);
			//为每一个对象创建一个行
			Row row1 =sheet.createRow(i+1);
			Cell cell11=row1.createCell(0);
			cell11.setCellValue(in.getDdId());
			Cell cell12 = row1.createCell(1);
			cell12.setCellValue(in.getProductVO().getSpName());
			Cell cell13 = row1.createCell(2);
			cell13.setCellValue(in.getProductVO().getSpImg());
			Cell cell14 = row1.createCell(3);
			cell14.setCellValue(in.getKind().getZlName());
			Cell cell15 = row1.createCell(4);
			cell15.setCellValue(in.getProductVO().getSpPrice().toString());
			Cell cell16 = row1.createCell(5);
			cell16.setCellValue(in.getDdNum());
			Cell cell17 = row1.createCell(6);
			cell17.setCellValue(in.getCustomer().getGkName());
			Cell cell18 = row1.createCell(7);
			cell18.setCellValue(in.getDdPayway());
			Cell cell19 = row1.createCell(8);
			cell19.setCellValue(in.getDdOrdertime());
			Cell cell20 = row1.createCell(9);
			cell20.setCellValue(in.getSendWay().getShName());
			Cell cell21 = row1.createCell(10);
			cell21.setCellValue(in.getDdStatus());

		}
	}
}
