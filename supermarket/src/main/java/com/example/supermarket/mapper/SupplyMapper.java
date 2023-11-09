package com.example.supermarket.mapper;


import com.example.supermarket.entity.Supply;
import com.example.supermarket.entity.dto.SupplyDTO;
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
public interface SupplyMapper {

	List<Supply> getByCondition(SupplyDTO supplyDTO);

	void add(Supply supply);

	void editSupply(Supply supply);

	void deleteBySuId(int suId);

	void removeAll(@Param("suIds") int[] suIds);

	List<Supply> getAllSupplies();
}
