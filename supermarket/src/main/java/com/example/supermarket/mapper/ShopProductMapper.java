package com.example.supermarket.mapper;

import com.example.supermarket.entity.ShopProduct;
import com.example.supermarket.entity.vo.ShopProductVO;
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
public interface ShopProductMapper {

	void add(ShopProduct shopProduct);

	void putall(@Param("mdId") Integer mdId,@Param("spId") Integer spId,@Param("putNo") Integer putNo,@Param("putDate")String putDate);

	List<ShopProductVO> getBySpId(ShopProduct shopProduct);

	void pullProduct(@Param("spId") Integer spId,@Param("mdId") Integer mdId);

	void putProductBySpId(@Param("spId") Integer spId,@Param("mdId") Integer mdId);
}
