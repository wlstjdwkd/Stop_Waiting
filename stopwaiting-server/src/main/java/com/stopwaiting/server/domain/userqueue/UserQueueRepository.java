package com.stopwaiting.server.domain.userqueue;

import com.stopwaiting.server.domain.user.User;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQueueRepository extends JpaRepository<UserQueue,Long> {
    List<UserQueue> findByUser(User user);
    UserQueue findByUserAndWaitingQueue(User user, WaitingQueue waitingQueue);
}
