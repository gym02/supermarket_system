package com.example.supermarket.service.impl;


import com.example.supermarket.mapper.PasswordMapper;
import com.example.supermarket.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceimpl implements PasswordService {
    @Autowired
    PasswordMapper passwordMapper;
    @Override
    public void change(String email, String password) {
        passwordMapper.change(email,password);
    }
}
