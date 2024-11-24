package com.example.demo.model.service;

import java.util.List;

import com.example.demo.model.dto.User;

public interface UserService {
	//전체 사용자 목록 불러오기
	public List<User> getUserList();
	//사용자 등록하기
	public boolean signup(User user);
	//로그인 하기
	public User login(String id, String password);
	
	// 사용자 정보 수정 메소드 정의
    public boolean updateUser(User user);
    
	public User getUserById(String id);
}