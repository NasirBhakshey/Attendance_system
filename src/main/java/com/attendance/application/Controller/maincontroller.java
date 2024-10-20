package com.attendance.application.Controller;

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
    public String loginform(@ModelAttribute("user") User user, Model model) {
        User user2 = userImplementation.Loginuser(user.getEmail(), user.getU_pass());
        // SignUp signUp2 = userImplementation.getByUserId(user2.getU_id());

        if (user2 != null) {
            model.addAttribute("Username",user2.getU_name());
            return "signup";
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
        else {
            model.addAttribute("errormsg", "Such User is not Found");
            return "login";
        }
    }

    // else if (user2!=null && (date2 == date4) && time2 != time3) {
    // return"signout";
    // }else

    @GetMapping("Sign_up")
    public String signup(@ModelAttribute User user2, Model model) {
        List<SignUp> signUp2 = userImplementation.getOrdersWithCustomerDetails();
        if (signUp2 != null) {
            return "Success";
        } else {
            return null;
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
    //     List<SignUp> signUps = userImplementation.getByUserId(null);
    //     model.addAttribute("signview", signUps);
    //     return "joinview";
    // }


    @GetMapping("Viewlist/edit/{id}")
    public String Editpage(@PathVariable("id") int id, Model model) {
        List<SignUp> signUps = userImplementation.getByUserId(id);
        model.addAttribute("Viewlist", signUps);
        return "veiw";
    }

    @PostMapping("/Viewlist/{id}")
    public String updatepage(@PathVariable int id,@ModelAttribute("signUp") SignUp signUp)
    {
       userImplementation.updatAttend(signUp, id);
        return "redirect:/Viewattend";
    }

    @PostMapping("Serchby")
    public String SerachByID(@RequestParam("id") int id, Model model) {
        List<SignUp> signUps = userImplementation.getByUserId(id);
        if(signUps!=null){
            model.addAttribute("Viewlist", signUps);
            return "veiw";
        }else{
            return "signout";
        }
       
    }
}

// this is the initial commit

// this is the another commit