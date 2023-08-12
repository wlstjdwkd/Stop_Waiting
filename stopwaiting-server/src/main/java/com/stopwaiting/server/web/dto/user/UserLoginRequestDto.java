package com.stopwaiting.server.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    private Long id;
    private String password;
    private String token;

    @Builder
    public UserLoginRequestDto(Long id, String password,String token) {
        this.id = id;
        this.password = password;
        this.token=token;
    }
}
