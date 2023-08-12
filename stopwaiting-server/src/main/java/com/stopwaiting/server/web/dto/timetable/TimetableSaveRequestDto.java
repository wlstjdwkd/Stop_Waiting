package com.stopwaiting.server.web.dto.timetable;

import com.stopwaiting.server.domain.timetable.Timetable;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TimetableSaveRequestDto {
    private WaitingInfo waitingInfo;
    private String time;

    @Builder
    public TimetableSaveRequestDto(WaitingInfo waitingInfo, String time) {
        this.waitingInfo = waitingInfo;
        this.time = time;
    }
    public Timetable toEntity() {
        return Timetable.builder()
                .waitingInfo(waitingInfo)
                .time(time)
                .build();
    }
}
