package com.stopwaiting.server.web.dto.image;

import com.stopwaiting.server.domain.image.WaitingInfoImage;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;

@Getter
@NoArgsConstructor
public class ImageRequestDto {
    private WaitingInfo waitingInfo;
    private String filename;
    private String fileurl;

    @Builder
    public ImageRequestDto(WaitingInfo waitingInfo, String filename, String fileurl) {
        this.waitingInfo=waitingInfo;
        this.filename = filename;
        this.fileurl = fileurl;
    }

    public WaitingInfoImage toEntity(){
        return WaitingInfoImage.builder()
                .waitingInfo(waitingInfo)
                .filename(filename)
                .fileurl(fileurl)
                .build();
    }
}
