package com.stopwaiting.server.domain.waitingInfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
class WaitingInfoTest {

    @Autowired
    WaitingInfoRepository waitingInfoRepository;

    @Test
    void WaitingInfo_저장() throws Exception{
        //given
        String name = "kim";
        Long admin = 1L;
        String locationDetail = "금오공대";
        String information="미용실";
        Type type=Type.NORMAL;
        int maxPerson=5;
        double latitude=50.324;
        double longitude=50.12;
        Set<String> timetables= new HashSet<>(Arrays.asList("12:00","13:00","15:00"));

        waitingInfoRepository.save(WaitingInfo.builder()
                .name(name)
                .adminId(admin)
                .locationDetail(locationDetail)
                .information(information)
                .type(type)
                .maxPerson(maxPerson)
                .latitude(latitude)
                .longitude(longitude)
                .build());
        //when
        List<WaitingInfo> waitingInfos = waitingInfoRepository.findAll();
        //then
        WaitingInfo waitingInfo = waitingInfos.get(0);

        assertThat(waitingInfo.getAdminId()).isEqualTo(admin);
        assertThat(waitingInfo.getLatitude()).isEqualTo(latitude);

    }

}