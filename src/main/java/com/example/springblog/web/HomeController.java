package com.example.springblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class HomeController {

    @RequestMapping(value="/", method = GET)
    public String home(Model model) {
        // To do: change later
        return "home";
    }

}
