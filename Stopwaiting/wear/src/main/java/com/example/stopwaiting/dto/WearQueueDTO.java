package com.example.stopwaiting.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WearQueueDTO implements Serializable {
    private Long qId;
    private String queueName;
    private String time;
    private int myNum;
    private Double latitude; //위도
    private Double longitude; //경도

    public WearQueueDTO(UserInfo user, WaitingQueue data, WaitingInfo wData) {
        this.qId = data.getQId();
        this.queueName = data.getQueueName();
        this.time = data.getTime();
        this.myNum = data.getWaitingPersonList().indexOf(user);
        this.longitude = wData.getLongitude();
        this.latitude = wData.getLatitude();
    }
}