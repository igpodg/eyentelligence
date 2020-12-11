package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.dto.TeamDto;
import com.igpodg.eyentelligence.exception.EyenBadRequestException;
import com.igpodg.eyentelligence.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
    public List<TeamDto> getAllTeams() {
        return this.teamService.getAllTeams();
    }

    @GetMapping("/team/{id}")
    public TeamDto getTeam(@PathVariable int id) {
        return this.teamService.getTeamById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/team")
    public TeamDto addUser(@RequestBody TeamDto team, HttpServletResponse response) {
        if (team.getName() == null || team.getType() == null)
            throw new EyenBadRequestException();
        if (!team.getType().equals("T") && !team.getType().equals("O"))
            throw new EyenBadRequestException();

        //team.setId(null);
        team = this.teamService.saveTeam(team);
        response.setHeader("Location", "/team/" + team.getId());
        return team;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/team/{id}")
    public TeamDto updateUser(@PathVariable int id, @RequestBody String body, HttpServletResponse response)
            throws JSONException
    {
        JSONObject requestTeam = new JSONObject(body);
        TeamDto teamToUpdate = this.teamService.getTeamById(id);
        if (requestTeam.has("name"))
            teamToUpdate.setName(requestTeam.getString("name"));
        if (requestTeam.has("type")) {
            String type = requestTeam.getString("type");
            if (!type.equals("T") && !type.equals("O"))
                throw new EyenBadRequestException();
            teamToUpdate.setType(type);
        }

        response.setHeader("Location", "/team/" + id);
        return this.teamService.saveTeam(teamToUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable int id) {
        TeamDto teamToDelete = this.teamService.getTeamById(id);
        this.teamService.deleteTeam(teamToDelete);
    }
}
