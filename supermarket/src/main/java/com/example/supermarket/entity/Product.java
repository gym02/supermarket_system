package com.example.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product{

    private Integer spId;

    private String spName;

    private BigDecimal spPrice;

    private Integer spNum;

    private String spImage;

    private Integer zlId;
    private Integer suId;
    private String spProduceDate;
    private String unit;

    private Integer isdelete;
    private int[] spIds;


}
