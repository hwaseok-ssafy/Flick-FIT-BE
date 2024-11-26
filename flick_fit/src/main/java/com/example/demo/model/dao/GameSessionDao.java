package com.example.demo.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.dto.GameSession;

@Mapper
public interface GameSessionDao {
    int insertGameSession(GameSession gameSession);
}
