package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Permission;
import com.example.supermarket.entity.Role;
import com.example.supermarket.exception.MenuReferenceException;
import com.example.supermarket.mapper.PermissionMapper;
import com.example.supermarket.mapper.RoleMapper;
import com.example.supermarket.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

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
public class PermissionServiceImpl  implements PermissionService {
	@Autowired
	PermissionMapper permissionMapper;

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	RedisTemplate<String,Object> redisTemplate;

	@Override
	public List<String> getPermissionPathsByUserId(int userId) {
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		if (redisTemplate.hasKey("permission:"+userId)){
			return (List<String>) opsForValue.get("permission:"+userId);
		}else {
			List<Permission> permissions = permissionMapper.getPermissionPathsByUserId(userId);
			List<String> paths = permissions.stream().map(permission -> permission.getPpath()).collect(Collectors.toList());
			opsForValue.set("permission:" + userId,paths);
			return paths;
		}
	}

	@Override
	public List<Permission> getAllPermissions() {
		List<Permission> allPermissions = permissionMapper.selectAllPermissions();
		List<Permission> permissions = allPermissions
				//筛选一级权限
				.stream().filter(permission -> permission.getPpid().equals(0))
				.map(permission ->{
					//对一级权限添加children属性
					permission.setChildren(allPermissions.stream()
							.filter(subpermission -> subpermission
									.getPpid().equals(permission.getPid()))
							.collect(Collectors.toList()));
					return permission;
				})
				.collect(Collectors.toList());
		return permissions;
	}

	@Override
	public List<Permission> getPermissionsByUserId(int userId) {
		List<Permission> allPermissions = permissionMapper.selectAllPermissions();
		List<Permission> permissions = allPermissions
				//筛选一级权限
				.stream().filter(permission -> permission.getPpid().equals(0))
				.map(permission ->{
					//对一级权限添加children属性
					permission.setChildren(allPermissions.stream()
							.filter(subpermission -> subpermission
									.getPpid().equals(permission.getPid()))
							.collect(Collectors.toList()));
					return permission;
				})
				.collect(Collectors.toList());
		return permissions;
	}

	@Override
	public void addParentPermission(Permission permission) {
		permissionMapper.addParentPermission(permission);
	}

	/**
	 * 删除权限
	 * @param pid
	 */
	@Override
	public void removePermissionByPid(int pid) {
		//被引用的不能删除
		List<Role> roles = roleMapper.selectRolesByPid(pid);
		if (roles.size()!=0){
			throw new MenuReferenceException("权限被使用,不能删除");
		}
		//TODD:执行删除
		permissionMapper.delPermissionByPid(pid);
	}

	/**
	 * 添加权限
	 * @param permission
	 */
	@Override
	public void addPermissions(Permission permission) {
		permissionMapper.addPermissions(permission);
	}
}
