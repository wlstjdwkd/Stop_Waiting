package com.stopwaiting.server.web.api;

import com.stopwaiting.server.service.WaitingInfoService;
import com.stopwaiting.server.web.dto.waitinginfo.WaitingInfoResponseDto;
import com.stopwaiting.server.web.dto.waitinginfo.WaitingInfoSaveRequestDto;
import com.stopwaiting.server.web.dto.waitinginfo.WaitingInfoStatusUpdateDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class WaitingInfoApiController {
    private final WaitingInfoService waitingInfoService;

    @PostMapping("/api/v1/waitinginfo")
    public Long save(@RequestBody WaitingInfoSaveRequestDto requestDto) throws IOException {
        return waitingInfoService.save(requestDto);
    }

    @GetMapping("/api/v1/waitinginfo/all")
    public JSONObject findAll() {
        return waitingInfoService.findAll();
    }

    @GetMapping("/api/v1/waitinginfo/{id}")
    public WaitingInfoResponseDto findById(@PathVariable Long id) {
        return waitingInfoService.findById(id);
    }

    @GetMapping("/api/v1/waitinginfo")
    public JSONObject findByAdminId(@RequestParam Long adminId) {
        return waitingInfoService.findByAdminId(adminId);
    }

    @GetMapping("/api/v1/waitinginfo/holded")
    public JSONObject findByStatusIsHold() {
        return waitingInfoService.findByStatusIsHolded();
    }

    @GetMapping("/api/v1/waitinginfo/confirmed")
    public JSONObject findByStatusIsConfirmed() {
        return waitingInfoService.findByStatusIsConfirmed();
    }

    @PutMapping("/api/v1/waitinginfo/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestBody WaitingInfoStatusUpdateDto requestDto){
        waitingInfoService.updateStatus(id,requestDto);
    }
    @DeleteMapping("/api/v1/waitinginfo/{id}")
    public void delete(@PathVariable Long id){
        waitingInfoService.delete(id);
    }

}
