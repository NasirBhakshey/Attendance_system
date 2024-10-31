package com.attendance.application.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.attendance.application.Entities.SignUp;
import com.attendance.application.Entities.User;
import com.attendance.application.Repository.AttendRepository;
import com.attendance.application.Repository.UserRepository;

@Service
public class UserImplementation implements UserInterfaces {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendRepository attendRepository;

    // private final LocalTime localTime= LocalTime.of(12, 57);

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
    public boolean updateUser(User user, int id) {
        User user1 = searchbyID(id);
        if (user1 != null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User Loginuser(String email, String pass) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getU_pass().equals(pass)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getlldetails() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(int id) {
        User user = searchbyID(id);
        if (user != null) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User searchbyID(int id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    // Attendant Part.....

    @Override
    public List<SignUp> getallAttend() {
        return attendRepository.findAll();
    }

    @Override
    public boolean deleteattend(int id) {
        Optional<SignUp> attend = attendRepository.findById(id);
        if (attend.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SignUp searchByattend(int id) {
        Optional<SignUp> optional = attendRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public SignUp updatAttend(SignUp attend, int id) {
        SignUp signUp = searchByattend(id);
        if (signUp != null) {
            return attendRepository.save(attend);
        } else {
            return null;
        }
    }

    @Override
    public List<SignUp> getOrdersWithCustomerDetails() {
        return attendRepository.findAttendsWithUserDetails();
    }

    @Override
    public SignUp getByUserId(Integer id) {
        Optional<SignUp> optional = attendRepository.findByUserId(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public List<SignUp> getByUserID(Integer U_id) {
        return attendRepository.findByUserID(U_id);
    }

    @Override
    public boolean UpdateTimeByU_ID(LocalTime localTime, Integer ID, LocalDate localDate1) {
        List<SignUp> signUps = getByUserID(ID);
        if (signUps != null) {
            for (SignUp signUp : signUps) {
                LocalDate localDate = signUp.getDate();
                if (localDate.equals(localDate1)) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String formatedTime = localTime.format(dateTimeFormatter);
                    signUp.setSignOut(formatedTime);
                    attendRepository.save(signUp);
                }
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public SignUp savAttend(int user_id) {

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Such User is not found::"));
        SignUp attend = new SignUp();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formatedTime = LocalTime.now().format(dateTimeFormatter);
        attend.setUser(user);
        attend.setDate(LocalDate.now());
        attend.setSignIn(formatedTime);
        return attendRepository.save(attend);

    }

    @Override
    @Scheduled(cron = "0 57 12 * * ?")
    public boolean AutoAbsentDate() {
        LocalDate localDate2 = LocalDate.now();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            boolean markabsent = attendRepository.FindBydate(localDate2).stream()
                    .anyMatch(att -> att.getUser().getU_id().equals(user.getU_id()));

            if (!markabsent) {
                SignUp signUp2 = new SignUp();
                signUp2.setDate(localDate2);
                signUp2.setSignIn(null);
                signUp2.setSignOut(null);
                signUp2.setUser(user);
                attendRepository.save(signUp2);
                return true;
            }
        }
        return true;

    }

    @Override
    public List<SignUp> getallBydate() {
        return attendRepository.FindBydate(LocalDate.now());
    }

    @Override
    public SignUp getuserByDateSignUp(int id) {
        return attendRepository.findTopByUserIdOrderByCreatedAtDesc(id);
    }

    @Override
    public SignUp AbsentDetails(LocalDate localDate, int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Such User is not found::"));
                String str="ABSENT";
        if (user != null) {
            SignUp attend = new SignUp();
            attend.setSignIn(str);
            attend.setDate(localDate);
            attend.setSignOut(str);
            attend.setUser(user);
            return attendRepository.save(attend);
        }else{
            throw new RuntimeException("");
        }

    }

}
