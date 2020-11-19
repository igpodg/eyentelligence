package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.model.User;
import com.igpodg.eyentelligence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return this.userService.getUserById(id);
    }

    @PostMapping(value = "/user")
    public void addUser(@RequestBody User user) {
        user.setUsername("user");
        user.setPasswordHash("useruseruser");
        System.out.println(user.getFirstName());
    }
}
