package com.example.supermarket.service;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.dto.ProductDTO;
import com.example.supermarket.entity.vo.ProductVO;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface ProductService {

	PageBean<ProductVO> getByCondition(int pageNum, int pageSize, ProductDTO productDTO);

	void addProduct(Product product);

	void deleteBySpId(int spId);

	void removeAll(int[] spIds);

	List<ProductVO> getAllProduct(ProductVO productVO);

	void editProduct(Product product);
}
