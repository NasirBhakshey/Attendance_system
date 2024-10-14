package com.attendance.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.application.Entities.Attend;

public interface AttendRepository extends JpaRepository<Attend,Integer>{

    

}
