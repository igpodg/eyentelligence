package com.igpodg.eyentelligence.controller;

import com.igpodg.eyentelligence.dto.DashboardDto;
import com.igpodg.eyentelligence.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(produces = "application/json;charset=utf-8")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public List<DashboardDto> getAllDashboards() {
        return this.dashboardService.getAllDashboards();
    }

    @GetMapping("/dashboard/{id}")
    public DashboardDto getDashboard(@PathVariable int id) {
        return this.dashboardService.getDashboardById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/dashboard")
    public DashboardDto addDashboard(@RequestBody @Valid DashboardDto dashboard,
                                     HttpServletResponse response)
    {
        dashboard = this.dashboardService.saveDashboard(dashboard);
        response.setHeader("Location", "/dashboard/" + dashboard.getId());
        return dashboard;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/dashboard/{id}")
    public DashboardDto updateDashboard(@PathVariable int id,
                                        @RequestBody @Valid DashboardDto dashboard,
                                        HttpServletResponse response)
    {
        response.setHeader("Location", "/dashboard/" + id);
        return this.dashboardService.mergeDashboard(id, dashboard);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/dashboard/{id}")
    public void deleteDashboard(@PathVariable int id) {
        this.dashboardService.deleteDashboardById(id);
    }
}
