package com.example.demo.model.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DailyStats {
    private String statId;            // 통계 ID
    private String userId;            // 사용자 ID
    private LocalDate date;           // 해당 일자
    private float caloriesBurned;     // 소모된 칼로리 합계
    private int sessionsCount;        // 하루 동안의 세션 수
    private boolean achievedGoal;     // 목표 달성 여부

    // 기본 생성자
    public DailyStats() {}

    // GameSession 기반 생성자
    public DailyStats(GameSession gameSession, float goalCalories) {
        this.userId = gameSession.getUserId();
        this.date = convertToLocalDate(gameSession.getStartTime());
        this.caloriesBurned = gameSession.getCaloriesBurned();
        this.sessionsCount = 1;
        this.achievedGoal = this.caloriesBurned >= goalCalories;
    }

    // Getter와 Setter
    public String getStatId() {
        return statId;
    }

    public void setStatId(String statId) {
        this.statId = statId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(float caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public int getSessionsCount() {
        return sessionsCount;
    }

    public void setSessionsCount(int sessionsCount) {
        this.sessionsCount = sessionsCount;
    }

    public boolean isAchievedGoal() {
        return achievedGoal;
    }

    public void setAchievedGoal(boolean achievedGoal) {
        this.achievedGoal = achievedGoal;
    }

    // 통계 업데이트 메서드
    public void updateStats(GameSession gameSession, float goalCalories) {
        this.caloriesBurned += gameSession.getCaloriesBurned();
        this.sessionsCount += 1;
        this.achievedGoal = this.caloriesBurned >= goalCalories;
    }

    // Date를 LocalDate로 변환하는 유틸리티 메서드
    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
