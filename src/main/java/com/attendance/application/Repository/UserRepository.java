package com.attendance.application.Repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.attendance.application.Entities.User;



public interface UserRepository extends JpaRepository<User, Integer>{

    User findByEmail(String email);
}
