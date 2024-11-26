package com.example.demo.model.service;

import com.example.demo.model.dto.GameSession;
import com.example.demo.model.response.GameResultResponse;

public interface GameService {

    /**
     * 게임 세션 정보를 저장하고, 사용자 및 일일 통계를 업데이트한 결과를 반환합니다.
     *
     * @param gameSession 게임 세션 데이터 (사용자 ID, 소모 칼로리, 획득 코인 등 포함)
     * @return 게임 결과 응답 객체 (GameResultResponse)
     */
    GameResultResponse saveGameResult(GameSession gameSession);
}
