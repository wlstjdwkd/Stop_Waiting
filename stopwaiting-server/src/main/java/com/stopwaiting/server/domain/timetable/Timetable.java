package com.stopwaiting.server.domain.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)//Lazy에서 바꿈
    @JoinColumn(name = "WAITINGINFO_ID",nullable = false)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private WaitingInfo waitingInfo;

    @Column(nullable = false)
    private String time;

    public void addWaitingInfo(WaitingInfo waitingInfo){
        this.waitingInfo=waitingInfo;
    }

    @Builder
    public Timetable(WaitingInfo waitingInfo, String time) {
        this.waitingInfo = waitingInfo;
        this.time = time;
    }
}
