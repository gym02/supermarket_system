package com.example.supermarket.service;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.WareHouse;
import com.example.supermarket.entity.dto.StoreDTO;
import com.example.supermarket.entity.vo.StoreVO;
import com.example.supermarket.entity.vo.StoresVO;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface StoreService {

    void updateStore(StoreVO storeVO);

    void getUserByPwd(String userId, String userPwd);

    PageBean<StoresVO> getStore(StoreDTO storeDTO);

    WareHouse getWareHouserById(int whId);

    Object[] getMap(int whId);
}
