package com.stopwaiting.server.domain.timetable;

import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable,Long> {
    //@Query("SELECT t FROM Timetable t WHERE t.time =: time and t.waitingInfo =: id")
    Timetable findByWaitingInfoAndTime(WaitingInfo waitingInfo, String time);
    Timetable findByTime(String time);
    List<Timetable> findByWaitingInfo(WaitingInfo waitingInfo);
}
