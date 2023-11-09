package com.example.supermarket.entity.vo;

import com.example.supermarket.entity.File;
import com.example.supermarket.entity.Shop;
import com.example.supermarket.entity.User;
import com.example.supermarket.validationgroup.EmployeeAddGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {
    private Integer empId;

    private String empName;
    @Min(value = 18,message = "年龄不小于18",groups = {EmployeeAddGroup.class})
    @Max(value = 60,message = "年龄不大于60",groups = {EmployeeAddGroup.class})
    private Integer empAge;

    private String empGender;
    @Pattern(regexp ="^1[3456789]\\d{9}$",message = "手机格式错误",groups ={EmployeeAddGroup.class})
    private String empPhone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date empDate;
    private File file;

    private Shop shop;




    private Integer isdelete;

    private User user;
}
