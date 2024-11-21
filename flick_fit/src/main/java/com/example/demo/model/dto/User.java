package com.example.demo.model.dto;

public class User {
    private String userId;          // 사용자 ID (PK)
    private String password;          // 비밀번호
    private String username;        // 닉네임
    private Float weight;           // 체중
    private Float height;           // 키
    private Integer age;            // 나이
    private String gender;          // 성별
    private Float goalCalories;     // 일일 칼로리 소모 목표

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Float getGoalCalories() {
        return goalCalories;
    }

    public void setGoalCalories(Float goalCalories) {
        this.goalCalories = goalCalories;
    }

}

