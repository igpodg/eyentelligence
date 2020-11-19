package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.EyenBadRequestException;
import com.igpodg.eyentelligence.model.Team;
import com.igpodg.eyentelligence.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/team")
    public Team addUser(@RequestBody Team team, HttpServletResponse response) {
        if (team.getName() == null || team.getType() == null)
            throw new EyenBadRequestException();
        if (!team.getType().equals("T") && !team.getType().equals("O"))
            throw new EyenBadRequestException();

        team = this.teamService.saveTeam(team);
        response.setHeader("Location", "/team/" + team.getId());
        return team;
    }
}
