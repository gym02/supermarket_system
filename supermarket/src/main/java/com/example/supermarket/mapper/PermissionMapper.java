package com.example.supermarket.mapper;

import com.example.supermarket.entity.Permission;

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
public interface PermissionMapper  {
	List<Permission> selectAllPermissions();
	void addPermissions(Permission permission);
	void delPermissionByPid(int pid);
	List<Permission> getPermissionPathsByUserId(int userId);
	void addParentPermission(Permission permission);
}
