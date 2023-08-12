package com.stopwaiting.server.web.dto.user;

import com.stopwaiting.server.domain.user.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private Long id;
    private String name;
    private String password;
    private String phoneNumber;

    @Builder
    public UserSaveRequestDto(Long id, String name, String password, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    public User toEntity(String password){
        return User.builder()
                .id(id)
                .name(name)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
    }
}
