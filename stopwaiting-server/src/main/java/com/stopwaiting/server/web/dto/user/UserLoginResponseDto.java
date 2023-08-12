package com.stopwaiting.server.web.dto.user;

import com.stopwaiting.server.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginResponseDto {
    private Long id;
    private String name;
    private String phoneNumber;

    @Builder
    public UserLoginResponseDto(Long id, String name, String phoneNumber){
        this.id=id;
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    public User toEntity(){
        return User.builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }
}
