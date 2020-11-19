package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.model.Team;
import com.igpodg.eyentelligence.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/team")
    public List<Team> getAllTeams() {
        return this.teamService.getAllTeams();
    }

    @GetMapping("/team/{id}")
    public Team getTeam(@PathVariable int id) {
        return this.teamService.getTeamById(id);
    }
}
