package com.igpodg.eyentelligence.service;

import com.igpodg.eyentelligence.dto.TeamDto;
import com.igpodg.eyentelligence.exception.EyenBadRequestException;
import com.igpodg.eyentelligence.exception.EyenNotFoundException;
import com.igpodg.eyentelligence.model.Team;
import com.igpodg.eyentelligence.repository.TeamRepository;
import com.igpodg.eyentelligence.util.OptionalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    private TeamDto convertToTeamDto(Team team) {
        if (team == null)
            return null;
        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        teamDto.setType(team.getType());
        teamDto.setParentTeam(convertToTeamDto(team.getParentTeam()));
        return teamDto;
    }

    private List<TeamDto> convertToTeamDto(List<Team> teams) {
        return teams.stream()
                .map(this::convertToTeamDto)
                .collect(Collectors.toList());
    }

    private Team convertToTeam(TeamDto teamDto) {
        if (teamDto == null)
            return null;
        Team team = new Team();
        team.setId(teamDto.getId());
        team.setName(OptionalUtil.unwrap(teamDto.getName()));
        team.setType(OptionalUtil.unwrap(teamDto.getType()));
        team.setParentTeam(this.convertToTeam(OptionalUtil.unwrap(teamDto.getParentTeam())));
        return team;
    }

    private List<Team> convertToTeam(List<TeamDto> teamDtos) {
        return teamDtos.stream()
                .map(this::convertToTeam)
                .collect(Collectors.toList());
    }

    public Boolean doesTeamExistById(Integer id) {
        return this.teamRepository.findById(id).isPresent();
    }

    public List<TeamDto> getAllTeams() {
        return this.convertToTeamDto(this.teamRepository.findAll());
    }

    public TeamDto getTeamById(Integer id) {
        Optional<Team> team = this.teamRepository.findById(id);
        if (team.isEmpty())
            throw new EyenNotFoundException();

        return this.convertToTeamDto(team.get());
    }

    public TeamDto saveTeam(TeamDto team) {
        if (team.getName() == null)
            throw new EyenBadRequestException();
        if (team.getType() == null)
            throw new EyenBadRequestException();

        Team entity = this.teamRepository.save(this.convertToTeam(team));
        this.teamRepository.refresh(entity);
        return this.convertToTeamDto(entity);
    }

    public void deleteTeam(TeamDto team) {
        this.teamRepository.delete(this.convertToTeam(team));
    }

    public TeamDto mergeTeam(Integer id, TeamDto request) {
        TeamDto team = this.getTeamById(id);
        if (OptionalUtil.isValid(request.getName()))
            team.setName(request.getName().get());
        if (OptionalUtil.isValid(request.getType()))
            team.setType(request.getType().get());
        if (OptionalUtil.isValid(request.getParentTeam()))
            team.setParentTeam(request.getParentTeam().get());

        Team entity = this.teamRepository.save(this.convertToTeam(team));
        this.teamRepository.refresh(entity);
        return this.convertToTeamDto(entity);
    }
}
