use flickfit;
-- 사용자 테이블
CREATE TABLE users (
    user_id VARCHAR(36) PRIMARY KEY,         -- 사용자 ID
    user_pw VARCHAR(255) NOT NULL,
    username VARCHAR(50) NOT NULL,           -- 닉네임
    weight FLOAT NOT NULL,                   -- 체중 (칼로리 계산에 필요)
    height FLOAT,                            -- 키 (필요 시)
    age INT,                                 -- 나이
    gender VARCHAR(10) CHECK (gender IN ('male', 'female')),
    goal_calories FLOAT,                     -- 일일 칼로리 소모 목표
    goal_frequency INT,                      -- 주간 목표 운동 빈도
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 계정 생성일
    coin int DEFAULT 0, -- 사용자가 가지고 있는 코인
	last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP                      -- 마지막 로그인 날짜
);

-- 각 게임 결과
CREATE TABLE GameSession (
    session_id VARCHAR(36) PRIMARY KEY,      -- 고유 게임 세션 ID
    user_id VARCHAR(36),                     -- 사용자 ID 참조
    start_time DATETIME,                     -- 게임 시작 시간
    end_time DATETIME,                       -- 게임 종료 시간
    calories_burned FLOAT DEFAULT 0,         -- 게임 세션 동안 소모된 칼로리
	coin int DEFAULT 0,          -- 획득한 코인
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- 일일 통계
CREATE TABLE DailyStats (
    stat_id VARCHAR(36) PRIMARY KEY,         -- 고유 통계 ID
    user_id VARCHAR(36),                     -- 사용자 ID 참조
    date DATE,                               -- 해당 일자
    calories_burned FLOAT DEFAULT 0,         -- 일일 소모 칼로리
    sessions_count INT DEFAULT 0,            -- 일일 세션 수
    achieved_goal BOOLEAN DEFAULT FALSE,     -- 일일 목표 달성 여부
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);


