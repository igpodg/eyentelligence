package com.igpodg.eyentelligence.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Optional;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardDto {
    public DashboardDto() {}
    public DashboardDto(Optional<TeamDto> team,
                        Optional<String> name, Optional<String> type,
                        Optional<Long> createdDateTime,
                        Optional<Long> lastModifiedDateTime)
    {
        this.team = team;
        this.name = name;
        this.createdDateTime = createdDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    @Null
    private Integer id;
    private Optional<@NotNull TeamDto> team;
    private Optional<@NotNull @NotBlank String> name;
    private Optional<Long> createdDateTime;
    private Optional<Long> lastModifiedDateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTeam(TeamDto team) {
        this.team = Optional.ofNullable(team);
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public void setCreatedDateTime(Long createdDateTime) {
        this.createdDateTime = Optional.ofNullable(createdDateTime);
    }

    public void setLastModifiedDateTime(Long lastModifiedDateTime) {
        this.lastModifiedDateTime = Optional.ofNullable(lastModifiedDateTime);
    }

    @Override
    public String toString() {
        return "DashboardDto{" +
                "id=" + this.id +
                ", team=" + this.team +
                ", name=" + this.name +
                ", createdDateTime=" + this.createdDateTime +
                ", lastModifiedDateTime=" + this.lastModifiedDateTime +
                '}';
    }
}
