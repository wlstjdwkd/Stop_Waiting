package com.stopwaiting.server.domain.waitingInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WaitingInfoRepository extends JpaRepository<WaitingInfo,Long> {
    @Query("select w from WaitingInfo  w where w.adminId=:id and w.status =:status")
    List<WaitingInfo> findByAdminId(@Param("id") Long id,@Param("status") Status status);

    @Query("select w from WaitingInfo w where w.status=:status")
    List<WaitingInfo> findByStatus(@Param("status")Status status);


}
