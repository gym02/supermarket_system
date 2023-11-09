package com.example.supermarket.service;

import com.example.supermarket.entity.Festival;

import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
public interface VipFestivalService {
    List<Festival> all();

    void add(Festival festival);

    void update(Festival festival);

    void remove(Integer jrId);

    Festival getBirthdayEqualsToday(String todayStr);
}
