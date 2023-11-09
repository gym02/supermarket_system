package com.example.supermarket.service.impl;

import com.example.supermarket.entity.VipIndent;
import com.example.supermarket.mapper.VipIndentMapper;
import com.example.supermarket.service.VipIndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Service
public class VipIndentServiceImpl implements VipIndentService {

    @Autowired
    VipIndentMapper vipIndentMapper;

    @Override
    public List<VipIndent> all() {
        return vipIndentMapper.all();
    }

    @Override
    public void add(VipIndent vipIndent) {
        vipIndentMapper.add(vipIndent);
    }

    @Override
    public void update(VipIndent vipIndent) {
        vipIndentMapper.update(vipIndent);
    }

    @Override
    public void remove(Integer jrId) {
        vipIndentMapper.remove(jrId);
    }

}
