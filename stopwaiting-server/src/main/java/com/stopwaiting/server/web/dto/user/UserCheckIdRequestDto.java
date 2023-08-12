package com.stopwaiting.server.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;

@Getter
@NoArgsConstructor
public class UserCheckIdRequestDto {
    private Long id;

    @Builder
    public UserCheckIdRequestDto(Long id) {
        this.id = id;
    }
}
