package com.example.supermarket.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Customer {

    private Integer gkId;

    private String gkName;

    private String gkPhone;

    private String gkLocation;

    private Integer vipId;

    private String gkBirthday;

    private Integer isdelete;


}
