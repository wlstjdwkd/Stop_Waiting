package com.stopwaiting.server.domain.waitingQueue;

import com.stopwaiting.server.domain.timetable.Timetable;
import com.stopwaiting.server.domain.waitingInfo.WaitingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.List;

public interface WaitingQueueRepository extends JpaRepository<WaitingQueue,Long> {
    WaitingQueue findByTimetable(Timetable timetable);
}
