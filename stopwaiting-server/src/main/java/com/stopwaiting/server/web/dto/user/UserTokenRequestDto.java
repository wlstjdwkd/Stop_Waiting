package com.stopwaiting.server.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserTokenRequestDto {
    private String token;

    @Builder
    public UserTokenRequestDto(String token) {
        this.token = token;
    }
}
