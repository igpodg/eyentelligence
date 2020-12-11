package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.dto.UserDto;
import com.igpodg.eyentelligence.exception.EyenBadRequestException;
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
    public List<UserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable int id) {
        return this.userService.getUserById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserDto addUser(@RequestBody UserDto user, HttpServletResponse response) {
        user.setUsername("user");
        user.setPasswordHash("useruseruser");

        if (user.getFirstName() == null || user.getLastName() == null)
            throw new EyenBadRequestException();

        user = this.userService.saveUser(user);
        response.setHeader("Location", "/user/" + user.getId());
        return user;
    }
}
