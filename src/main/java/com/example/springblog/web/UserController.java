package com.example.springblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value="/register", method=GET)
    public String showRegistrationForm(){
        return "registerForm";
    }
}
