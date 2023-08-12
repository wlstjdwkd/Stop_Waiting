package com.stopwaiting.server.web.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReportedResponseDto {
    private Long id;
    private String name;
    private Integer reported;

    public UserReportedResponseDto(Long id, String name, Integer reported) {
        this.id = id;
        this.name = name;
        this.reported = reported;
    }
}
