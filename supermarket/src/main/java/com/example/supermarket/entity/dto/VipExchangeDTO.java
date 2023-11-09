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
public class VipExchangeDTO {
    private Integer viId;
    private String viName;
    private Integer viPoint;
    private Integer hyId;
    private String hyName;
    private Integer hyPoint;
    private String gkEmail;
    private String dhTime;
    private Integer isdelete;
}
