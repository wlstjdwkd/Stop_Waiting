package com.stopwaiting.server.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String phoneNumber;
    private Integer reported;

    @Builder
    public UserUpdateRequestDto(String phoneNumber,Integer reported) {
        this.phoneNumber = phoneNumber;
        this.reported = reported;
    }
}
