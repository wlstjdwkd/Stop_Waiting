package com.stopwaiting.server.web.dto.userqueue;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserQueueDeleteRequestDto {
    Long id;

    public UserQueueDeleteRequestDto(Long id) {
        this.id = id;
    }
}
