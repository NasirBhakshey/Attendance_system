package com.attendance.application.Repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.attendance.application.Entities.User;



public interface UserRepository extends JpaRepository<User, Integer>{

    User findByEmail(String email);

    @Query("SELECT Attend.a_id, User.U_name FROM Attend INNER JOIN User ON Orders.CustomerID = Customers.CustomerID")
    List<User> findUserByAttendID(@Param("a_id") Integer a_id);
    
    @Query("SELECT u FROM User u JOIN FETCH u.attend WHERE u.u_id = :u_id")
    User findUserWithAttend(Integer id);

}
