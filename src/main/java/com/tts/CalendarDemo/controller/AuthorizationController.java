package com.tts.CalendarDemo.controller;

import com.tts.CalendarDemo.model.User;
import com.tts.CalendarDemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthorizationController {

    private UserService userService;

    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/signup")
    public String createNewUser(@Valid User user,
                                BindingResult bindingResult,
                                Model model) {

        User userExists = userService.findByUsername(user.getUsername());

        if (userExists != null) {
            bindingResult.rejectValue("username",
                    "error.user",
                    "Username is already taken");
        }

        if (!bindingResult.hasErrors()) {
            userService.saveNewUser(user);
            model.addAttribute("success",
                    "Sign up successful!");
            model.addAttribute("user", new User());
        }

        return "registration";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
