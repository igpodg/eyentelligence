package com.igpodg.eyentelligence.dto;

import lombok.Data;

@Data
public class UserDto {
    public UserDto() {}
    public UserDto(String username, String passwordHash, String title, String firstName, String middleName,
                   String lastName, String email, String avatarLink)
    {
        this.username = username;
        this.passwordHash = passwordHash;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.avatarLink = avatarLink;
    }

    private Integer id;
    private String username;
    private String passwordHash;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String avatarLink;
}
