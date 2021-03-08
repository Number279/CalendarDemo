package com.tts.CalendarDemo.controller;

import com.tts.CalendarDemo.model.Event;
import com.tts.CalendarDemo.model.User;
import com.tts.CalendarDemo.service.EventService;
import com.tts.CalendarDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@ControllerAdvice
public class CalendarController {
    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @ModelAttribute("loggedInUser")
    public User loggedInUser() {return userService.getLoggedInUser();}

    @ModelAttribute("calendar")
    public Map<Event, Integer> calendar() {
        User user = loggedInUser();
        if(user == null) return null;
        System.out.println("Getting calendar");
        return user.getCalendar();
    }

    @ModelAttribute("list")
    public List<Double> list() {return new ArrayList<>();
    }

    @PostMapping("/calendar")
    public String showCalendar() {return "calendar";}

    @PatchMapping("/calendar")
    public String addToCalendar(@RequestParam long id) {
        Event e = eventService.findById(id);
        setQuantity(e, calendar().getOrDefault(e, 0) + 1);
        return "calendar";
    }

    @PostMapping("/calendar/{id}")
    public String removeFromCalendar(@PathVariable long id) {
        Event e = eventService.findById(id);
        setQuantity(e, 0);
        return "calendar";
    }

    private void setQuantity(Event e, int quantity){
        if(quantity > 0)
            calendar().put(e, quantity);
        else calendar().remove(e);

        userService.updateCalendar(calendar());
    }
}
