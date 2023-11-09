package com.example.supermarket.entity;

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
public class VipExchange {
    private Integer viId;
    private Integer hyId;
    private String duTime;
    private Integer isdelete;
}
