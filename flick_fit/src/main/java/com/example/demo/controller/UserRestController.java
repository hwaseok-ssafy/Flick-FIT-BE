package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.model.dto.User;
import com.example.demo.model.service.UserService;

@RestController
@RequestMapping("/api-user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserRestController {

	private final UserService userService;
	private final JwtUtil jwtUtil;

//	@Autowired
	public UserRestController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	// 사용자 목록 전체 가져오기
	@GetMapping("/users")
	public ResponseEntity<?> userList() {
		List<User> list = userService.getUserList();
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable String id) {
	    User user = userService.getUserById(id);
	    if (user == null) {
	        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	

	// 사용자 회원가입
	@PostMapping("/signup")
	public ResponseEntity<String> write(@RequestBody User user) {
		if (userService.signup(user)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user");
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
	    Map<String, Object> result = new HashMap<>();
	    HttpStatus status;

	    User loginUser = userService.login(user.getUserId(), user.getPassword());

	    if (loginUser != null) {
	        // 사용자 정보에서 민감한 데이터를 제거한 후 응답
	        User responseUser = new User();
	        responseUser.setUserId(loginUser.getUserId());
	        responseUser.setUsername(loginUser.getUsername());
	        responseUser.setHeight(loginUser.getHeight());
	        responseUser.setWeight(loginUser.getWeight());
	        responseUser.setAge(loginUser.getAge());
	        responseUser.setGender(loginUser.getGender());
	        responseUser.setGoalCalories(loginUser.getGoalCalories());

	        result.put("message", "login 성공");
	        result.put("access-token", jwtUtil.createToken(loginUser.getUsername())); // JWT 토큰 생성
	        result.put("user", responseUser); // 민감 정보를 제외한 사용자 정보
	        status = HttpStatus.OK;
	    } else {
	        result.put("message", "login 실패");
	        status = HttpStatus.UNAUTHORIZED;
	    }

	    return new ResponseEntity<>(result, status);
	}

	
	// 사용자 정보 변경
	@PutMapping("/{id}")
	public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) {
	    if (user.getUsername() == null || user.getUsername().isEmpty()) {
	        return new ResponseEntity<>("Username cannot be null or empty", HttpStatus.BAD_REQUEST);
	    }
	    user.setUserId(id);
	    if (userService.updateUser(user)) {
	        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
	    }
	    return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
