package com.attendance.application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.attendance.application.Entities.Attend;

public interface AttendRepository extends JpaRepository<Attend, Integer>{

    @Query("SELECT a FROM Attend a JOIN a.User u WHERE u.u_id = :u_id")
    List<Attend> findAttendByu_id(@Param("u_id") Integer U_id);
    

}
