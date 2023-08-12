package com.stopwaiting.server.web.dto.userqueue;

import com.stopwaiting.server.domain.user.User;
import com.stopwaiting.server.domain.userqueue.UserQueue;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class UserQueueSaveRequestDto {
    Long id;
//    private User user;
//    private WaitingQueue waitingQueue;
//
//    @Builder
//    public UserQueueSaveRequestDto(User user, WaitingQueue waitingQueue) {
//        this.user = user;
//        this.waitingQueue = waitingQueue;
//    }
//    public UserQueue toEntity(){
//        return UserQueue.builder()
//                .user(user)
//                .waitingQueue(waitingQueue)
//                .build();
//    }
}
