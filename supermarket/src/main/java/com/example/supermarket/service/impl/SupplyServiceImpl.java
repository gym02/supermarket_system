package com.example.supermarket.service.impl;


import com.example.supermarket.entity.Supply;
import com.example.supermarket.entity.dto.SupplyDTO;
import com.example.supermarket.mapper.SupplyMapper;
import com.example.supermarket.service.SupplyService;
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
public class SupplyServiceImpl implements SupplyService {
	@Autowired
	SupplyMapper supplyMapper;

	@Override
	public List<Supply> getByCondition(SupplyDTO supplyDTO) {
		return supplyMapper.getByCondition(supplyDTO);
	}

	@Override
	public void addSupply(Supply supply) {
		supplyMapper.add(supply);
	}

	@Override
	public void editSupply(Supply supply) {
		supplyMapper.editSupply(supply);
	}

	@Override
	public void deleteBySuId(int suId) {
		supplyMapper.deleteBySuId(suId);
	}

	@Override
	public void removeAll(int[] suIds) {
		supplyMapper.removeAll(suIds);
	}

	@Override
	public List<Supply> getAllSupplies() {
		return supplyMapper.getAllSupplies();
	}
}
