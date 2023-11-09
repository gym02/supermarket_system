package com.example.supermarket.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class Employee {

    private Integer empId;

    private String empName;

    private Integer empAge;

    private String empGender;

    private String empPhone;

    private Date empDate;

    private Integer mdId;

    private Integer isdelete;


}
