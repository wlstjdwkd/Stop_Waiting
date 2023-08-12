package com.stopwaiting.server.service;

import com.google.api.client.json.Json;
import com.stopwaiting.server.domain.timetable.Timetable;
import com.stopwaiting.server.domain.timetable.TimetableRepository;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfoRepository;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueue;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueueRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;
    private final WaitingInfoRepository waitingInfoRepository;
    private final WaitingQueueRepository waitingQueueRepository;

    @Transactional
    public JSONObject findInfoId(Long id){
        WaitingQueue waitingQueue = waitingQueueRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("존재하지않는 WaitingQueue"));
        Timetable timetable =waitingQueue.getTimetable();
        JSONObject jsonMain = new JSONObject();

        jsonMain.put("data",timetable.getWaitingInfo());
        return jsonMain;
    }

}
