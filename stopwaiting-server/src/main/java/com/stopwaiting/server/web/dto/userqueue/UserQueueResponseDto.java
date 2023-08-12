package com.stopwaiting.server.web.dto.userqueue;

import com.stopwaiting.server.domain.user.User;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueue;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserQueueResponseDto {
    private Long id;
    private WaitingQueue waitingQueue;

    public UserQueueResponseDto(Long id,WaitingQueue waitingQueue) {
        this.id = id;
        this.waitingQueue = waitingQueue;
    }
}
