package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Menu;
import com.example.supermarket.entity.Role;
import com.example.supermarket.exception.MenuReferenceException;
import com.example.supermarket.mapper.MenuMapper;
import com.example.supermarket.mapper.RoleMapper;
import com.example.supermarket.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuMapper menuMapper;

	@Autowired
	RoleMapper roleMapper;

	/**
	 * 查询所有菜单
	 * @return
	 */
	@Override
	public List<Menu> getAllMenus() {
		//查询所有并进行后端树枝节点拆分
		List<Menu> allmenus = menuMapper.selectAllMenus();
//		List<Menu> menus = allmenus
//				//筛选一级菜单
//				.stream().filter(menu -> menu.getPmid().equals(0))
//				.map(menu ->{
//					//对一级菜单添加children属性
//					menu.setChildren(allmenus.stream()
//							.filter(submenu -> submenu
//									.getPmid().equals(menu.getMid()))
//							.collect(Collectors.toList()));
//					return menu;
//				})
//				.collect(Collectors.toList());
//		return menus;
		List<Menu> menus = new ArrayList<>();
		for (Menu menu : allmenus) {
			if(menu.getPmid()==0){
				menu.setChildren(new ArrayList<>()); // 添加一个空的子菜单列表
				menus.add(menu);
		        addChildren(menu,allmenus);
			}
		}
		return menus;
	}

	private void addChildren(Menu root,List<Menu> allmenus){
		for(Menu submenu : allmenus){
			if(submenu.getPmid().equals(root.getMid())){
				submenu.setChildren(new ArrayList<>()); // 添加一个空的子菜单列表
				root.getChildren().add(submenu);
//				addChildren(submenu,allmenus);
				addGrandchildren(submenu, allmenus);
			}
		}
	}

	private void addGrandchildren(Menu submenu, List<Menu> allmenus) {
		for (Menu grandchild : allmenus) {
			if (grandchild.getPmid().equals(submenu.getMid())) {
				grandchild.setChildren(new ArrayList<>()); // 添加一个空的子菜单列表
				submenu.getChildren().add(grandchild);
			}
		}
	}


	/**
	 * 根据uid查询用户已拥有的菜单
	 * @param userId
	 * @return
	 */
	@Override
	public List<Menu> getMenusByUserId(int userId) {
		List<Menu> allmenus = menuMapper.getMenusByUserId(userId);
//		List<Menu> menus = allmenus
//				//筛选一级菜单
//				.stream().filter(menu -> menu.getPmid().equals(0))
//				.map(menu ->{
//					//对一级菜单添加children属性
//					menu.setChildren(allmenus.stream()
//							.filter(submenu -> submenu
//									.getPmid().equals(menu.getMid()))
//							.collect(Collectors.toList()));
//					return menu;
//				})
//				.collect(Collectors.toList());
		List<Menu> menus = new ArrayList<>();
		for (Menu menu : allmenus) {
			if(menu.getPmid()==0){
				menus.add(menu);
				addChildren(menu,allmenus);
			}
		}
		return menus;
	}

	/**
	 * 添加父级菜单
	 * @param menu
	 */
	@Override
	public void addParentMenus(Menu menu) {
		menuMapper.addParentMenus(menu);
	}

	/**
	 * 添加子级菜单
	 * @param menu
	 */
	@Override
	public void addMenus(Menu menu) {
		menuMapper.addMenus(menu);
	}

	/**
	 * 删除
	 * @param mid
	 */
	@Override
	public void removeMenuByMid(int mid) {
		//被引用的不能删除
		List<Role> roles = roleMapper.selectRolesByMid(mid);
		if (roles.size()!=0){
			throw new MenuReferenceException("菜单被使用,不能删除");
		}else {
			//TODD:执行删除
			menuMapper.removeMenuByMid(mid);
		}
	}
}
