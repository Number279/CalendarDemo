package com.tts.CalendarDemo.controller;

import com.tts.CalendarDemo.service.EventService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Controller
@ControllerAdvice
public class AdminController {
    @Autowired
    EventService eventService;

    @GetMapping("/admin")
    public String admin(){ return "admin";}
}
