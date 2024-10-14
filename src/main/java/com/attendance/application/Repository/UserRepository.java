package com.attendance.application.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.application.Entities.User;



public interface UserRepository extends JpaRepository<User, Integer>{

    User findByEmail(String email);

}
