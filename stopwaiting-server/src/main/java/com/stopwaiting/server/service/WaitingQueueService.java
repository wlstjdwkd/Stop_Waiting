package com.stopwaiting.server.service;

import com.stopwaiting.server.domain.timetable.Timetable;
import com.stopwaiting.server.domain.timetable.TimetableRepository;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfoRepository;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueue;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueueRepository;
import com.stopwaiting.server.web.dto.waitingqueue.WaitingQueueRequestDto;
import com.stopwaiting.server.web.dto.waitingqueue.WaitingQueueResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WaitingQueueService {
    private final WaitingQueueRepository waitingQueueRepository;
    private final WaitingInfoRepository waitingInfoRepository;
    private final TimetableRepository timetableRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public Long save(Long id,WaitingQueueRequestDto requestDto){
        WaitingInfo waitingInfo = waitingInfoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 웨이팅장소 id 입니다."));
        Timetable timetable = timetableRepository.findByWaitingInfoAndTime(waitingInfo,requestDto.getTime());

    return waitingQueueRepository.save(new WaitingQueue(timetable)).getId();
    }
    @Transactional(readOnly = true)
    public JSONObject findAll(){
        JSONObject jsonMain = new JSONObject();
        jsonMain.put("data",waitingQueueRepository.findAll()
        .stream()
        .map(waitingQueue -> modelMapper.map(waitingQueue, WaitingQueueResponseDto.class))
        .collect(Collectors.toList()));
        return jsonMain;
    }
    @Transactional
    public JSONObject findQueue(Long id, String time) {
        WaitingInfo waitingInfo = waitingInfoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 WaitingInfo"));
        Timetable timetable = timetableRepository.findByWaitingInfoAndTime(waitingInfo,time);

        JSONObject jsonMain = new JSONObject();
        jsonMain.put("data",waitingQueueRepository.findByTimetable(timetable));
        return jsonMain;
    }
    public JSONObject findQueueByInfoId(Long id){
        WaitingInfo waitingInfo =waitingInfoRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지않는 WaitingInfo"));
        List<Timetable> timetables = timetableRepository.findByWaitingInfo(waitingInfo);
        List<WaitingQueue> waitingQueues = new ArrayList<>();
        for(Timetable time:timetables){
            waitingQueues.add(waitingQueueRepository.findByTimetable(time));
        }
        JSONObject jsonMain = new JSONObject();
        jsonMain.put("data",waitingQueues);
        return jsonMain;
    }

}
