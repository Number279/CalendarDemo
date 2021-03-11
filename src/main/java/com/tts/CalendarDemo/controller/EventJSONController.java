package com.tts.CalendarDemo.controller;

import com.tts.CalendarDemo.model.Event;
import com.tts.CalendarDemo.repository.EventRepository;
import com.tts.CalendarDemo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api
@RequestMapping("/json")
public class EventJSONController {
    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;

    @ApiOperation(value = "Get all events.", response = Event.class,
            responseContainer = "List")
    @GetMapping("/all")
    public Iterable<Event> findAll() { return eventService.findAll();}

}
