package com.stopwaiting.server.service;

import com.stopwaiting.server.domain.user.User;
import com.stopwaiting.server.domain.user.UserRepository;
import com.stopwaiting.server.domain.userqueue.UserQueueRepository;
import com.stopwaiting.server.domain.waitingInfo.Status;
import com.stopwaiting.server.web.dto.FCMRequestDto;
import com.stopwaiting.server.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserQueueRepository userQueueRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Transactional
    public Long save(UserSaveRequestDto userSaveRequestDto) {
        String encodedPassword = passwordEncoder.encode(userSaveRequestDto.getPassword());
        return userRepository.save(userSaveRequestDto.toEntity(encodedPassword)).getId();
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디의 회원정보가 없습니다. id=" + id));
        userRepository.delete(user);
    }

    @Transactional
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        Long id = requestDto.getId();
        User entity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디의 회원정보가 없습니다. id=" + id));
        String password = requestDto.getPassword();
        if (!passwordEncoder.matches(password, entity.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        updateToken(id,requestDto.getToken());

        return UserLoginResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }
    @Transactional
    public Long update(Long id, UserUpdateRequestDto userUpdateRequestDto){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 회원정보가 없습니다."));
        user.update(userUpdateRequestDto.getPhoneNumber(),userUpdateRequestDto.getReported());
        return id;
    }
    @Transactional
    public void updateToken(Long id, String token){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 회원정보가 없습니다."));
        user.updateToken(token);
        userRepository.save(user);
    }
    @Transactional
    public void addReport(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 아이디의 회원정보가 없습니다."));
        user.addReport();
        userRepository.save(user);

        RestTemplate restTemplate = new RestTemplate();
        FCMRequestDto fcmRequestDto = new FCMRequestDto(user.getToken(),
                "누적 신고횟수:"+user.getReported(), "조심하세요.");

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/v1/fcm")
                .encode()
                .build()
                .toUri();
        restTemplate.postForEntity(uri,fcmRequestDto,FCMRequestDto.class);


    }
    @Transactional
    public Long checkId(Long id){
        if(!userRepository.existsById(id))
            return id;
        else
            throw new IllegalArgumentException("중복된 아이디 입니다.");
    }
    public UserResponseDto findById(Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디의 회원정보가 없습니다. id=" + id));
        return new UserResponseDto(entity);
    }

    public JSONObject findByReported() {
        JSONObject jsonMain = new JSONObject();
        jsonMain.put("data",userRepository.findByReported()
                .stream()
                .map(user-> modelMapper.map(user,UserReportedResponseDto.class))
                .collect(Collectors.toList()));
        return jsonMain;

    }

}
