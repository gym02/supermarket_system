package com.example.supermarket.mapper;

import com.example.supermarket.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface UserMapper {
	//查询
	List<User> getUsers(User user);
	//添加
	void addUser(User user);
	//判断
	User getUserByEmail(String userEmail);
	User getUserByName(String userName);
	int findEmail(String email);
	int findUserName(String userName);

	//修改
	void updateByUser(User user);
	//删除
	void deleteByUser(int userId);
	//批量删除
	void removeBatchUser(@Param("userIds") int[] userIds);

	@Select("select user_id userId,user_name userName,user_pwd userPwd from user where user_name=#{userName}")
	User getUserByUserName(String userName);
	@Select("select * from user where user_id=#{userId}")
	User getUserById(int userId);
}
