package com.stopwaiting.server.web.dto.waitinginfo;

import com.stopwaiting.server.domain.timetable.Timetable;
import com.stopwaiting.server.domain.waitingInfo.Type;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class WaitingInfoSaveRequestDto {
    private Long adminId;
    private double latitude;
    private double longitude;
    private String name;
    private String locationDetail;
    private String information;
    private Type type;
    private int maxPerson;
    private List<String> timetables;

    @Builder
    public WaitingInfoSaveRequestDto(Long adminId, double latitude, double longitude,
                                     String name, String locationDetail, String information,
                                     Type type, int maxPerson,List<String> timetables) {
        this.adminId = adminId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.locationDetail = locationDetail;
        this.information = information;
        this.type = type;
        this.maxPerson = maxPerson;
        this.timetables=timetables;
    }

    public WaitingInfo toEntity() {
        return WaitingInfo.builder()
                .adminId(adminId)
                .latitude(latitude)
                .longitude(longitude)
                .name(name)
                .locationDetail(locationDetail)
                .information(information)
                .type(type)
                .maxPerson(maxPerson)
                .build();
    }
}
