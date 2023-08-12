package com.stopwaiting.server.web.api;

import com.stopwaiting.server.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TimetableApiController {
    private final TimetableService timetableService;


    @GetMapping("/api/v1/waitingqueue/{id}")
    public JSONObject findInfoId(@PathVariable Long id){
        return timetableService.findInfoId(id);
    }
}
