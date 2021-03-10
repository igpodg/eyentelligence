package com.igpodg.eyentelligence.service;

import com.igpodg.eyentelligence.dto.DashboardDto;
import com.igpodg.eyentelligence.dto.TeamDto;
import com.igpodg.eyentelligence.exception.EyenUserException;
import com.igpodg.eyentelligence.model.Dashboard;
import com.igpodg.eyentelligence.repository.DashboardRepository;
import com.igpodg.eyentelligence.repository.TeamRepository;
import com.igpodg.eyentelligence.util.DtoConversion;
import com.igpodg.eyentelligence.util.OptionalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {
    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private TeamRepository teamRepository;

    private final static String ERR_DASHBOARD_NOT_FOUND =
            "The specified dashboard has not been found.";
    private final static String ERR_DASHBOARD_CREATE_NOT_ENOUGH_FIELDS =
            "Not enough required fields to create a new dashboard.";
    private final static String ERR_TEAM_OBJ_INVALID_ID =
            "The team object has a missing/invalid ID.";
    private final static String ERR_TEAM_INVALID_ID =
            "The ID that the team is referring to is invalid.";

    public Boolean doesTeamExistById(Integer id) {
        return this.teamRepository.findById(id).isPresent();
    }

    public List<DashboardDto> getAllDashboards() {
        return DtoConversion.convertToDashboardDto(this.dashboardRepository.findAll());
    }

    public DashboardDto getDashboardById(Integer id) {
        Optional<Dashboard> dashboard = this.dashboardRepository.findById(id);
        if (dashboard.isEmpty())
            throw new EyenUserException(ERR_DASHBOARD_NOT_FOUND);

        return DtoConversion.convertToDashboardDto(dashboard.get());
    }

    public DashboardDto saveDashboard(DashboardDto dashboard) {
        // fields "team" and "name" must exist when adding a new Dashboard
        //   (this doesn't apply to mergeDashboard)
        if (dashboard.getTeam() == null || dashboard.getName() == null) {
            throw new EyenUserException(ERR_DASHBOARD_CREATE_NOT_ENOUGH_FIELDS);
        }

        if (dashboard.getTeam().isPresent()) {
            Integer teamId = dashboard.getTeam().get().getId();
            if (teamId == null)
                throw new EyenUserException(ERR_TEAM_OBJ_INVALID_ID);
            else if (!this.doesTeamExistById(teamId))
                throw new EyenUserException(ERR_TEAM_INVALID_ID);
        }

        Long currentDateTime = new Date().getTime() / 1000L;
        dashboard.setCreatedDateTime(currentDateTime);
        dashboard.setLastModifiedDateTime(currentDateTime);

        Dashboard entity = this.dashboardRepository.save(DtoConversion.convertToDashboard(dashboard));
        this.dashboardRepository.refresh(entity);
        return DtoConversion.convertToDashboardDto(entity);
    }

    public DashboardDto mergeDashboard(Integer id, DashboardDto request) {
        DashboardDto dashboard = this.getDashboardById(id);
        if (OptionalUtil.isValid(request.getTeam())) {
            TeamDto team = request.getTeam().get();
            if (team.getId() == null)
                throw new EyenUserException(ERR_TEAM_OBJ_INVALID_ID);
            else if (!this.doesTeamExistById(team.getId()))
                throw new EyenUserException(ERR_TEAM_INVALID_ID);

            dashboard.setTeam(team);
        }
        if (OptionalUtil.isValid(request.getName()))
            dashboard.setName(request.getName().get());
        dashboard.setLastModifiedDateTime(new Date().getTime() / 1000L);

        Dashboard entity = this.dashboardRepository.save(DtoConversion.convertToDashboard(dashboard));
        this.dashboardRepository.refresh(entity);
        return DtoConversion.convertToDashboardDto(entity);
    }

    public void deleteDashboardById(Integer id) {
        DashboardDto dashboard = this.getDashboardById(id);
        this.dashboardRepository.delete(DtoConversion.convertToDashboard(dashboard));
    }
}
