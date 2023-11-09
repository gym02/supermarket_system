package com.example.supermarket.entity;


import com.example.supermarket.validationgroup.UserAddGroup;
import com.example.supermarket.validationgroup.UserModifyGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
public class User{
    private Integer userId;

    @NotBlank(message = "请输入用户名",groups = UserAddGroup.class)
    @Size(min = 3,max = 6,message = "密码长度必须在{min}~{max}之间",groups = {UserAddGroup.class, UserModifyGroup.class})
    private String userName;

    @NotBlank(message = "密码不能为空", groups = {UserAddGroup.class, UserModifyGroup.class})
    @Size(min = 3,max = 6,message = "用户名长度必须在{min}-{max}之间",groups = {UserAddGroup.class, UserModifyGroup.class})
    private String userPwd;

    @NotBlank(message = "邮箱不能为空",groups =
            {UserAddGroup.class, UserModifyGroup.class})
    @Pattern(regexp ="^\\w+@\\w+\\.\\w+$",message = "邮箱格式错误",
            groups ={UserAddGroup.class, UserModifyGroup.class})
    private String userEmail;
    private Role role;
    private Integer isdelete;
}
