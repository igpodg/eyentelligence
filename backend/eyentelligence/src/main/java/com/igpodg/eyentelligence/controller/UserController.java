package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.EyenBadRequestException;
import com.igpodg.eyentelligence.model.User;
import com.igpodg.eyentelligence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public User getUser(@PathVariable long id) {
        return this.userService.getUserById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public User addUser(@RequestBody User user, HttpServletResponse response) {
        user.setUsername("user");
        user.setPasswordHash("useruseruser");

        if (user.getFirstName() == null || user.getLastName() == null)
            throw new EyenBadRequestException();

        user = this.userService.saveUser(user);
        response.setHeader("Location", "/user/" + user.getId());
        return user;
    }
}
