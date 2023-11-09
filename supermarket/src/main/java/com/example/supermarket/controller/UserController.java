package com.example.supermarket.controller;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.ResponseResult;
import com.example.supermarket.entity.User;
import com.example.supermarket.exception.VerifyCodeErrorException;
import com.example.supermarket.service.UserService;
import com.example.supermarket.util.JwtUtil;
import com.example.supermarket.validationgroup.UserAddGroup;
import com.example.supermarket.validationgroup.UserModifyGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	RedisTemplate<String,Object> redisTemplate;
	/**
	 * 查询所有用户,分页
	 * @param pageNum
	 * @param pageSize
	 * @param User
	 * @return
	 */
	@RequestMapping ("/get/{pageNum}/{pageSize}")
	public ResponseResult<PageBean<User>> getUser(
			@PathVariable("pageNum") int pageNum,
			@PathVariable("pageSize") int pageSize,
			@RequestBody User User){
		return new ResponseResult<PageBean<User>>(200,"success",
				userService.getUsers(pageNum,pageSize,User));
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
//	//TODO:未完成
//	@RequestMapping("/add")
//	public ResponseResult addUser(@RequestBody User user){
//		//TODO:
//		userService.addUser(user);
//		return new ResponseResult<Void>(200,"success",null);
//	}
	@RequestMapping("/add")
	public ResponseResult addUser(@Validated(UserAddGroup.class) @RequestBody User user, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseResult<Void>(400, errorMessages.toString(), null);
		}
		userService.addUser(user);
		return new ResponseResult<Void>(200, "success", null);
	}

	//判断email是否存在,是否已经被使用
	@RequestMapping("/userEmail/{userEmail}")
	public ResponseResult<Boolean> emailExists(@PathVariable("userEmail") String userEmail){
		return new ResponseResult<Boolean>(200,"success",userService.emailExists(userEmail));
	}
	//判断名字是否存在,是否已经被使用
	@RequestMapping("/userName/{userName}")
	public ResponseResult<Boolean> nameExists(@PathVariable("userName") String userName){
		return new ResponseResult<Boolean>(200,"success",userService.nameExists(userName));
	}
	//查询邮箱是否已经被注册
	@PostMapping("/findEmail/{email}")//
	public Object findEmail(@PathVariable String email){
		int num = userService.findEmail(email);
		if(num!=0){
			return new ResponseResult<>(400,"failed",null);
		}else{
			return  new ResponseResult<>(200,"success",null);
		}
	}
	//查询用户名是否已经被注册
	@PostMapping("/findUserName/{userName}")//
	public Object findUserName(@PathVariable String userName){
		int num = userService.findUserName(userName);
		if(num!=0){
			return new ResponseResult<>(400,"failed",null);
		}else{
			return  new ResponseResult<>(200,"success",null);
		}
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
//	@RequestMapping("/modify")
//	public ResponseResult modify(@RequestBody User user) {
//	    //TODO:未完成
//		userService.updateByUser(user);
//		return new ResponseResult<Void>(200, "success", null);
//	}

	@PostMapping("/modify")
	public ResponseResult<Void> modifyUser(@Validated({UserModifyGroup.class}) @RequestBody User user, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return new ResponseResult<Void>(400, errorMessages.toString(), null);
		}
		userService.updateByUser(user);
		return new ResponseResult<Void>(200, "success", null);
	}


	/**
	 * 根据uid删除单个用户
	 * @param userId
	 * @return
	 */
	@RequestMapping("/remove/{userId}")
	public ResponseResult<Void> removeUser(@PathVariable("userId") int userId){
		userService.deleteByUser(userId);
		return new ResponseResult<Void>(200,"success",null);
	}

	/**
	 * 根据uid批量删除用户
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/removeBatchUser")
	public ResponseResult<Void> removeBatchUser(@RequestParam("userIds") int[] userIds){
		log.info("批量删除={}", Arrays.toString(userIds));
		userService.removeBatchUser(userIds);
		return new ResponseResult<Void>(200,"success",null);
	}

	/**
	 * 登录
	 * @param userName
	 * @param userPwd
	 * @param uuid
	 * @param verifycode
	 * @param response
	 * @return
	 */
	@GetMapping("/login")
	public ResponseResult<String> login(String userName, String userPwd, String uuid, String verifycode, HttpServletResponse response){
		if(!verifycode.equals(redisTemplate.opsForValue().get(uuid))){
			throw new VerifyCodeErrorException("验证码错误");
		}else{
			redisTemplate.delete(uuid);
		}
		User user = userService.login(userName, userPwd);
		System.out.println(user);

		String userId = user.getUserId().toString();
		System.out.println(userId);

		String token = JwtUtil.createToken(userId, user.getUserName());
		String refresh = JwtUtil.createRefresh(userId, user.getUserName());
		System.out.println(token);

		response.setHeader("token",token);
		response.setHeader("refresh",refresh);

		response.setHeader("Access-Control-Expose-Headers", "*");

		return new ResponseResult(200,"succedss","login-ok");
	}
}

