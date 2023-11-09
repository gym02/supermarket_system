package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Menu;
import com.example.supermarket.entity.Permission;
import com.example.supermarket.entity.Role;

import com.example.supermarket.entity.vo.RoleMenuVO;
import com.example.supermarket.entity.vo.RolePermissionVO;
import com.example.supermarket.mapper.MenuMapper;
import com.example.supermarket.mapper.RoleMapper;
import com.example.supermarket.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;

	@Autowired
	MenuMapper menuMapper;
	/**
	 * 查询全部角色
	 * @return
	 */
	@Override
	public List<Role> getAllRoles() {
		return roleMapper.selectAllRoles();
	}

	@Override
	public void addRole(Role role) {
		roleMapper.addRole(role);
	}

	/**
	 * 通过角色id获取菜单
	 * @param rid
	 * @return
	 */
	@Override
	public List<Integer> getMidByRid(int rid) {
		List<Menu> menus = roleMapper.selectMenusByRid(rid);
		List<Integer> mids = new ArrayList<>();
		return menus.stream().map(menu -> menu.getMid()).collect(Collectors.toList());
	}

	/**
	 *分配菜单
	 * @param roleMenuVO
	 * @return
	 */
	@Transactional
	@Override
	public void allocationMenu(RoleMenuVO roleMenuVO) {
		//先删除当前角色的所有已分配的菜单,再添加
		roleMapper.deleteMenusByRid(roleMenuVO.getRole().getRid());
		//添加新的菜单
		roleMapper.insertMenusByRid(roleMenuVO.getRole().getRid(),roleMenuVO.getMids());
	}

	@Override
	public List<Integer> getPidByRid(int rid) {
		List<Permission> permissions = roleMapper.selectPermissionsByRid(rid);
		List<Integer> pids = new ArrayList<>();
		return permissions.stream().map(permission -> permission.getPid()).collect(Collectors.toList());
	}

	/**
	 * 权限
	 * @param rolePermissionVO
	 */
	@Transactional
	@Override
	public void allocationPermission(RolePermissionVO rolePermissionVO) {
		//先删除当前角色的所有已分配的菜单
		roleMapper.deletePermissionsByRid(rolePermissionVO.getRole().getRid());
		//然后再添加新的权限
		roleMapper.insertPermissionsByRid(rolePermissionVO.getRole().getRid(),rolePermissionVO.getPids());
	}
}
