package com.igpodg.eyentelligence.service;

import com.igpodg.eyentelligence.dto.TeamDto;
import com.igpodg.eyentelligence.exception.EyenUserException;
import com.igpodg.eyentelligence.model.Team;
import com.igpodg.eyentelligence.repository.TeamRepository;
import com.igpodg.eyentelligence.util.DtoConversion;
import com.igpodg.eyentelligence.util.OptionalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    private final static String ERR_TEAM_NOT_FOUND =
            "The specified team has not been found.";
    private final static String ERR_TEAM_CREATE_NOT_ENOUGH_FIELDS =
            "Not enough required fields to create a new team.";
    private final static String ERR_PARENT_TEAM_OBJ_INVALID_ID =
            "The parent team object has a missing/invalid ID.";
    private final static String ERR_PARENT_TEAM_INVALID_ID =
            "The ID that the parent team is referring to is invalid.";
    private final static String ERR_PARENT_TEAM_SELF_REFERENCE =
            "The ID of the parent team cannot refer to itself.";

    public Boolean doesTeamExistById(Integer id) {
        return this.teamRepository.findById(id).isPresent();
    }

    public List<TeamDto> getAllTeams() {
        return DtoConversion.convertToTeamDto(this.teamRepository.findAll());
    }

    public TeamDto getTeamById(Integer id) {
        Optional<Team> team = this.teamRepository.findById(id);
        if (team.isEmpty())
            throw new EyenUserException(ERR_TEAM_NOT_FOUND);

        return DtoConversion.convertToTeamDto(team.get());
    }

    public TeamDto saveTeam(TeamDto team) {
        // fields "name" and "type" must exist when adding a new Team
        //   (this doesn't apply to mergeTeam)
        if (team.getName() == null || team.getType() == null) {
            throw new EyenUserException(ERR_TEAM_CREATE_NOT_ENOUGH_FIELDS);
        }

        if (OptionalUtil.isValid(team.getParentTeam())) {
            Integer parentTeamId = team.getParentTeam().get().getId();
            if (parentTeamId == null)
                throw new EyenUserException(ERR_PARENT_TEAM_OBJ_INVALID_ID);
            else if (!this.doesTeamExistById(parentTeamId))
                throw new EyenUserException(ERR_PARENT_TEAM_INVALID_ID);
        }

        Team entity = this.teamRepository.save(DtoConversion.convertToTeam(team));
        this.teamRepository.refresh(entity);
        return DtoConversion.convertToTeamDto(entity);
    }

    public TeamDto mergeTeam(Integer id, TeamDto request) {
        TeamDto team = this.getTeamById(id);
        if (OptionalUtil.isValid(request.getName()))
            team.setName(request.getName().get());
        if (OptionalUtil.isValid(request.getType()))
            team.setType(request.getType().get());
        if (OptionalUtil.isValid(request.getParentTeam())) {
            TeamDto parentTeam = request.getParentTeam().get();
            if (parentTeam.getId() == null)
                throw new EyenUserException(ERR_PARENT_TEAM_OBJ_INVALID_ID);
            else if (!this.doesTeamExistById(parentTeam.getId()))
                throw new EyenUserException(ERR_PARENT_TEAM_INVALID_ID);

            if (parentTeam.getId().equals(id))
                throw new EyenUserException(ERR_PARENT_TEAM_SELF_REFERENCE);

            team.setParentTeam(parentTeam);
        }

        Team entity = this.teamRepository.save(DtoConversion.convertToTeam(team));
        this.teamRepository.refresh(entity);
        return DtoConversion.convertToTeamDto(entity);
    }

    public void deleteTeamById(Integer id) {
        TeamDto team = this.getTeamById(id);
        this.teamRepository.delete(DtoConversion.convertToTeam(team));
    }
}
