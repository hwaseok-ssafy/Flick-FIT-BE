package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.dto.GameSession;
import com.example.demo.model.response.GameResultResponse;
import com.example.demo.model.service.GameService;

@RestController
@RequestMapping("/api-game")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game-over")
    public ResponseEntity<?> saveGameResult(@RequestBody GameSession gameSession) {
        try {
            // 요청 데이터 로깅
            System.out.println("Received game session: " + gameSession);

            // 게임 결과 저장 로직 호출
            GameResultResponse response = gameService.saveGameResult(gameSession);

            // 성공적인 응답 로그
            System.out.println("Game result saved successfully: " + response);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 에러 로그 추가
            System.err.println("Error while saving game result: " + e.getMessage());
            e.printStackTrace();

            // 클라이언트로 에러 메시지 반환
            return ResponseEntity.status(500).body("Failed to save game result: " + e.getMessage());
        }
    }

}
