package com.example.supermarket.entity.vo;

import com.example.supermarket.entity.Kind;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {

    private Integer spId;

    private String spName;

    private BigDecimal spPrice;

    private String spProduceDate;

    private Integer spNum;

    private String spImg;

    private  Integer suId;
    private  String suName;
    private  Integer zlId;
    private String unit;
    private String zlName;

    private Kind kind;
}
