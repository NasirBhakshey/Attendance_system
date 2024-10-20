package com.attendance.application.Services;

import java.util.List;

import com.attendance.application.Entities.SignUp;
import com.attendance.application.Entities.User;

public interface UserInterfaces {

    public boolean InsertUser(User user);
    public boolean updateUser(User user, int id);
    public User Loginuser(String email,String pass);
    public List<User> getlldetails();
    public boolean deleteUser(int id);
    public User searchbyID(int id);


    public SignUp savAttend(int user_id);
    public List<SignUp> getallAttend();
    public boolean deleteattend(int id);
    public SignUp searchByattend(int id);
    public SignUp updatAttend(SignUp attend,int id);
    public List<SignUp> getOrdersWithCustomerDetails();

    public SignUp getByUserId(Integer id);

    public List<SignUp> getByUserID(Integer U_id);


}
