package com.example.supermarket.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PasswordMapper {
   @Update("update user set user_pwd=#{password} where user_email=#{email}")
    void change(@Param("email") String email, @Param("password") String password);
}
