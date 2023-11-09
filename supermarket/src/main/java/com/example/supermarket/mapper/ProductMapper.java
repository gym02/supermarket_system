package com.example.supermarket.mapper;


import com.example.supermarket.entity.Product;
import com.example.supermarket.entity.dto.ProductDTO;
import com.example.supermarket.entity.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Mapper
public interface ProductMapper {

	List<ProductVO> getByCondition(ProductDTO productDTO);

	void addProduct(Product product);

	void deleteBySpId(int spId);

	void removeAll(@Param("spIds") int[] spIds);

	List<ProductVO> getAllProduct(ProductVO productVO);

	void edit(Product product);
}
