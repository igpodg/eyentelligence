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

import static com.igpodg.eyentelligence.exception.ExceptionMessages.*;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public Boolean doesTeamExistById(Integer id) {
        return this.teamRepository.findById(id).isPresent();
    }

    public List<TeamDto> getAllTeams() {
        return DtoConversion.convertToTeamDto(
                this.teamRepository.findAllByOrderByIdAsc());
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
        // first work on entity level
        Optional<Team> teamAsOptional = this.teamRepository.findById(id);
        Team teamAsModel = teamAsOptional.orElseThrow(() ->
                new EyenUserException(ERR_TEAM_NOT_FOUND));
        // merge "not null" fields
        if (OptionalUtil.isValid(request.getName()))
            teamAsModel.setName(request.getName().get());
        if (OptionalUtil.isValid(request.getType()))
            teamAsModel.setType(request.getType().get());

        // if request contains a parent team
        if (request.getParentTeam().isPresent()) {
            Optional<Team> parentTeamAsOptional =
                    this.teamRepository.findById(request.getParentTeam().get().getId());
            Team parentTeamAsModel = parentTeamAsOptional.orElseThrow(() ->
                    new EyenUserException(ERR_PARENT_TEAM_NOT_FOUND));

            // IF new parent team is different than previous THEN update it
            Integer newParentId = parentTeamAsModel.getId();
            Team existingParent = teamAsModel.getParentTeam();
            if (existingParent == null || !newParentId.equals(existingParent.getId())) {
                // self-reference
                if (newParentId.equals(id))
                    throw new EyenUserException(ERR_PARENT_TEAM_SELF_REFERENCE);

                teamAsModel.setParentTeam(parentTeamAsModel);
            }
        }

        Team entity = this.teamRepository.save(teamAsModel);
        //this.teamRepository.refresh(entity);
        return DtoConversion.convertToTeamDto(entity);
    }

    public void deleteTeamById(Integer id) {
        TeamDto team = this.getTeamById(id);
        this.teamRepository.delete(DtoConversion.convertToTeam(team));
    }

    public TeamDto deleteParentTeamById(Integer teamId, int parentTeamId) {
        Optional<Team> teamAsOptional = this.teamRepository.findById(teamId);
        Team team = teamAsOptional.orElseThrow(() -> new EyenUserException(ERR_TEAM_NOT_FOUND));

        if (team.getParentTeam() == null ||
                !(team.getParentTeam().getId().equals(parentTeamId)))
        {
            throw new EyenUserException(ERR_PARENT_TEAM_INVALID_ID);
        }

        team.setParentTeam(null);
        this.teamRepository.save(team);
        return DtoConversion.convertToTeamDto(team);
    }
}
