package com.example.supermarket.service;

import com.example.supermarket.entity.Permission;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface PermissionService {
	List<Permission> getAllPermissions();
	void addPermissions(Permission permission);
	void removePermissionByPid(int pid);
	List<String> getPermissionPathsByUserId(int userId);
	List<Permission> getPermissionsByUserId(int userId);
	void addParentPermission(Permission permission);
}
