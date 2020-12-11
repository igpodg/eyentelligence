package com.igpodg.eyentelligence.service;

import com.igpodg.eyentelligence.dto.TeamDto;
import com.igpodg.eyentelligence.exception.EyenNotFoundException;
import com.igpodg.eyentelligence.model.Team;
import com.igpodg.eyentelligence.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private TeamDto convertToTeamDto(Team team) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return modelMapper.map(team, TeamDto.class);
    }

    private List<TeamDto> convertToTeamDto(List<Team> teams) {
        return teams.stream()
                .map(this::convertToTeamDto)
                .collect(Collectors.toList());
    }

    private Team convertToTeam(TeamDto teamDto) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return modelMapper.map(teamDto, Team.class);
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
        //return this.teamRepository.findById(id)
        //        .orElseThrow(EyenNotFoundException::new);

        Optional<Team> team = this.teamRepository.findById(id);
        if (team.isEmpty())
            throw new EyenNotFoundException();

        return this.convertToTeamDto(team.get());
    }

    public TeamDto saveTeam(TeamDto team) {
        //Team newTeam = this.teamRepository.save(team);
        ////this.teamRepository.refresh(newTeam);
        //return newTeam;

        Team entity = this.teamRepository.save(this.convertToTeam(team));
        this.teamRepository.refresh(entity);
        return this.convertToTeamDto(entity);
    }

    public void deleteTeam(TeamDto team) {
        this.teamRepository.delete(this.convertToTeam(team));
    }
}
