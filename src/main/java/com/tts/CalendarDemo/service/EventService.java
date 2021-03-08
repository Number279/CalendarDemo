package com.tts.CalendarDemo.service;


import com.tts.CalendarDemo.model.Event;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface EventService {
    List<Event> findAll();
    Event findById(long id);
//    List<String> findDistinctCategories();
//    List<String> findDistinctCreatedAts();
    void save(Event event);
    void deleteById(long id);
    List<Event> findByCreatedAtAndOrCategory(Date createdAt, String category);
}
