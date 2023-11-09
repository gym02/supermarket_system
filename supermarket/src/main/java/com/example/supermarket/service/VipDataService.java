package com.example.supermarket.service;

import java.util.List;
import java.util.Map;

/**
 * @author 高垚淼
 * @version 1.0
 */
public interface VipDataService {
    List<Map<String, Integer>> LevelCount();

    List<Integer> BalanceNumber();

    List<Map<String,Integer>> PointNumber();

    Object[] getVipCareData();
}
