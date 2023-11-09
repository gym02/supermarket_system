package com.example.supermarket.service;

import com.example.supermarket.entity.Role;
import com.example.supermarket.entity.vo.RoleMenuVO;
import com.example.supermarket.entity.vo.RolePermissionVO;


import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface RoleService {
	List<Role> getAllRoles();

	List<Integer> getMidByRid(int rid);
	void allocationMenu(RoleMenuVO roleMenuVO);

	List<Integer> getPidByRid(int rid);
	void allocationPermission(RolePermissionVO rolePermissionVO);

	void addRole(Role role);
}
