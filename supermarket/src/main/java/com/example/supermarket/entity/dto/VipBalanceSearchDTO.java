package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VipBalanceSearchDTO {

    private Integer czId;

    private Integer hyId;

    private String hyName;

    private Integer hyBalance;

    private Integer czAmount;

    private List<Date> czTimeArray;

    private String czTime;

    private String czType;

    private String czStatue;

    private Integer pageNum;

    private Integer pageSize;

    private Integer isdelete;
}
