package com.example.demo.model.dao;

import org.apache.ibatis.annotations.Param;

import com.example.demo.model.dto.DailyStats;

public interface DailyStatsDao {
	DailyStats getDailyStats(@Param("userId") String userId, @Param("gameDate") String gameDate);

    int insertDailyStats(DailyStats dailyStats);

    int updateDailyStats(DailyStats dailyStats);
}
