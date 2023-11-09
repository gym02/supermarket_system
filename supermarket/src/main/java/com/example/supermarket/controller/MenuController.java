package com.example.supermarket.controller;


import com.example.supermarket.entity.Menu;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.service.MenuService;
import com.example.supermarket.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	MenuService menuService;

	/**
	 * 获取所有菜单
	 * @return
	 */
	@RequestMapping("/get/all")
	public ResponseResult<List<Menu>> getAllMenus(){
		return new ResponseResult<List<Menu>>(200,"success",
				menuService.getAllMenus());
	}

	/**
	 * 添加子级菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping("/addMenus")
	public ResponseResult<Void> addMenus(@RequestBody Menu menu){
		menuService.addMenus(menu);
		return new ResponseResult<Void>(200,"success",null);
	}


	/**
	 * 添加父级菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping("/addParentMenus")
	public ResponseResult<Void> addParentMenus(@RequestBody Menu menu){
		menuService.addParentMenus(menu);
		return new ResponseResult<Void>(200,"success",null);
	}

	/**
	 * 删除菜单
	 * @param mid
	 * @return
	 */
	@RequestMapping("/remove/{mid}")
	public ResponseResult<Void> removeMenuByMid(@PathVariable("mid") int mid){
		menuService.removeMenuByMid(mid);
		return new ResponseResult<>(200,"success",null);
	}

	/**
	 * 根据用户Id获取用户的拥有的菜单
	 * @param token
	 * @return
	 */
	@RequestMapping("/getMenus")
	public ResponseResult<List<Menu>> getMenusByUid(@RequestHeader("token") String token){
		//根据token解析id
		int userId = Integer.parseInt(JwtUtil.getUserId(token));
		return new ResponseResult<List<Menu>>(200,"success",menuService.getMenusByUserId(userId));
	}
}

