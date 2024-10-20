package com.attendance.application.Repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.attendance.application.Entities.SignUp;

public interface AttendRepository extends JpaRepository<SignUp,Integer>{

     @Query("SELECT s FROM SignUp s JOIN s.user u")
     List<SignUp> findAttendsWithUserDetails();

     @Query("select s from SignUp s where s.user.U_id=id")
     List<SignUp> findByUserId(Integer id);

     // List<SignUp> findByUserID(Integer ID);

}
