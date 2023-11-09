package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipLevel {
    private Integer djId;
    private String djName;
    private Integer djMin;
    private Integer djMax;
    private Double djDiscount;
    private Integer isdelete;
}
