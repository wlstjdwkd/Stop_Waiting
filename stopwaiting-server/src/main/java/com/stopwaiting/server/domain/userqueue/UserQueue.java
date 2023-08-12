package com.stopwaiting.server.domain.userqueue;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stopwaiting.server.domain.user.User;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class UserQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)//Lazy에서 바꿈
    @JoinColumn(name = "USER_ID")
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)//LAZY에서 바꿈
    @JoinColumn(name = "WAITINGQUEUE_ID")
    private WaitingQueue waitingQueue;

    @Builder
    public UserQueue(User user, WaitingQueue waitingQueue) {
        this.user = user;
        user.getUserQueues().add(this);
        this.waitingQueue = waitingQueue;
        waitingQueue.getUserQueues().add(this);
    }

    public void updateUser(User user) {
        this.user = user;
        user.getUserQueues().add(this);
    }
    public void updateWaitingQueue(WaitingQueue waitingQueue) {
        this.waitingQueue = waitingQueue;
        waitingQueue.getUserQueues().add(this);
    }
}
