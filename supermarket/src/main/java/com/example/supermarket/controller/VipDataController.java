package com.example.supermarket.controller;

import com.example.supermarket.service.VipDataService;
import com.example.supermarket.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 高垚淼
 * @version 1.0
 */
@RestController
@RequestMapping("/vipData")
public class VipDataController {

    @Autowired
    VipDataService vipDataService;

//    获取不同会员等级的会员数量
    @GetMapping("/LevelCount")
    public Object LevelCount(){
        List<Map<String, Integer>> vipLevelCountDTOList =  vipDataService.LevelCount();
        return new ResponseEntity(200,"查询不同会员等级的会员数量成功",vipLevelCountDTOList);
    }

//    获取各月份余额充值的人数
    @GetMapping("/BalanceNumber")
    public Object BalanceNumber(){
        List<Integer> balanceNumber = vipDataService.BalanceNumber();
        return new ResponseEntity(200,"查询各月份余额充值记录数量成功",balanceNumber);
    }

//    获取各积分商品兑换数量
    @GetMapping("/PointNumber")
    public Object PointNumber(){
        List<Map<String,Integer>> pointNumber = vipDataService.PointNumber();
        Object[] objects = new Object[2];
        ArrayList name = new ArrayList<String>();
        ArrayList number = new ArrayList<Integer>();
        for (Map<String,Integer> m :pointNumber) {
            name.add(m.get("name"));
            number.add(m.get("number"));
        }
        objects[0] = name;
        objects[1] = number;
        return new ResponseEntity(200,"查询各积分商品兑换数量成功",objects);
    }

//    获取会员关怀界面相关的数据
    @GetMapping("/getVipCareData")
    public Object getVipCareData(){
        Object[] objects = new Object[3];
        objects = vipDataService.getVipCareData();
        return new ResponseEntity(200,"获取会员关怀数据成功",objects);
    }
}
