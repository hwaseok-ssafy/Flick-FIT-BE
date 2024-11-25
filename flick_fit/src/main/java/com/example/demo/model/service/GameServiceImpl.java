package com.example.demo.model.service;

import com.example.demo.model.dao.DailyStatsDao;
import com.example.demo.model.dao.GameSessionDao;
import com.example.demo.model.dao.UserDao;
import com.example.demo.model.dto.DailyStats;
import com.example.demo.model.dto.GameSession;
import com.example.demo.model.dto.User;
import com.example.demo.model.response.GameResultResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GameServiceImpl implements GameService {

    private final GameSessionDao gameSessionDao;
    private final DailyStatsDao dailyStatsDao;
    private final UserDao userDao;

    public GameServiceImpl(GameSessionDao gameSessionDao, DailyStatsDao dailyStatsDao, UserDao userDao) {
        this.gameSessionDao = gameSessionDao;
        this.dailyStatsDao = dailyStatsDao;
        this.userDao = userDao;
    }

    @Override
    public GameResultResponse saveGameResult(GameSession gameSession) {
        // 입력 데이터 검증
        if (gameSession.getStartTime() == null) {
        	System.out.printf("게임 시작 시간이 누락된 요청: %s%n", gameSession);

            throw new IllegalArgumentException("게임 시작 시간이 누락되었습니다.");
        }
        if (gameSession.getEndTime() == null) {
        	
            throw new IllegalArgumentException("게임 종료 시간이 누락되었습니다.");
        }

        // GameSession 저장
        gameSessionDao.insertGameSession(gameSession);

        // 사용자 정보 가져오기
        User user = userDao.getUserById(gameSession.getUserId());
        if (user == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다: " + gameSession.getUserId());
        }
        
        // goalCalories 기본값 처리
        Float goalCalories = user.getGoalCalories();
        if (goalCalories == null) {
            goalCalories = 2000.0f; // 기본값 설정
        }

        // DailyStats 업데이트
        LocalDate gameDate = gameSession.getStartTime()
                                        .toInstant()
                                        .atZone(java.time.ZoneId.systemDefault())
                                        .toLocalDate();

        DailyStats dailyStats = dailyStatsDao.getDailyStats(gameSession.getUserId(), gameDate.toString());

        if (dailyStats == null) {
            dailyStats = new DailyStats(gameSession, user.getGoalCalories());
            dailyStatsDao.insertDailyStats(dailyStats);
        } else {
            dailyStats.updateStats(gameSession, user.getGoalCalories());
            dailyStatsDao.updateDailyStats(dailyStats);
        }

        // 사용자 코인 업데이트
        int updatedCoins = userDao.updateUserCoins(gameSession.getUserId(), gameSession.getCoin());
        if (updatedCoins < 1) {
            throw new RuntimeException("코인 업데이트 실패");
        }

        // 응답 생성
        return new GameResultResponse(
                user.getUsername(),
                user.getGoalCalories(),
                gameSession.getCaloriesBurned(),
                dailyStats.getCaloriesBurned(),
                dailyStats.getSessionsCount(),
                dailyStats.isAchievedGoal(),
                gameSession.getCoin() // 획득한 코인
        );
    }
}
