package com.attendance.application.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    // LocalTime time = signUp2.getSignIn();
    // // Integer time2 = time.getHour() * 10000 + time.getMinute() * 100 +
    // // time.getSecond();

    // LocalTime time1 = LocalTime.now();
    // // Integer time3 = time1.getHour() * 10000 + time1.getMinute() * 100 +
    // // time1.getSecond();

    // LocalDate date = signUp2.getDate();

    // LocalDate date3 = LocalDate.now();

    // if ((date.equals(date3)) && (!time.equals(time1))) {
    // model.addAttribute("Username", user2.getU_name());
    // return "signout";
    // } else {
    // model.addAttribute("errormsg", "Such User is not Found");
    // return "redirect:/Sign_up";
    // }
    // } else if (user2 != null && signUp2 == null) {
    // SignUp signUp3 = userImplementation.savAttend(user2.getU_id());
    // if (signUp3 != null) {
    // model.addAttribute("signUp3", user2.getU_name());
    // return "redirect:/Sign_up";
    // } else {
    // model.addAttribute("signUp3", user2.getU_name());
    // return "";
    // }

    // else if (user2!=null && (date2 == date4) && time2 != time3) {
    // return"signout";
    // }else

    @GetMapping("Sign_up")
    public String signup(HttpSession session, Model model) {
        User user = (User) session.getAttribute("UserID");
        SignUp signUp2 = userImplementation.getByUserId(user.getU_id());
        if (signUp2 != null) {
            LocalDate localDate=signUp2.getDate();
            LocalDate localDate2=LocalDate.now();
            if(localDate.equals(localDate2)){
            System.out.println(localDate+" "+localDate2);
            System.out.println(signUp2.getUser().getU_id());
            System.out.println(user.getU_id());
            model.addAttribute("user_ID", signUp2.getUser().getU_name());
            return "Success";
            }else{
            session.setAttribute("User_ID", user);
            model.addAttribute("Username", signUp2.getUser().getU_name());
            return "signup";
            }
        } else {
            session.setAttribute("User_ID", user);
            model.addAttribute("Username", user.getU_name());
            return "signup";
        }
    }

    @GetMapping("/Sign-Up")
    public String SerachByID(Model model, HttpSession session) {
        User user = (User) session.getAttribute("User_ID");
        SignUp signUps = userImplementation.savAttend(user.getU_id());
        if (signUps != null) {
            model.addAttribute("user_ID", user.getU_name());
            return "Success";
        } else {
            return "signup";
        }

    }

    @GetMapping("Viewattend")
    public String listAttendent(Model model) {
        List<SignUp> attends = userImplementation.getallAttend();
        model.addAttribute("Viewlist", attends);
        return "veiw";
    }

    // @GetMapping("joinattend")
    // public String joinattend(@PathVariable("id") int id, Model model) {
    // List<SignUp> signUps = userImplementation.getByUserId(null);
    // model.addAttribute("signview", signUps);
    // return "joinview";
    // }

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
}

// this is the initial commit

// this is the another commit