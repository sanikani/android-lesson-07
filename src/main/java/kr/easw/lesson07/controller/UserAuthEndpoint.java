package kr.easw.lesson07.controller;


import jakarta.servlet.http.HttpServletResponse;
import kr.easw.lesson07.model.ExceptionalResultDto;
import kr.easw.lesson07.model.UserDataEntity;
import kr.easw.lesson07.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserAuthEndpoint {
    private final UserDataService userDataService;
    private final BCryptPasswordEncoder encoder;


    // JWT 인증을 위해 사용되는 엔드포인트입니다.
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDataEntity entity) {
        try {
            // 로그인을 시도합니다.
            return ResponseEntity.ok(userDataService.createTokenWith(entity));
        } catch (Exception ex) {
            // 만약 로그인에 실패했다면, 400 Bad Request를 반환합니다.
            return ResponseEntity.badRequest().body(new ExceptionalResultDto(ex.getMessage()));
        }
    }


    @PostMapping("/register")
    public void register(@ModelAttribute UserDataEntity entity , HttpServletResponse response) throws IOException {
        entity.setPassword(encoder.encode(entity.getPassword()));
        userDataService.createUser(entity);
        response.sendRedirect("/");
    }
}