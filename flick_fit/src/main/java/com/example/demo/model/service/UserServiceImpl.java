package com.example.demo.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.model.dao.UserDao;
import com.example.demo.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public boolean signup(User user) {
		return userDao.insertUser(user) == 1;
	}

	@Override
	public User login(String id, String password) {
	    Map<String, String> info = new HashMap<>();
	    info.put("userId", id);       // XML에서 #{userId}로 사용
	    info.put("password", password); // XML에서 #{password}로 사용
	    User tmp = userDao.selectOne(info);
	    return tmp;
	}

}