package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer spId;

    private String spName;

    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    private Integer spNum;

    private String spImage;

    private Integer zlId;

    private  Integer suId;

    private  String startDate;
    private String endDate;
    private Integer isdelete;


}
