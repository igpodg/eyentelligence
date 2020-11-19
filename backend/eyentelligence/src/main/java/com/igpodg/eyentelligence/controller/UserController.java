package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.EyenBadRequestException;
import com.igpodg.eyentelligence.EyenNotFoundException;
import com.igpodg.eyentelligence.model.User;
import com.igpodg.eyentelligence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    @ExceptionHandler(EyenNotFoundException.class)
//    public void handleNotFound() {
//        System.out.println("!!!yes");
//    }
//
//    @ExceptionHandler(EyenBadRequestException.class)
//    public void handleBadRequest() {
//        System.out.println("!!!yes!!!!!!");
//    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return this.userService.getUserById(id);
    }
}
