package com.stopwaiting.server.web.dto.waitinginfo;

import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WaitingInfoAdminResponseDto {
    private Long adminId;


    public WaitingInfoAdminResponseDto(WaitingInfo entity) {
        this.adminId = entity.getAdminId();
    }
}
