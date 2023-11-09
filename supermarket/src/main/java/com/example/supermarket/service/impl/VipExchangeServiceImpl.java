package com.example.supermarket.service.impl;

import com.example.supermarket.entity.dto.VipExchangeDTO;
import com.example.supermarket.entity.dto.VipExchangeSearchDTO;
import com.example.supermarket.exception.CustomerNotFundException;
import com.example.supermarket.exception.CustomerPointNotEnoughException;
import com.example.supermarket.mapper.VipExchangeMapper;
import com.example.supermarket.service.VipExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 高垚淼
 * @version 1.0
 */
@Service
public class VipExchangeServiceImpl implements VipExchangeService {

    @Autowired
    VipExchangeMapper vipExchangeMapper;

//    添加兑换商品信息
    @Transactional
    @Override
    public void add(VipExchangeDTO vipExchangeDTO) {
        //查询会员积分余额，如果少于商品所需积分则报错
        Integer hyPoint = vipExchangeMapper.selectHyPoint(vipExchangeDTO);
        if(hyPoint == null){
            throw new CustomerNotFundException();
        }
        // 查询该积分商品对应的所有信息，和会员对应的所有信息
        VipExchangeDTO all = vipExchangeMapper.selectAllMessage(vipExchangeDTO);
        if(hyPoint < all.getViPoint()){
            throw new CustomerPointNotEnoughException();
        }
        //减少兑换商品数量
        vipExchangeMapper.decreasePointGoods(all.getViId());
        //减少会员积分
        vipExchangeMapper.decreasePoint(all);
        //会员积分充足，插入兑换记录
        vipExchangeDTO.setDhTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        vipExchangeMapper.add(vipExchangeDTO);
    }

//    查询所有兑换商品
    @Override
    public List<VipExchangeSearchDTO> search(VipExchangeSearchDTO vipExchangeSearchDTO) {
        return vipExchangeMapper.search(vipExchangeSearchDTO);
    }

    @Override
    public void remove(Integer dhId) {
        vipExchangeMapper.remove(dhId);
    }
}
