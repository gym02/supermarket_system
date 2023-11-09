package com.example.supermarket.service;

import com.example.supermarket.entity.Menu;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface MenuService {
	List<Menu> getAllMenus();
	void addMenus(Menu menu);
	void removeMenuByMid(int mid);
	List<Menu> getMenusByUserId(int userId);
	void addParentMenus(Menu menu);
}
