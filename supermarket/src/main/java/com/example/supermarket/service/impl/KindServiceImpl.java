package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Kind;
import com.example.supermarket.entity.dto.KindDTO;
import com.example.supermarket.mapper.KindMapper;
import com.example.supermarket.service.KindService;
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
public class KindServiceImpl implements KindService {
	@Autowired
	KindMapper kindMapper;

	@Override
	public List<Kind> getByCondition(KindDTO kindDTO) {
		return kindMapper.getByCondition(kindDTO);
	}

	@Override
	public void addkind(Kind kind) {
		kindMapper.add(kind);
	}

	@Override
	public void editkind(Kind kind) {
		kindMapper.edit(kind);
	}

	@Override
	public void deleteByZlId(int zlId) {
		kindMapper.deleteByZlId(zlId);
	}

	@Override
	public void removeAll(int[] zlIds) {
		kindMapper.removeAll(zlIds);
	}

	@Override
	public List<Kind> getAllKinds() {
		return kindMapper.getAllKinds();
	}


}
