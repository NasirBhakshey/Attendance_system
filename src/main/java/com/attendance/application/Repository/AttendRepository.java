package com.attendance.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.application.Entities.SignUp;

public interface AttendRepository extends JpaRepository<SignUp,Integer>{

    

}
