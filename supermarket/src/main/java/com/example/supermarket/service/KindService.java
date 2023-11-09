package com.example.supermarket.service;

import com.example.supermarket.entity.Kind;
import com.example.supermarket.entity.dto.KindDTO;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface KindService {

	List<Kind> getByCondition(KindDTO kindDTO);

	void addkind(Kind kind);

	void editkind(Kind kind);

	void deleteByZlId(int zlId);

	void removeAll(int[] zlIds);

	List<Kind> getAllKinds();
}
