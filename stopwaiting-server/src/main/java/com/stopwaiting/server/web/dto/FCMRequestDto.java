package com.stopwaiting.server.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMRequestDto {
    private String targetToken;
    private String title;
    private String Body;

    public FCMRequestDto(String targetToken, String title, String Body) {
        this.targetToken = targetToken;
        this.title = title;
        this.Body = Body;
    }
}
