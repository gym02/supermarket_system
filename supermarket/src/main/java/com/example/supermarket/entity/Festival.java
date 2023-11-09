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
public class Festival {

    private Integer jrId;
    private String jrName;
    private String jrBless;
    private String jrTime;
    private Integer isdelete;
}
