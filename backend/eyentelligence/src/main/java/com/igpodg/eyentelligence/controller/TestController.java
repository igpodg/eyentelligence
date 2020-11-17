package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.model.Team;
import com.igpodg.eyentelligence.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/test")
    public Team testController() {
        return teamService.getFirst().orElse(null);
    }
}
