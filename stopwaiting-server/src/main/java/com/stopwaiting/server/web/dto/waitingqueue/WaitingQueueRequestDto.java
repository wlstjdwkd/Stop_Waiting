package com.stopwaiting.server.web.dto.waitingqueue;

import com.stopwaiting.server.domain.timetable.Timetable;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class WaitingQueueRequestDto {
    //private WaitingInfo waitingInfo;
//    private Timetable timetable;
      private String time;
//    @Builder
//    public WaitingQueueRequestDto(//WaitingInfo waitingInfo,
//                                  Timetable timetable) {
//        //this.waitingInfo = waitingInfo;
//        this.timetable = timetable;
//    }
//
//    public WaitingQueue toEntity() {
//        return WaitingQueue.builder()
//                //.waitingInfo(waitingInfo)
//                .timetable(timetable)
//                .build();
//    }
}
