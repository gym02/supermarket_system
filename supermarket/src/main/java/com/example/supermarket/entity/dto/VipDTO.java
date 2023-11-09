package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipDTO {

    private Integer gkId;

    private Integer hyId;

    @Size(min = 2,max = 6,message = "名字长度在2-6之间")
    private String hyName;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$" ,message = "邮箱格式不符合")
    private String gkEmail;

    private Integer hyUsername;

    @Size(min = 6,message = "密码不能少于6位")
    private String hyPassword;

    private Integer hyLevel;

    private Double hyDiscount;

    @Min(value = 0,message = "余额不能少于0")
    private Double hyBalance;

    @Min(value = 0,message = "会员积分不能少于0")
    private Integer hyPoint;

    private Integer hyTotalPoint;

    private Integer isdelete;
}
