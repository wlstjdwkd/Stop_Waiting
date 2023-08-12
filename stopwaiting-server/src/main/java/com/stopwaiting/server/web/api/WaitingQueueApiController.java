package com.stopwaiting.server.web.api;

import com.stopwaiting.server.service.WaitingQueueService;
import com.stopwaiting.server.web.dto.waitingqueue.WaitingQueueRequestDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class WaitingQueueApiController {
    private final WaitingQueueService waitingQueueService;

    @PostMapping("/api/v1/waitinginfo/{id}/queue")
    public Long save(@PathVariable Long id, @RequestBody WaitingQueueRequestDto requestDto){
        return waitingQueueService.save(id,requestDto);
    }
    //불가능
    @GetMapping("/api/v1/waitinginfo/queues")
    public JSONObject findAll(){
        return  waitingQueueService.findAll();
    }

    @GetMapping("/api/v1/waitinginfo/{id}/queue")
    public JSONObject findQueue(@PathVariable Long id, @RequestParam String time) {
        return waitingQueueService.findQueue(id, time);
    }
    @GetMapping("/api/v1/waitinginfo/{id}/queues")
    public JSONObject findQueueByInfoId(@PathVariable Long id){
        return waitingQueueService.findQueueByInfoId(id);
    }
}
