package com.example.supermarket.service;

import com.example.supermarket.entity.VipIndent;

import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
public interface VipIndentService {
    List<VipIndent> all();

    void add(VipIndent vipIndent);

    void update(VipIndent vipIndent);

    void remove(Integer viId);
}
