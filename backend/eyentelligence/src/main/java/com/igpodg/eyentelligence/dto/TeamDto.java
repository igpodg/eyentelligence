package com.igpodg.eyentelligence.dto;

import lombok.Data;

@Data
public class TeamDto {
    public TeamDto() {}
    public TeamDto(String name, String type, TeamDto parentTeam) {
        this.name = name;
        this.type = type;
        this.parentTeam = parentTeam;
    }

    private Integer id;
    private String name;
    private String type;
    private TeamDto parentTeam;
}
