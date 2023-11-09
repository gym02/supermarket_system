package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Festival;
import com.example.supermarket.mapper.VipFestivalMapper;
import com.example.supermarket.service.VipFestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Service
public class VipFestivalServiceImpl implements VipFestivalService {

    @Autowired
    VipFestivalMapper vipFestivalMapper;

    @Override
    public List<Festival> all() {
        return vipFestivalMapper.all();
    }

    @Override
    public void add(Festival festival) {
        vipFestivalMapper.add(festival);
    }

    @Override
    public void update(Festival festival) {
        vipFestivalMapper.update(festival);
    }

    @Override
    public void remove(Integer jrId) {
        vipFestivalMapper.remove(jrId);
    }

//    查询所有会员信息，并查询当天是否是节日
    @Override
    public Festival getBirthdayEqualsToday(String todayStr) {
        return vipFestivalMapper.getBirthdayEqualsToday(todayStr);
    }
}
