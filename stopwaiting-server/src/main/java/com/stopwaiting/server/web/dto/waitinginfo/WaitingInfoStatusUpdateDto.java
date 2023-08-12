package com.stopwaiting.server.web.dto.waitinginfo;

import com.stopwaiting.server.domain.waitingInfo.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WaitingInfoStatusUpdateDto {
    private Status status;

    public WaitingInfoStatusUpdateDto(Status status) {
        this.status = status;
    }
}
