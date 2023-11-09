package com.example.supermarket.service;

import com.example.supermarket.entity.Cargo;
import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.WareHouse;
import com.example.supermarket.entity.dto.GoodsDTO;
import com.example.supermarket.entity.vo.GoodsVO;

import java.util.List;

public interface GoodsService {

    PageBean<GoodsVO> getGoods(GoodsDTO goodsDTO);

    List<Cargo> getCargos();

    void addGoods(GoodsVO goodsVO);

    void delete(int storeapplyId);

    void update(GoodsVO goodsVO);

    void deleteGoodsBatch(int[] storeapplyIds);

    GoodsVO getGoodsById(int storeapplyId);

    List<WareHouse> getAllWarehouse();
}
