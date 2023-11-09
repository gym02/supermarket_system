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
public class VipSearchDTO {

    private Integer gkId;

    private Integer hyId;

    private String hyName;

    private String gkEmail;

    private Integer hyUsername;

    private String hyPassword;

    private Integer hyLevel;

    private Double hyDiscount;

    private Double hyBalance;

    private Integer hyPoint;

    private Integer hyTotalPoint;

    private Integer pageNum;

    private Integer pageSize;

    private Integer isdelete;
}
