package com.stopwaiting.server.service;

import com.stopwaiting.server.domain.image.WaitingInfoImageRepository;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfoRepository;
import com.stopwaiting.server.web.dto.image.ImageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final WaitingInfoImageRepository imageRepository;
    private final WaitingInfoRepository waitingInfoRepository;
    @Transactional
    public Long save(Long id,MultipartFile file) throws IOException {
            Date date= new Date();
            String originalFilename = file.getOriginalFilename();
            String pathname= "/webapp/image/" + originalFilename;
            File dest = new File("C:/KIT/2022-1/Capstone/stopwaiting-server/src/main/webapp/image/" + originalFilename);
            file.transferTo(dest);

            WaitingInfo waitingInfo = waitingInfoRepository.findById(id).orElseThrow(()->new IllegalArgumentException("이미지 저장에 필요한 웨이팅정보가 없습니다"));
            ImageRequestDto ImageRequestDto = new ImageRequestDto(waitingInfo,originalFilename,pathname);
            return imageRepository.save(ImageRequestDto.toEntity()).getId();
    }

}
