package com.stopwaiting.server.web.api;

import com.stopwaiting.server.service.UserQueueService;
import com.stopwaiting.server.web.dto.userqueue.UserQueueDeleteRequestDto;
import com.stopwaiting.server.web.dto.userqueue.UserQueueSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserQueueApiController {
    private final UserQueueService userQueueService;

    @PostMapping("/api/v1/waitinginfo/{id}/queue/{time}")
    public Long save(@PathVariable Long id, @PathVariable String time, @RequestBody UserQueueSaveRequestDto requestDto) {
        return userQueueService.save(id, time, requestDto);
    }

    @GetMapping("/api/v1/user/{id}/queue")
    public JSONObject findQueueByUser(@PathVariable Long id) {
        return userQueueService.findQueueByUser(id);
    }
    @GetMapping("/api/v1/queue/{id}")
    public JSONObject findQueueByAdminId(@PathVariable Long id){
        return userQueueService.findQueueByAdminId(id);
    }
    @DeleteMapping("/api/v1/queue/{id}/{studentId}")
    public JSONObject deleteUserQueue(@PathVariable Long id, @PathVariable Long studentId){
        return userQueueService.deleteUserQueue(id,studentId);
    }
}
