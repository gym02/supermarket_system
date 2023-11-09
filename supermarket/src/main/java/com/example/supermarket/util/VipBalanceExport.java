package com.example.supermarket.util;

import com.example.supermarket.entity.dto.VipBalanceSearchDTO;
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

/**
 * @author 高垚淼
 * @version 1.0
 */
@Component
public class VipBalanceExport extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Sheet sheet = workbook.createSheet("会员卡余额充值信息表");
        //表头
        Row row = sheet.createRow(0);
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("交易id");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("会员id");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("会员姓名");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("充值金额");
        Cell cell4 = row.createCell(4);
        cell4.setCellValue("充值时间");
        Cell cell5 = row.createCell(5);
        cell5.setCellValue("交易方式");
        Cell cell6 = row.createCell(6);
        cell6.setCellValue("交易状态");
        //取出map中的数据
        List<VipBalanceSearchDTO> emps = (List<VipBalanceSearchDTO>) model.get("vipBalanceSearchDTOS");
        //遍历
        for (int i = 0; i < emps.size(); i++) {
            VipBalanceSearchDTO e = emps.get(i);
            //为每一个对象创建一个行
            Row row1 = sheet.createRow(i + 1);
            Cell cell10 = row1.createCell(0);
            cell10.setCellValue(e.getCzId());
            Cell cell11 = row1.createCell(1);
            cell11.setCellValue(e.getHyId());
            Cell cell12 = row1.createCell(2);
            cell12.setCellValue(e.getHyName());
            Cell cell13 = row1.createCell(3);
            cell13.setCellValue(e.getCzAmount());
            Cell cell14 = row1.createCell(4);
            cell14.setCellValue(e.getCzTime());
            Cell cell15 = row1.createCell(5);
            cell15.setCellValue(e.getCzType());
            Cell cell16 = row1.createCell(6);
            cell16.setCellValue(e.getCzStatue());
        }
    }
}
