package com.example.supermarket.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipFindPasswordDTO {

    @Size(min = 2,max = 6,message = "名字长度在2-6之间")
    @NotNull
    private String hyName;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "邮箱格式不符合")
    @NotNull
    private String gkEmail;

    @NotNull
    private String code;
}
