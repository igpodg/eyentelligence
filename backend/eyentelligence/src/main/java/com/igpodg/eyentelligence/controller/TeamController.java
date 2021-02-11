package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.dto.TeamDto;
import com.igpodg.eyentelligence.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/team")
    public List<TeamDto> getAllTeams() {
        return this.teamService.getAllTeams();
    }

    @GetMapping("/team/{id}")
    public TeamDto getTeam(@PathVariable int id) {
        return this.teamService.getTeamById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/team")
    public TeamDto addTeam(@RequestBody @Valid TeamDto team, HttpServletResponse response) {
        team = this.teamService.saveTeam(team);
        response.setHeader("Location", "/team/" + team.getId());
        return team;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/team/{id}")
    public TeamDto updateTeam(@PathVariable int id, @RequestBody @Valid TeamDto team,
                              HttpServletResponse response)
    {
        response.setHeader("Location", "/team/" + id);
        return this.teamService.mergeTeam(id, team);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable int id) {
        this.teamService.deleteTeamById(id);
    }
}
