package com.example.supermarket.controller;


import com.example.supermarket.entity.Permission;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.service.PermissionService;

import com.example.supermarket.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	PermissionService permissionService;

	/**
	 * 查询全部权限
	 * @return
	 */
	@RequestMapping("/get/all")
	public ResponseResult<List<Permission>> selectAllPermissions(){
		return new ResponseResult<List<Permission>>(200,"success",
				permissionService.getAllPermissions());
	}

	/**
	 * 添加子级权限
	 * @return
	 */
	@RequestMapping("/addPermissions")
	public ResponseResult<Void> addPermissions(@RequestBody Permission permission){
		permissionService.addPermissions(permission);
		return new ResponseResult<Void>(200,"success",null);
	}

	/**
	 * 添加父级权限
	 * @return
	 */
	@RequestMapping("/addParentPermission")
	public ResponseResult<Void> addParentPermissions(@RequestBody Permission permission){
		permissionService.addParentPermission(permission);
		return new ResponseResult<Void>(200,"success",null);
	}

	/**
	 * 删除权限
	 * @return
	 */
	@RequestMapping("/remove/{pid}")
	public ResponseResult<Void> removePermissionByPid(@PathVariable("pid") int pid){
		permissionService.removePermissionByPid(pid);
		return new ResponseResult<>(200,"success",null);
	}

	/**
	 * 通过用户id获取用户的权限
	 * @param token
	 * @return
	 */
	@RequestMapping("/getPermissions")
	public ResponseResult<List<Permission>> getPermissionsByUid(@RequestHeader("token") String token){
		//根据token解析id
		int userId = Integer.parseInt(JwtUtil.getUserId(token));
		return new ResponseResult<List<Permission>>(200,"success",permissionService.getPermissionsByUserId(userId));
	}
}

