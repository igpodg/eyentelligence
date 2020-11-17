package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.model.User;
import com.igpodg.eyentelligence.service.TeamService;
import com.igpodg.eyentelligence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public User testController() {
        //return teamService.getFirst().orElse(null);
        return userService.getFirst().orElse(null);
    }
}
