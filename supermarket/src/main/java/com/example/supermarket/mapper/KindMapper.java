package com.example.supermarket.mapper;

import com.example.supermarket.entity.Kind;
import com.example.supermarket.entity.dto.KindDTO;
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
public interface KindMapper {

	List<Kind> getByCondition(KindDTO kindDTO);

	void add(Kind kind);

	void edit(Kind kind);

	void deleteByZlId(int zlId);

	void removeAll(@Param("zlIds") int[] zlIds);

	List<Kind> getAllKinds();
}
