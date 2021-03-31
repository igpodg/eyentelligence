package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.dto.TeamDto;
import com.igpodg.eyentelligence.exception.EyenUserException;
import com.igpodg.eyentelligence.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static com.igpodg.eyentelligence.exception.ExceptionMessages.ERR_PARENT_TEAM_OBJ_INVALID_ID;
import static com.igpodg.eyentelligence.exception.ExceptionMessages.ERR_PARENT_TEAM_SELF_REFERENCE;

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

    private void validateTeamDto(Integer id, TeamDto request) {
        // if the Optional is null, set to Optional CONTAINING null
        if (request.getParentTeam() == null) {
            request.setParentTeam(null);
        }

        if (request.getParentTeam().isPresent()) {
            Integer parentTeamId = request.getParentTeam().get().getId();

            if (parentTeamId == null)
                throw new EyenUserException(ERR_PARENT_TEAM_OBJ_INVALID_ID);

            if (parentTeamId.equals(id))
                throw new EyenUserException(ERR_PARENT_TEAM_SELF_REFERENCE);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/team/{id}")
    public TeamDto updateTeam(@PathVariable int id, @RequestBody @Valid TeamDto team,
                              HttpServletResponse response)
    {
        response.setHeader("Location", "/team/" + id);
        this.validateTeamDto(id, team);
        return this.teamService.mergeTeam(id, team);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/team/{id}")
    public void deleteTeam(@PathVariable int id) {
        this.teamService.deleteTeamById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/team/{id}/parent/{pid}")
    public TeamDto deleteParentTeam(@PathVariable int id, @PathVariable int pid) {
        return this.teamService.deleteParentTeamById(id, pid);
    }
}
