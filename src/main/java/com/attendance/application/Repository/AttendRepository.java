package com.attendance.application.Repository;

import java.time.*;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.attendance.application.Entities.SignUp;

public interface AttendRepository extends JpaRepository<SignUp,Integer>{

     @Query("SELECT s FROM SignUp s JOIN s.user u")
     List<SignUp> findAttendsWithUserDetails();

     @Query("select s from SignUp s where s.user.U_id=:userid")
     Optional<SignUp> findByUserId(@Param("userid")Integer id);

     @Query("select s from SignUp s where s.user.U_id=:userid")
     List<SignUp> findByUserID(@Param("userid")Integer U_id);

     @Query("select s from SignUp s where s.date=:localdate")
     List<SignUp> FindBydate(@Param("localdate")LocalDate localdate);

     @Query("SELECT s FROM SignUp s WHERE s.user.U_id =:userId ORDER BY s.date DESC LIMIT 1")
     SignUp findTopByUserIdOrderByCreatedAtDesc(@Param("userId") int userId);


}
