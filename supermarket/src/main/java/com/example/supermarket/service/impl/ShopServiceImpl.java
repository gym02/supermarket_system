package com.example.supermarket.service.impl;


import com.example.supermarket.entity.Shop;
import com.example.supermarket.entity.dto.ShopDTO;
import com.example.supermarket.mapper.ShopMapper;
import com.example.supermarket.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Service
public class ShopServiceImpl implements ShopService {
@Autowired
ShopMapper shopMapper;
	@Override
	public List<Shop> getShopByCondition(ShopDTO shopDTO) {
		return shopMapper.getAllShopsByCondition(shopDTO);
	}

	@Override
	public void addShop(Shop shop) {
		shopMapper.addShop(shop);
	}

	@Override
	public void editByMdid(Shop shop) {
		shopMapper.editByMdId(shop);
	}

	@Override
	public void deleteByMdId(int mdId) {
		shopMapper.deleteByMdId(mdId);
	}

	@Override
	public void deleteByMdIds(int[] mdIds) {
		shopMapper.deleteBtMdIds(mdIds);
	}

	@Override
	public List<Shop> getAllShop(Shop shop) {
		return shopMapper.getAll(shop);
	}

	@Override
	public List<Shop> getAllShops() {
		return shopMapper.getAllShops();
	}
}
