package com.example.supermarket.service.impl;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.ShopProduct;
import com.example.supermarket.entity.vo.ShopProductVO;
import com.example.supermarket.mapper.ShopProductMapper;
import com.example.supermarket.service.ShopProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShopProductServiceImpl implements ShopProductService {
	@Autowired
	ShopProductMapper shopProductMapper;
	@Override
	public void add(ShopProduct shopProduct) {
		shopProduct.setPutDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		System.out.println(shopProduct.getPutDate());
		shopProductMapper.add(shopProduct);

	}

	@Override
	public void puttAllProduct(ShopProduct shopProduct) {
		for (Integer spId:shopProduct.getSpIds()
		     ) {
			shopProduct.setPutDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
			System.out.println(shopProduct.getPutDate());
			shopProductMapper.putall(shopProduct.getMdId(),spId,shopProduct.getPutNo(),shopProduct.getPutDate());
		}
	}

	@Override
	public PageBean<ShopProductVO> getAllShopProduct(int pageNum, int pageSize, ShopProduct shopProduct) {
		PageHelper.startPage(pageNum,pageSize);
		 List<ShopProductVO>shopProductVOS=shopProductMapper.getBySpId(shopProduct);
		PageInfo<ShopProductVO>pageInfo=new PageInfo<>(shopProductVOS);
		System.out.println(pageInfo);
		PageBean<ShopProductVO>pageBean=new PageBean<>(pageNum,pageSize,(int) pageInfo.getTotal(),
				pageInfo.getPages(),
				pageInfo.getList());
		return pageBean;

	}

	@Override
	public void pullProductBySpId(Integer spId, Integer mdId) {
		shopProductMapper.pullProduct(spId,mdId);
	}

	@Override
	public void putProductBySpId(Integer spId, Integer mdId) {
		shopProductMapper.putProductBySpId(spId,mdId);
	}
}
