package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.model.User;
import com.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserMapper userDao;
	
	@Override
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

}
