package com.attendance.application.Controller;

import java.time.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.application.Entities.SignUp;
import com.attendance.application.Entities.User;
import com.attendance.application.Services.UserImplementation;

import jakarta.servlet.http.HttpSession;

@Controller
public class maincontroller {

    @Autowired
    private UserImplementation userImplementation;

    @GetMapping("regpage")
    public ModelAndView Registerpage() {
        return new ModelAndView("register", "user", new User());
    }

    @PostMapping("regform")
    public String registerd(@ModelAttribute("user") User user, Model model) {
        boolean status = userImplementation.InsertUser(user);
        if (status) {
            model.addAttribute("Successmsg", "User Register Successfully...");
            return "login";
        } else {
            model.addAttribute("errormsg", "User Register Failed Due To Some Error");
            return "redirect:/regpage";
        }
    }

    @GetMapping("loginpage")
    public ModelAndView loginpage() {
        return new ModelAndView("login", "user", new User());
    }

    @PostMapping("loginform")
    public String loginform(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User user2 = userImplementation.Loginuser(user.getEmail(), user.getU_pass());
        // List<SignUp> signUp2 = userImplementation.getByUserId(user2.getU_id());

        if (user2 != null) {
            session.setAttribute("UserID", user2);
            model.addAttribute("Username", user2.getU_name());
            return "redirect:/Sign_up";
        } else {
            model.addAttribute("errormsg", "Such User is not Found");
            return "login";
        }
    }

    @GetMapping("Sign_up")
    public String signup(HttpSession session, Model model) {
        User user = (User) session.getAttribute("UserID");
        List<SignUp> signUp2 = userImplementation.getByUserID(user.getU_id());
        LocalTime localTime1 = LocalTime.now();
        LocalDate localDate6 = LocalDate.now();

        int a = 0;
        if (signUp2 != null) {
            SignUp signUp3 = userImplementation.getuserByDateSignUp(user.getU_id());
            LocalTime localTime = LocalTime.now();
            LocalDate localDate1 = LocalDate.now();
            LocalDate localDate3 = signUp3.getDate();
            LocalDate date=localDate1;
            if (localDate3.equals(localDate1)) {
                a++;
            } else {
                while(date.minusDays(1).isAfter(localDate3)){
                    date=date.minusDays(1);
                    userImplementation.AbsentDetails(date, user.getU_id());
                }
            }
            if (a > 0) {
                session.setAttribute("User_ID", user);
                model.addAttribute("Username", user.getU_name());
                model.addAttribute("date", localDate1);
                model.addAttribute("time", localTime);
                return "signout";
            } else {
                session.setAttribute("User_ID", user);
                model.addAttribute("date", localDate1);
                model.addAttribute("time", localTime);
                model.addAttribute("Username", user.getU_name());
                return "signup";
            }
        } else {
            session.setAttribute("User_ID", user);
            model.addAttribute("date", localDate6);
            model.addAttribute("time", localTime1);
            model.addAttribute("Username", user.getU_name());
            return "signup";
        }

    }

    @GetMapping("/Sign-Up")
    public String SerachByID(Model model, HttpSession session) {
        User user = (User) session.getAttribute("User_ID");
        SignUp signUps = userImplementation.savAttend(user.getU_id());
        if (signUps != null) {
            model.addAttribute("Username", user.getU_name());
            model.addAttribute("user_ID", user.getU_id());
            return "Success";
        } else {
            return "signup";
        }
    }

    @GetMapping("Viewattend")
    public String listAttendent(Model model,HttpSession session) {
        User user = (User) session.getAttribute("User_ID");
        List<SignUp> attends = userImplementation.getByUserID(user.getU_id());
        model.addAttribute("Viewlist", attends);
        return "veiw";
    }

    @GetMapping("joinattend")
    public String joinattend(@RequestParam("U_id") int ID, Model model) {
        List<SignUp> signUps = userImplementation.getByUserID(ID);
        model.addAttribute("signview", signUps);
        return "joinview";
    }

    @GetMapping("Viewlist/edit/{id}")
    public String Editpage(@PathVariable("id") int id, Model model) {
        SignUp signUps = userImplementation.getByUserId(id);
        model.addAttribute("Viewlist", signUps);
        return "veiw";
    }

    @PostMapping("/Viewlist/{id}")
    public String updatepage(@PathVariable int id, @ModelAttribute("signUp") SignUp signUp) {
        userImplementation.updatAttend(signUp, id);
        return "redirect:/Viewattend";
    }

    @GetMapping("Serchby")
    public ModelAndView joinattend() {
        return new ModelAndView("signout", "signup", new SignUp());
    }

    @GetMapping("signoutpage")
    public String SignOutPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("User_ID");
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        boolean status = userImplementation.UpdateTimeByU_ID(localTime, user.getU_id(), localDate);
        if (status) {
            model.addAttribute("Username", user.getU_name());
            model.addAttribute("user_ID", user.getU_id());
            return "Success";
        } else {
            return "signout";
        }
    }

    @GetMapping("/Viewattend1")
    public String getlastattenduser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("UserID");
        SignUp signUp = userImplementation.getuserByDateSignUp(user.getU_id());
        model.addAttribute("siguplist", signUp);
        return "veiw";

    }

}

// this is the initial commit

// this is the another commit