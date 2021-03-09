package com.tts.CalendarDemo.repository;

import com.tts.CalendarDemo.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();
    Event findById(long id);
    List<Event> findByCategory(String category);
    List<Event> findByCreatedAt(Date createdAt);
//    List<Event> findByEventDate(LocalDate eventDate);
    List<Event> findByDateTime(LocalDateTime dateTime);
    List<Event> findByCreatedAtAndCategory(Date createdAt, String category);

//    @Query("SELECT DISTINCT e.category FROM Event e")
//    List<String> findDistinctCategories();
//
//    @Query("SELECT DISTINCT e.createdat FROM Event e")
//    List<String> findDistinctCreatedAts();
}
