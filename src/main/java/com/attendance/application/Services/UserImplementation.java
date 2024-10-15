package com.attendance.application.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.application.Entities.SignUp;
import com.attendance.application.Entities.User;
import com.attendance.application.Repository.AttendRepository;
import com.attendance.application.Repository.UserRepository;

@Service
public class UserImplementation implements UserInterfaces{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendRepository attendRepository;

    @Override
    public boolean InsertUser(User user) {
       try {
        userRepository.save(user);
        return true;
       } catch (Exception e) {
        e.printStackTrace();
        return false;
       }
    }

    @Override
    public boolean updateUser(User user,int id) {
        User user1=searchbyID(id);
        if(user1 !=null)
        {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User Loginuser(String email, String pass) {
        User user=userRepository.findByEmail(email);
        if(user !=null && user.getU_pass().equals(pass))
        {
            return user;
        }
        return null;
    }

    @Override
    public List<User> getlldetails() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(int id) {
        User user=searchbyID(id);
        if(user !=null)
        {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User searchbyID(int id) {
        Optional<User> optional=userRepository.findById(id);
        if(optional.isPresent())
        {
            return optional.get();
        }else{
            return null;
        }
    }

    //Attendant Part.....

    @Override
    public SignUp savAttend(int user_id) {
        
        User user=userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("Such is not found::"));

        SignUp attend=new SignUp();
        attend.setUser(user);
        return attendRepository.save(attend);

    }

    @Override
    public List<SignUp> getallAttend() {
        return attendRepository.findAll();
    }

    @Override
    public boolean deleteattend(int id) {
        Optional<SignUp> attend=attendRepository.findById(id);
        if(attend.isPresent()){
            userRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<SignUp> searchByattend(int id) {
       return attendRepository.findById(id);
    }

    @Override
    public SignUp updatAttend(SignUp attend, int id) {
        Optional<SignUp> attend2=searchByattend(id);
        if(attend2.isPresent())
        {
            return attendRepository.save(attend);
        }else{
            return null;
        }
    }

    

}
