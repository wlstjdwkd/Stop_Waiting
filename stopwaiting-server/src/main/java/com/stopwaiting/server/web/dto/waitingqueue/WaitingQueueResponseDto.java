package com.stopwaiting.server.web.dto.waitingqueue;

import com.stopwaiting.server.domain.timetable.Timetable;
import com.stopwaiting.server.domain.userqueue.UserQueue;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueue;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class WaitingQueueResponseDto {
    private Long id;
    private Timetable timetable;
    private List<UserQueue>  userQueues = new ArrayList<>();

    public WaitingQueueResponseDto(WaitingQueue entity) {
        this.id = entity.getId();
        this.timetable=entity.getTimetable();
        this.userQueues = entity.getUserQueues();
    }
}
