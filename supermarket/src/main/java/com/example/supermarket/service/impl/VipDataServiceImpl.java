package com.example.supermarket.service.impl;

import com.example.supermarket.mapper.VipDataMapper;
import com.example.supermarket.service.VipDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Service
public class VipDataServiceImpl implements VipDataService {

    @Autowired
    VipDataMapper vipDataMapper;
    @Override
    public List<Map<String, Integer>> LevelCount() {
        List<Map<String, Integer>> levelCount = vipDataMapper.getLevels();
        return levelCount;
    }

    @Override
    public List<Integer> BalanceNumber() {
        return vipDataMapper.BalanceNumber();
    }

    @Override
    public List<Map<String,Integer>> PointNumber() {
        return vipDataMapper.PointNumber();
    }

    @Override
    public Object[] getVipCareData() {
//        存储返回数据
        Object[] objects = new Object[3];
//        当天节日
        String festival = vipDataMapper.getFestival(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(5,10));
//        总会员数
        Integer vips = vipDataMapper.getVipNumber();
//        当天过生日会员
        Integer vipBirthday = vipDataMapper.getBirthdayNumber(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).substring(5,10));
        objects[0] = festival;
        objects[1] = vips;
        objects[2] = vipBirthday;
        return objects;
    }
}
