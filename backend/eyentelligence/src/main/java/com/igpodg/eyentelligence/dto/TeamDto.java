package com.igpodg.eyentelligence.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamDto {
    public TeamDto() {}
    public TeamDto(Optional<String> name, Optional<String> type,
                   Optional<ParentTeamDto> parentTeam)
    {
        this.name = name;
        this.type = type;
        this.parentTeam = parentTeam;
    }

    @Null
    private Integer id;
    private Optional<@NotNull @NotBlank String> name;
    private Optional<@NotNull @Pattern(regexp = "^[TO]{1}$") String> type;
    private Optional<ParentTeamDto> parentTeam;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public void setType(String type) {
        this.type = Optional.ofNullable(type);
    }

    public void setParentTeam(ParentTeamDto parentTeam) {
        this.parentTeam = Optional.ofNullable(parentTeam);
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "id=" + this.id +
                ", name=" + this.name +
                ", type=" + this.type +
                ", parentTeam=" + this.parentTeam +
                '}';
    }
}
