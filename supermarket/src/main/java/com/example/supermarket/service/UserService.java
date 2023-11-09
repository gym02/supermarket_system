package com.example.supermarket.service;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.User;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
public interface UserService {
	PageBean<User> getUsers(int pageNum, int pageSize, User user);
	void addUser(User user);
	//判断邮箱
	boolean emailExists(String userEmail);
	//判断名字
	boolean nameExists(String userName);
	int findEmail(String email);
	int findUserName(String userName);


	void updateByUser(User user);
	void deleteByUser(int userId);
	void removeBatchUser(@Param("userIds") int[] userIds);

	User getUserById(int userId) ;
	User login(String userName, String userPwd);
}
