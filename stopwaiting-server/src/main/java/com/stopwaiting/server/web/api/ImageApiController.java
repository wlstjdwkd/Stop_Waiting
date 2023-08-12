package com.stopwaiting.server.web.api;

import com.stopwaiting.server.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ImageApiController {
    private final ImageService imageService;
    @PostMapping("/api/v1/waitinginfo/{id}/image")
    public Long saveImage(@PathVariable Long id,@RequestParam("webapp/image") MultipartFile file) throws IOException {
        return imageService.save(id,file);
    }
    @PostMapping("/api/v1/waitinginfo/{id}/images")
    public void saveImages(@PathVariable Long id,@RequestBody MultipartFile[] files) {
        Arrays.asList(files)
                .stream()
                .map(file-> {
                    try {
                        return saveImage(id,file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    @GetMapping(
            value = "/image/{imageName}.{extension}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImage(@PathVariable(name = "imageName") String imageName,
                                         @PathVariable(name = "extension",required = false)
                                                 String extension, HttpServletRequest request)throws IOException{
        String imagePath =request.getServletContext().getRealPath("/image/"+imageName+"."+extension);
        InputStream in = new FileInputStream(imagePath);
        byte[] imageByteArray = IOUtils.toByteArray(in);
        in.close();

        return imageByteArray;
    }
}
