package com.tts.CalendarDemo.service;

import com.tts.CalendarDemo.model.Event;
import com.tts.CalendarDemo.model.Role;
import com.tts.CalendarDemo.model.User;
import com.tts.CalendarDemo.repository.RoleRepository;
import com.tts.CalendarDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }


    public void save(User user) {
        userRepository.save(user);
    }

    public User saveNewUser(User user) {
        // encodes our user's password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        // set the user as active
        user.setActive(1);
        // finds user role in database
        Role userRole = roleRepository.findByRole("USER");
        // sets the roles of our user
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        // saves new user
        return userRepository.save(user);
    }

    public User getLoggedInUser(){
        return findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public void updateCalendar(Map<Event, Integer> calendar){
        User user = getLoggedInUser();
        user.setCalendar(calendar);
        save(user);
    }
}
