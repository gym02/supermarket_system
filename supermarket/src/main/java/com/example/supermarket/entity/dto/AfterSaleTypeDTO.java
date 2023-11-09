package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfterSaleTypeDTO {
    private String ddId;//订单号

    private String asType;//售后类型

    private String asStatus;//售后状态

    private String asReason;//发起原因

    private String asExplain;//退款说明
}
