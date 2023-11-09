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
public class VipIndent {

    private Integer viId;
    private String viName;
    private Integer viPoint;
    private Integer viNumber;
    private Integer isdelete;
}
