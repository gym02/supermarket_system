package com.example.supermarket.mapper;

import com.example.supermarket.entity.Menu;

import org.apache.ibatis.annotations.Mapper;

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
public interface MenuMapper {
	List<Menu> selectAllMenus();
	void addMenus(Menu menu);
	void removeMenuByMid(int mid);
	List<Menu> getMenusByUserId(int userId);
	void addParentMenus(Menu menu);
}
