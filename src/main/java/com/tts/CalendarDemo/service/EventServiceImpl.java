package com.tts.CalendarDemo.service;

import com.tts.CalendarDemo.model.Event;
import com.tts.CalendarDemo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(long id) {
        return eventRepository.findById(id);
    }

//    @Override
//    public List<String> findDistinctCategories() {
//        return eventRepository.findDistinctCategories();
//    }
//
//    @Override
//    public List<String> findDistinctCreatedAts() {
//        return eventRepository.findDistinctCreatedAts();
//    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> findByCreatedAtAndOrCategory(Date createdAt, String category) {
        if(category == null && createdAt == null) return eventRepository.findAll();
        else if(category == null) return eventRepository.findByCreatedAt(createdAt);
        else if(createdAt==null) return eventRepository.findByCategory(category);
        else return eventRepository.findByCreatedAtAndCategory(createdAt, category);
    }

//    @Override
//    public List<Event> findByEventDate(LocalDate eventDate){
//        if(eventDate == null) return eventRepository.findAll();
//        else return eventRepository.findByEventDate(eventDate);
//    }

    @Override
    public List<Event> findByDateTime(LocalDateTime dateTime){
        if(dateTime == null) return eventRepository.findAll();
        else return eventRepository.findByDateTime(dateTime);
    }
}
