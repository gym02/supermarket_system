package com.example.supermarket.mapper;

import com.example.supermarket.entity.Shop;
import com.example.supermarket.entity.dto.ShopDTO;
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
public interface ShopMapper  {

	List<Shop> getAllShopsByCondition(ShopDTO shopDTO);

	void addShop(Shop shop);

	void editByMdId(Shop shop);

	void deleteByMdId(int mdId);

	void deleteBtMdIds(@Param("mdIds") int[] mdIds);

	List<Shop> getAll(Shop shop);

	List<Shop> getAllShops();
}
