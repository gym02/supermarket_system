package com.example.supermarket.service;


import com.example.supermarket.entity.Supply;
import com.example.supermarket.entity.dto.SupplyDTO;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface SupplyService {

	List<Supply> getByCondition(SupplyDTO supplyDTO);

	void addSupply(Supply supply);

	void editSupply(Supply supply);

	void deleteBySuId(int suId);

	void removeAll(int[] suIds);

	List<Supply> getAllSupplies();
}
