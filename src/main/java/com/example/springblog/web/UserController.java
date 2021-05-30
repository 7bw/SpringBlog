package com.example.springblog.web;

import com.example.springblog.User;
import com.example.springblog.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @RequestMapping(value="/register", method=GET)
    public String showRegistrationForm(){
        return "registerForm";
    }

    @RequestMapping(value="/register", method=POST)
    public String processRegistration(User user){
        userRepository.save(user);
        return "redirect:/user/" + user.getUsername();
    }
}
