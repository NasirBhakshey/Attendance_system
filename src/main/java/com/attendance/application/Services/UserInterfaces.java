package com.attendance.application.Services;

import java.util.List;

import com.attendance.application.Entities.User;

public interface UserInterfaces {

    public boolean InsertUser(User user);
    public boolean updateUser(User user, int id);
     public User Loginuser(String email,String pass);
    public List<User> getlldetails();
    public boolean deleteUser(int id);
    public User searchbyID(int id);


    


}
