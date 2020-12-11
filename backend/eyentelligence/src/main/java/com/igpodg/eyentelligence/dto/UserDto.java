package com.igpodg.eyentelligence.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Optional;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    public UserDto() {}
    public UserDto(Optional<String> username, Optional<String> passwordHash,
                   Optional<String> title, Optional<String> firstName,
                   Optional<String> middleName, Optional<String> lastName,
                   Optional<String> email, Optional<String> avatarLink)
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

    @Null
    private Integer id;
    private Optional<String> username;
    private Optional<String> passwordHash;
    private Optional<String> title;
    @NotNull
    private Optional<@NotNull @NotBlank String> firstName;
    private Optional<String> middleName;
    @NotNull
    private Optional<@NotNull @NotBlank String> lastName;
    private Optional<String> email;
    private Optional<String> avatarLink;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = Optional.ofNullable(username);
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = Optional.ofNullable(passwordHash);
    }

    public void setTitle(String title) {
        this.title = Optional.ofNullable(title);
    }

    public void setFirstName(String firstName) {
        this.firstName = Optional.ofNullable(firstName);
    }

    public void setMiddleName(String middleName) {
        this.middleName = Optional.ofNullable(middleName);
    }

    public void setLastName(String lastName) {
        this.lastName = Optional.ofNullable(lastName);
    }

    public void setEmail(String email) {
        this.email = Optional.ofNullable(email);
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = Optional.ofNullable(avatarLink);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + this.id +
                ", username=" + this.username +
                ", passwordHash=" + this.passwordHash +
                ", title=" + this.title +
                ", firstName=" + this.firstName +
                ", middleName=" + this.middleName +
                ", lastName=" + this.lastName +
                ", email=" + this.email +
                ", avatarLink=" + this.avatarLink +
                '}';
    }
}
