package com.stopwaiting.server.web.api;

import com.stopwaiting.server.domain.userqueue.UserQueue;
import com.stopwaiting.server.service.UserService;
import com.stopwaiting.server.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/api/v1/user/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/api/v1/user/reported")
    public JSONObject findByReported(){
        return userService.findByReported();
    }
    @PostMapping("/api/v1/checkid")
    public Long checkId(@RequestBody UserCheckIdRequestDto userCheckIdRequestDto) {
        return userService.checkId(userCheckIdRequestDto.getId());
    }

    @PostMapping("/api/v1/signup")
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @PostMapping("/api/v1/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return userService.login(requestDto);
    }

    @PutMapping("/api/v1/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }
//    @PutMapping("/api/v1/user/{id}/token")
//    public void updateToken(@PathVariable Long id, @RequestBody UserTokenRequestDto requestDto){
//        userService.updateToken(id,requestDto);
//    }

    @PutMapping("/api/v1/user/{id}/report")
    public void addReport(@PathVariable Long id) {
        userService.addReport(id);
    }
    @DeleteMapping("/api/v1/user/{id}")
    public Long delete(@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
}
