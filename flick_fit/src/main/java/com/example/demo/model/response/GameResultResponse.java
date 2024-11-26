package com.example.demo.model.response;

public class GameResultResponse {
    private String username;             // 사용자 이름
    private float goalCalories;          // 목표 칼로리
    private float caloriesBurned;        // 현재 게임에서 소모된 칼로리
    private float dailyCaloriesBurned;   // 일일 총 소모 칼로리
    private int dailySessionsCount;      // 일일 세션 수
    private boolean dailyGoalAchieved;   // 목표 달성 여부
    private int coins;                   // 게임에서 획득한 코인

    // 수정된 생성자
    public GameResultResponse(String username, float goalCalories, float caloriesBurned,
                              float dailyCaloriesBurned, int dailySessionsCount,
                              boolean dailyGoalAchieved, int coins) {
        this.username = username;
        this.goalCalories = goalCalories;
        this.caloriesBurned = caloriesBurned;
        this.dailyCaloriesBurned = dailyCaloriesBurned;
        this.dailySessionsCount = dailySessionsCount;
        this.dailyGoalAchieved = dailyGoalAchieved;
        this.coins = coins;
    }
    
	// Getter와 Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getGoalCalories() {
        return goalCalories;
    }

    public void setGoalCalories(float goalCalories) {
        this.goalCalories = goalCalories;
    }

    public float getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(float caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public float getDailyCaloriesBurned() {
        return dailyCaloriesBurned;
    }

    public void setDailyCaloriesBurned(float dailyCaloriesBurned) {
        this.dailyCaloriesBurned = dailyCaloriesBurned;
    }

    public int getDailySessionsCount() {
        return dailySessionsCount;
    }

    public void setDailySessionsCount(int dailySessionsCount) {
        this.dailySessionsCount = dailySessionsCount;
    }

    public boolean isDailyGoalAchieved() {
        return dailyGoalAchieved;
    }

    public void setDailyGoalAchieved(boolean dailyGoalAchieved) {
        this.dailyGoalAchieved = dailyGoalAchieved;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
