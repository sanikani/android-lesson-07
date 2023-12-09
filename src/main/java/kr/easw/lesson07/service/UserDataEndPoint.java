package kr.easw.lesson07.service;

import kr.easw.lesson07.model.RemoveUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RequestMapping 어노테이션을 사용하여 이 클래스의 기반 엔드포인트를 /api/v1/data로 설정합니다.
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserDataEndPoint {

    private final UserDataService userDataService;

    @GetMapping("/list")
    public List<String> listUsers() {
        return userDataService.userList();
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeUser(@RequestBody RemoveUserDto removeUser) {
        userDataService.deleteUser(removeUser.getUserId());
        return ResponseEntity.ok().body(removeUser.getUserId());
    }

}