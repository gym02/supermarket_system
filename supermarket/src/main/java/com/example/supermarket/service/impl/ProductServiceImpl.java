package com.example.supermarket.service.impl;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.dto.ProductDTO;
import com.example.supermarket.entity.vo.ProductVO;
import com.example.supermarket.mapper.ProductMapper;
import com.example.supermarket.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductMapper productMapper;

	@Override
	public PageBean<ProductVO> getByCondition(int pageNum, int pageSize, ProductDTO productDTO) {
		PageHelper.startPage(pageNum,pageSize);
		List<ProductVO>products=productMapper.getByCondition(productDTO);
		PageInfo<ProductVO>pageInfo=new PageInfo<>(products);
		System.out.println(pageInfo);
		PageBean<ProductVO>pageBean=new PageBean<>(pageNum,pageSize, (int) pageInfo.getTotal(),
				pageInfo.getPages(),
				pageInfo.getList());
		return pageBean;
	}

	@Override
	public void addProduct(Product product) {
		productMapper.addProduct(product);
	}

	@Override
	public void deleteBySpId(int spId) {
		productMapper.deleteBySpId(spId);
	}

	@Override
	public void removeAll(int[] spIds) {
		productMapper.removeAll(spIds);
	}

	@Override
	public List<ProductVO> getAllProduct(ProductVO productVO) {
		return productMapper.getAllProduct(productVO);
	}

	@Override
	public void editProduct(Product product) {
		productMapper.edit(product);
	}
}
