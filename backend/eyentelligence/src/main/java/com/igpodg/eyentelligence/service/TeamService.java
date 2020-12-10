package com.igpodg.eyentelligence.service;

import com.igpodg.eyentelligence.EyenNotFoundException;
import com.igpodg.eyentelligence.model.Team;
import com.igpodg.eyentelligence.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return this.teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return this.teamRepository.findById(id).orElseThrow(EyenNotFoundException::new);
    }

    public Team saveTeam(Team team) {
        Team newTeam = this.teamRepository.save(team);
        //this.teamRepository.refresh(newTeam);
        return newTeam;
    }

    public Boolean doesTeamExistById(Long id) {
        return this.teamRepository.findById(id).isPresent();
    }

    public void deleteTeam(Team team) {
        this.teamRepository.delete(team);
    }
}
