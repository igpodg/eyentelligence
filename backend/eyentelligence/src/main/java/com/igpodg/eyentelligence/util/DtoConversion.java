package com.igpodg.eyentelligence.util;

import com.igpodg.eyentelligence.dto.TeamDto;
import com.igpodg.eyentelligence.dto.UserDto;
import com.igpodg.eyentelligence.model.Team;
import com.igpodg.eyentelligence.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class DtoConversion {
    // --------------- TEAM ---------------
    public static TeamDto convertToTeamDto(Team team) {
        if (team == null)
            return null;
        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        teamDto.setType(team.getType());
        teamDto.setParentTeam(convertToTeamDto(team.getParentTeam()));
        return teamDto;
    }

    public static List<TeamDto> convertToTeamDto(List<Team> teams) {
        return teams.stream()
                .map(DtoConversion::convertToTeamDto)
                .collect(Collectors.toList());
    }

    public static Team convertToTeam(TeamDto teamDto) {
        if (teamDto == null)
            return null;
        Team team = new Team();
        team.setId(teamDto.getId());
        team.setName(OptionalUtil.unwrap(teamDto.getName()));
        team.setType(OptionalUtil.unwrap(teamDto.getType()));
        team.setParentTeam(convertToTeam(OptionalUtil.unwrap(teamDto.getParentTeam())));
        return team;
    }

    public static List<Team> convertToTeam(List<TeamDto> teamDtos) {
        return teamDtos.stream()
                .map(DtoConversion::convertToTeam)
                .collect(Collectors.toList());
    }

    // --------------- USER ---------------
    public static UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPasswordHash(user.getPasswordHash());
        userDto.setTitle(user.getTitle());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setAvatarLink(user.getAvatarLink());
        return userDto;
    }

    public static List<UserDto> convertToUserDto(List<User> users) {
        return users.stream()
                .map(DtoConversion::convertToUserDto)
                .collect(Collectors.toList());
    }

    public static User convertToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(OptionalUtil.unwrap(userDto.getUsername()));
        user.setPasswordHash(OptionalUtil.unwrap(userDto.getPasswordHash()));
        user.setTitle(OptionalUtil.unwrap(userDto.getTitle()));
        user.setFirstName(OptionalUtil.unwrap(userDto.getFirstName()));
        user.setMiddleName(OptionalUtil.unwrap(userDto.getMiddleName()));
        user.setLastName(OptionalUtil.unwrap(userDto.getLastName()));
        user.setEmail(OptionalUtil.unwrap(userDto.getEmail()));
        user.setAvatarLink(OptionalUtil.unwrap(userDto.getAvatarLink()));
        return user;
    }

    public static List<User> convertToUser(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(DtoConversion::convertToUser)
                .collect(Collectors.toList());
    }
}
