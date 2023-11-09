package com.example.supermarket.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer empId;

    private String empName;

    private Integer empAge;

    private String empGender;

    private String empPhone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date from;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date to;

    private Integer mdId;

    private Integer isdelete;
    private int pageSize;
    private int pageNum;

        }
