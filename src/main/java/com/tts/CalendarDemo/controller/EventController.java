package com.tts.CalendarDemo.controller;

import com.tts.CalendarDemo.model.Event;
import com.tts.CalendarDemo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping("/event/{id}")
    public String show(@PathVariable int id, Model model){
        Event event = eventService.findById(id);
        model.addAttribute(event);
        return "event";
    }

    // TODO: Either implement admin controls or remote these methods

    @RequestMapping(value = "/event", method = {RequestMethod.POST, RequestMethod.PUT})
    public String createOrUpdate(@Valid Event event){
        eventService.save(event);
        return "redirect:/event/" + event.getId();
    }

    @GetMapping("/addEvent")
    public String addEvent(Model model){
        Event newEvent = new Event();
        model.addAttribute("event", newEvent);
        return "addEvent";
    }
}
