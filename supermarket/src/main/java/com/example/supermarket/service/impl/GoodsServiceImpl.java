package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Cargo;
import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.WareHouse;
import com.example.supermarket.entity.dto.GoodsDTO;
import com.example.supermarket.entity.vo.GoodsVO;
import com.example.supermarket.mapper.GoodsMapper;
import com.example.supermarket.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public PageBean<GoodsVO> getGoods(GoodsDTO goodsDTO) {
        PageHelper.startPage(goodsDTO.getPageNum(),goodsDTO.getPageSize());
        List<GoodsVO> Goodss = goodsMapper.getGoods(goodsDTO);
        PageInfo<GoodsVO> pageInfo=new PageInfo<>(Goodss);

        PageBean<GoodsVO> pageBean=new PageBean<>(goodsDTO.getPageNum(),goodsDTO.getPageSize(),(int)pageInfo.getTotal(),
                pageInfo.getPages(),pageInfo.getList());


        return pageBean;
    }

    @Override
    public List<Cargo> getCargos() {
        return goodsMapper.getCargos();
    }

    @Override
    public void addGoods(GoodsVO goodsVO) {
        String code = UUID.randomUUID().toString().substring(0, 6);
        goodsVO.setGoodsNumber(code);
        goodsMapper.addGoods(goodsVO);
    }

    @Override
    public void delete(int storeapplyId) {
        goodsMapper.delete(storeapplyId);
    }

    @Override
    public void update(GoodsVO goodsVO) {
        goodsMapper.update(goodsVO);
    }

    @Override
    public void deleteGoodsBatch(int[] storeapplyIds) {
        goodsMapper.deleteGoodsBatch(storeapplyIds);
    }

    @Override
    public GoodsVO getGoodsById(int storeapplyId) {
        return goodsMapper.getGoodsById(storeapplyId);
    }

    @Override
    public List<WareHouse> getAllWarehouse() {
        List<WareHouse> allWarehouse= goodsMapper.selectAllWarehouse();
        List<WareHouse> wareHouses = allWarehouse.stream().filter(wareHouse -> wareHouse.getWhPid().equals(0))
                .map(wareHouse -> {
                    addchildern(wareHouse, allWarehouse);
                    return wareHouse;
                }).collect(Collectors.toList());
        return wareHouses;
    }


    private void addchildern(WareHouse wareHouse, List<WareHouse> allWarehouse) {
         /*for(Menu submenu:allmenus){
             if(submenu.getPmid().equals(menu.getMid())){
                 menu.getChildren().add(submenu);
                 addchildern(submenu,allmenus);
             }
         }*/
        allWarehouse.stream().filter(subwarehouse->subwarehouse.getWhPid().equals(wareHouse.getWhId()))
                .map(subwarehouse->{wareHouse.getChildren().add(subwarehouse);
                    addchildern(subwarehouse,allWarehouse);
                    return subwarehouse;
                }).collect(Collectors.toList());



    }
}
