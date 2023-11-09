package com.example.supermarket.controller;


import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.Role;
import com.example.supermarket.entity.vo.RoleMenuVO;
import com.example.supermarket.entity.vo.RolePermissionVO;
import com.example.supermarket.exception.MenuReferenceException;
import com.example.supermarket.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService roleService;

	/**
	 * 查询全部角色
	 * @return
	 */
	@RequestMapping("/get/all")
	public ResponseResult<List<Role>> getAllRoles(){
		return new ResponseResult<List<Role>>(200,"success",
				roleService.getAllRoles());
	}

	@RequestMapping("/add")
	public ResponseResult addRole(@RequestBody Role role){
		roleService.addRole(role);
		return new ResponseResult<Void>(200,"success",null);
	}


	/**
	 * 通过角色id获取菜单
	 * @param rid
	 * @return
	 */
	@RequestMapping("/getMids/{rid}")
	public ResponseResult<List<Integer>> getMidByRid(@PathVariable("rid")int rid){
		return new ResponseResult<List<Integer>>(200,"success",
				roleService.getMidByRid(rid));
	}

	/**
	 *分配菜单
	 * @param roleMenuVO
	 * @return
	 */
	@RequestMapping("/allocationMenu")
	public ResponseResult<Void> allocationMenu(@RequestBody RoleMenuVO roleMenuVO){
		if(roleMenuVO.getRole().getRid()==0 || roleMenuVO.getMids().equals(null)){
			throw  new MenuReferenceException("没有选中数据");
		}
		roleService.allocationMenu(roleMenuVO);
		return new ResponseResult<Void>(200,"success",null);
	}

	/**
	 * 通过角色id获取权限
	 * @param rid
	 * @return
	 */
	@RequestMapping("/getPids/{rid}")
	public ResponseResult<List<Integer>> getPidByRid(@PathVariable("rid")int rid){
		return new ResponseResult<List<Integer>>(200,"success",
				roleService.getPidByRid(rid));
	}

	@RequestMapping("/allocationPermission")
	public ResponseResult<Void> allocationPermission(@RequestBody RolePermissionVO rolePermissionVO){
		if(rolePermissionVO.getRole().getRid()==0 || rolePermissionVO.getPids().equals(null)){
			throw  new MenuReferenceException("没有选中数据");
		}
		roleService.allocationPermission(rolePermissionVO);
		return new ResponseResult<Void>(200,"success",null);
	}
}

