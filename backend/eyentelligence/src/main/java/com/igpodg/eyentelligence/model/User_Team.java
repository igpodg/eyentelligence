package com.igpodg.eyentelligence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class User_Team {
    public User_Team() {}
    public User_Team(PK_User_Team id, String role, String title,
                     String firstName, String middleName, String lastName,
                     String email, String avatarLink)
    {
        this.id = id;
        this.role = role;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.avatarLink = avatarLink;
    }

    @Embeddable
    @EqualsAndHashCode
    private static class PK_User_Team implements Serializable {
        private Integer userId;
        private Integer teamId;

        public PK_User_Team() {}
        public PK_User_Team(Integer userId, Integer teamId) {
            this.userId = userId;
            this.teamId = teamId;
        }
    }

    @Id
    private PK_User_Team id;

    @Column(columnDefinition = "CHAR(1)", nullable = false)
    private String role;

    @Column(length = 5)
    private String title;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String avatarLink;
}
