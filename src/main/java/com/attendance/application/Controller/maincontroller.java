package com.attendance.application.Controller;

import java.sql.ResultSet;
import java.time.LocalTime;
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

@Controller
public class maincontroller {

    @Autowired
    private UserImplementation userImplementation;

    @GetMapping("regpage")
    public ModelAndView Registerpage()
    {
        return new ModelAndView("register","user",new User());
    }

    @PostMapping("regform")
    public String registerd(@ModelAttribute("user") User user, Model model){
        boolean status=userImplementation.InsertUser(user);
        if(status)
        {
            model.addAttribute("Successmsg", "User Register Successfully...");
            return"login";
        }else{
            model.addAttribute("errormsg", "User Register Failed Due To Some Error");
            return"redirect:/regpage";
        }
    }

    @GetMapping("loginpage")
    public ModelAndView loginpage()
    {
        return new ModelAndView("login","user",new User());
    }

    @PostMapping("loginform")
    public String loginform(@ModelAttribute("user") User user, Model model,@ModelAttribute SignUp signUp)
    {
        User user2=userImplementation.Loginuser(user.getEmail(), user.getU_pass());
        SignUp signUp2=userImplementation.savAttend(user2.getU_id());
        LocalTime time2=signUp2.getTime();
        int hour1=time2.getHour();
        LocalTime time=LocalTime.now();
        int hour=time.getHour();

        if(user2 != null && hour1==hour && signUp2!=null)
        {
            model.addAttribute("Username", user2.getU_name());
            return"signup";
        }else if(user2 != null && hour1==hour && signUp2!=null){
            model.addAttribute("Username", user2.getU_name());
            return"signout";
        }else{
            model.addAttribute("errormsg", "Such User is not Found");
            return"login";
        }
    }

    @GetMapping("Viewattend")
    public String listAttendent(Model model){
        List<SignUp> attends=userImplementation.getallAttend();
        model.addAttribute("Viewlist", attends);
        return "veiw";
    }


}

// this is the initial commit

// this is the another commit