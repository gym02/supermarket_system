package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfterSaleDTO {
    private String afId;//售后单号

    private String ddId;//对应订单号

    private String mdName;//所属门店

    private String asType;//售后类型

    private String asStatus;//售后状态

    private String from;//申请开始时间

    private String to;//申请结束时间

    private int pageIndex;//分页页码

    private int pageNum;//总页数
}
