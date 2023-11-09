package com.example.supermarket.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipBalanceDTO {

    private Integer czId;

    @NotNull
    private Integer hyId;

    private String hyName;

//   接收充值金额
    @Min(value = 0,message = "充值金额不能小于0")
    @NotNull
    private Integer hyBalance;

//    显示充值金额
    private Integer czAmount;

    private String czTime;

    @NotNull
    private String czType;

    private String czStatue;

    private Integer isdelete;

}

