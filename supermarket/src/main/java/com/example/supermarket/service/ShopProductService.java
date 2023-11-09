package com.example.supermarket.service;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.ShopProduct;
import com.example.supermarket.entity.vo.ShopProductVO;

public interface ShopProductService {
	void add(ShopProduct shopProduct);

	void puttAllProduct(ShopProduct shopProduct);

	PageBean<ShopProductVO> getAllShopProduct(int pageNum, int pageSize, ShopProduct shopProduct);

	void pullProductBySpId(Integer spId, Integer mdId);

	void putProductBySpId(Integer spId, Integer mdId);
}
