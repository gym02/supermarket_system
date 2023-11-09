package com.example.supermarket.service;

import com.example.supermarket.entity.Shop;
import com.example.supermarket.entity.dto.ShopDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface ShopService {

	List<Shop> getShopByCondition(ShopDTO shopDTO);

	void addShop(Shop shop);

	void editByMdid(Shop shop);

	void deleteByMdId(int mdId);

	void deleteByMdIds(int[] mdIds);

	List<Shop> getAllShop(Shop shop);

	List<Shop> getAllShops();
}
