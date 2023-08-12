package com.stopwaiting.server.web;

import com.stopwaiting.server.domain.user.User;
import com.stopwaiting.server.domain.waitingInfo.Type;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfoRepository;
import com.stopwaiting.server.web.dto.waitinginfo.WaitingInfoSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class WaitingInfoApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WaitingInfoRepository waitingInfoRepository;

    @AfterEach
    void tearDown() throws Exception{
        waitingInfoRepository.deleteAll();
    }

    @Test
    void WaitingInfo_등록() throws Exception{
        //given
        Long adminId=1L;
        double latitude= 30;
        double longitude= 30;
        String name="미용실";
        String locationDetail="금오공대";
        String information="미용실입니다";
        Type type=Type.NORMAL;
        int maxPerson = 5;
        List<String> timetables= new ArrayList<>();
        timetables.add("13:00");
        timetables.add("14:00");
        timetables.add("15:00");

        WaitingInfoSaveRequestDto requestDto = WaitingInfoSaveRequestDto.builder()
                .adminId(adminId)
                .latitude(latitude)
                .longitude(longitude)
                .name(name)
                .locationDetail(locationDetail)
                .information(information)
                .type(type)
                .maxPerson(maxPerson)
                .timetables(timetables)
                .build();
        String url = "http://localhost:"+port+"/api/v1/waitinginfo";
        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<WaitingInfo> all = waitingInfoRepository.findAll();
        assertThat(all.get(0).getAdminId()).isEqualTo(adminId);
        assertThat(all.get(0).getName()).isEqualTo(name);
    }

}