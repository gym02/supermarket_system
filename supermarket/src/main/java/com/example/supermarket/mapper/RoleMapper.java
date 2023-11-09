package com.example.supermarket.mapper;

import com.example.supermarket.entity.Menu;
import com.example.supermarket.entity.Permission;
import com.example.supermarket.entity.Role;

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
public interface RoleMapper  {
	List<Role> selectAllRoles();

	List<Role> selectRolesByPid(int pid);
	List<Role> selectRolesByMid(int pid);

	//菜单
	List<Menu> selectMenusByRid(int rid);
	void deleteMenusByRid(Integer rid);
	void insertMenusByRid(@Param("rid") Integer rid,@Param("mids") int[] mids);

	//权限
	List<Permission> selectPermissionsByRid(int rid);
	void deletePermissionsByRid(Integer rid);
	void insertPermissionsByRid(@Param("rid") Integer rid,@Param("pids") int[] pids);

	void addRole(Role role);
}
