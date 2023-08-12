package com.stopwaiting.server.web;

import com.stopwaiting.server.domain.user.User;
import com.stopwaiting.server.domain.user.UserRepository;
import com.stopwaiting.server.web.dto.user.UserCheckIdRequestDto;
import com.stopwaiting.server.web.dto.user.UserLoginRequestDto;
import com.stopwaiting.server.web.dto.user.UserLoginResponseDto;
import com.stopwaiting.server.web.dto.user.UserSaveRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class UserApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }
    @Test
    @Order(1)
    void User_중복확인() throws Exception{
        Long id = 20171243L;
        UserCheckIdRequestDto requestDto = UserCheckIdRequestDto.builder()
                .id(id)
                .build();
        String url = "http://localhost:"+port+"/api/v1/checkid";
        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    @Test
    @Order(2)
    void User_등록() throws Exception {
        //given
        Long id = 20171243L;
        String name = "한기윤";
        String password = "1q2w3e4r";
        String phoneNumber = "01030559814";
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .id(id)
                .name(name)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
        String url = "http://localhost:"+port+"/api/v1/signup";
        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getId()).isEqualTo(id);
        assertThat(all.get(0).getName()).isEqualTo(name);
    }
    @Test
    @Order(3)
    void User_로그인() throws Exception{
        //given
        Long id = 20171243L;
        String password = "1q2w3e4r";
        UserLoginRequestDto requestDto = UserLoginRequestDto.builder()
                .id(id)
                .password(password)
                .build();

        String url = "http://localhost:"+port+"/api/v1/login";
        //when
        ResponseEntity<UserLoginResponseDto> responseEntity = restTemplate.postForEntity(url,requestDto,UserLoginResponseDto.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getId()).isEqualTo(id);
    }

}