package com.example.supermarket.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfterSaleVO {
    private String asId;//售后单号

    private String ddId;//对应订单号

    private String mdName;//所属门店

    private String spName;//商品名称

    private String spImg;//商品图片

    private double spPrice;//单价

    private int ddNum;//数量

    private String gkName;//顾客昵称

    private String asType;//售后类型

    private String asStatus;//售后状态

    private String xdDate;//下单时间

    private String fkDate;//付款时间

    private String fhDate;//发货时间

    private String shDate;//收货时间

    private String sqDate;//申请时间

    private String jcDate;//审核时间

    private String thDate;//退货时间

    private String finDate;//退货完成时间

    private String asReason;//退货原因

    private String asExplain;//情况说明

}
