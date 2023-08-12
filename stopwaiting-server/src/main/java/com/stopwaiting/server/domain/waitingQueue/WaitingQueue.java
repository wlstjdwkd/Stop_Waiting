package com.stopwaiting.server.domain.waitingQueue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stopwaiting.server.domain.timetable.Timetable;
import com.stopwaiting.server.domain.user.User;
import com.stopwaiting.server.domain.userqueue.UserQueue;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class WaitingQueue {
    @Id
    @GeneratedValue
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "WAITINGINFO_ID")
//    private WaitingInfo waitingInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIMETABLE_ID")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Timetable timetable;

    @JsonManagedReference
    @OneToMany(mappedBy = "waitingQueue",cascade = CascadeType.ALL)
    private List<UserQueue> userQueues = new ArrayList<>();

    @Builder
    public WaitingQueue(//WaitingInfo waitingInfo,
                        Timetable timetable) {
        //this.waitingInfo = waitingInfo;
        this.timetable = timetable;
    }
    public void addUserQueue(UserQueue userQueue){
        this.userQueues.add(userQueue);
        userQueue.updateWaitingQueue(this);
    }
}
