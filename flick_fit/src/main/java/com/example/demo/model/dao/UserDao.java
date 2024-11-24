package com.example.demo.model.dao;

import java.util.List;
import java.util.Map;


import com.example.demo.model.dto.User;

public interface UserDao {
	//전체 유저 목록
	public List<User> selectAll();

	//유저 등록
	public int insertUser(User user);
	
	//유저 조회
	public User selectOne(Map<String, String> info);
	
	//유저 업데이트
	public int updateUser(User user);

	public User getUserById(String id);
}