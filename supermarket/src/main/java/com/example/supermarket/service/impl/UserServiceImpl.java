package com.example.supermarket.service.impl;

import com.example.supermarket.entity.PageBean;
import com.example.supermarket.entity.User;
import com.example.supermarket.exception.PasswordNotException;
import com.example.supermarket.exception.UnameNotExistsException;
import com.example.supermarket.exception.UserNameNotExistException;
import com.example.supermarket.mapper.UserMapper;
import com.example.supermarket.service.UserService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-10-13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public PageBean<User> getUsers(int pageNum, int pageSize, User user) {
        //分页插件底层就是拦截器，AOP：执行查询之前调用织入
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.getUsers(user);
        //将查到的所有信息封装成一个完整的分页信息
        PageInfo<User> pageInfo = new PageInfo<>(users);
        //将所有信息封装成对象并发给前端
        PageBean<User> pageBean = new PageBean<>(pageNum,pageSize,(int)pageInfo.getTotal(),
                pageInfo.getPages(),users);
        return pageBean;
    }

    /**
     * 添加
     * @param user
     */
    @Override
    public void addUser(User user) {
//		//执行添加业务规则判断:用户名，邮箱如果被使用
//		if (emailExists(user.getUserEmail())){
//			//抛异常
//			throw new EmailNotExistsException("邮箱已被使用");
//		}
//		//if(用户名)
//		if (nameExists(user.getUserName())){
//			//抛异常
//			throw new UnameNotExistsException("用户名已被使用");
//		}
        //表中添加列salt,随机生成，“原始密码”+salt加密，存储
        user.setUserPwd(DigestUtils.md5DigestAsHex((user.getUserPwd() + "wn").getBytes()));
        userMapper.addUser(user);

    }
    @Override
    public boolean emailExists(String userEmail) {
        User user= userMapper.getUserByEmail(userEmail);
        if (user==null){return false;}else {return true;}
//		return userMapper.getUserByEmail(useremail);
    }
    @Override
    public boolean nameExists(String userName) {
        User user = userMapper.getUserByName(userName);
        if (user==null){return false;}else {return true;}
//		return userMapper.getUserByName(username);
    }
    @Override
    public int findEmail(String email) {
        return userMapper.findEmail(email);
    }

    @Override
    public int findUserName(String userName) {
        return userMapper.findUserName(userName);
    }


    /**
     * 修改用户
     * @param user
     */
    @Override
    public void updateByUser(User user) {
        userMapper.updateByUser(user);
    }

    /**
     * 根据uid删除用户
     * @param userId
     */
    @Override
    public void deleteByUser(int userId) {
        userMapper.deleteByUser(userId);
    }

    /**
     * 根据uid批量删除用户
     * @param userIds
     */
    @Override
    public void removeBatchUser(int[] userIds) {
        userMapper.removeBatchUser(userIds);
    }

    @Override
    public User login(String userName, String userPwd) {
        User user=userMapper.getUserByUserName(userName);
        if (user==null){
            throw new UnameNotExistsException("用户不存在");
        }

        if(!DigestUtils.md5DigestAsHex(userPwd.getBytes()).equals(user.getUserPwd())){
            throw new PasswordNotException("密码错误");
        }
        return user;
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }
}
