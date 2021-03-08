package com.tts.CalendarDemo.controller;

import com.tts.CalendarDemo.model.Event;
import com.tts.CalendarDemo.service.EventService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Data
@Controller
@ControllerAdvice
public class MainController {
    @Autowired
    EventService eventService;

    @GetMapping
    public String main(){ return "main";}

    @ModelAttribute("events")
    public List<Event> events() {return eventService.findAll();}

//    @ModelAttribute("categories")
//    public List<String> categories() {return eventService.findDistinctCategories();}
//
//    @ModelAttribute("createdats")
//    public List<String> createdats() {return eventService.findDistinctCreatedAts();}

    @GetMapping("/filter")
    public String filter(@RequestParam(required = false)Date createdAt,
                         @RequestParam(required = false) String category, Model model){
        List<Event> filtered = eventService.findByCreatedAtAndOrCategory(createdAt, category);
        model.addAttribute("events", filtered);
        return "main";
    }
}
