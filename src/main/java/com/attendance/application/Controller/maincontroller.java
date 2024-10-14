package com.attendance.application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.application.Entities.Attend;
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
            return"redirect:/regage";
        }
    }

    @GetMapping("loginpage")
    public ModelAndView loginpage()
    {
        return new ModelAndView("login","user",new User());
    }

    @PostMapping("loginform")
    public String loginform(@ModelAttribute("user") User user, Model model)
    {
        User user2=userImplementation.Loginuser(user.getEmail(), user.getU_pass());
        if(user2 != null)
        {
            model.addAttribute("Username", user2.getU_name());
            return"signup";
        }else{
            model.addAttribute("errormsg", "Such User is not Found");
            return"login";
        }
    }

    @PostMapping("serachpage")
    public String insertAttend(@RequestParam("a_name") String a_name,@RequestParam("user_id") int user_id ){
        userImplementation.savAttend(a_name, user_id);
        return"signout";
    }

    @GetMapping("Viewattend")
    public String listAttendent(Model model){
        List<Attend> attends=userImplementation.getallAttend();
        model.addAttribute("Viewlist", attends);
        return "veiw";
    }


}

// this is the initial commit

// this is the another commit