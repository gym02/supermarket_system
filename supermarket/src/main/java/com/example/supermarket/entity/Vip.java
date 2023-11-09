package com.example.supermarket.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vip {

    private Integer gkId;

    private Integer hyId;

    @Size(min = 2,max = 6)
    private String hyName;

    private Integer hyUsername;

    @Size(min = 6)
    private String hyPassword;

    private Integer hyLevel;

    private Double hyDiscount;

    @Min(0)
    private Double hyBalance;

    private Integer hyNowPoint;

    @Min(0)
    private Integer hyPoint;

    private Integer isdelete;


}
