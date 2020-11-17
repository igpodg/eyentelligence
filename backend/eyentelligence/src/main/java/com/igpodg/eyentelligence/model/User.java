package com.igpodg.eyentelligence.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "`User`")
public class User {
    public User() {}
    public User(String username, String passwordHash, String title, String firstName, String middleName,
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 32, nullable = false)
    private String username;

    @Column(name = "`passwordHash`", nullable = false)
    private String passwordHash;

    @Column(length = 5)
    private String title;

    @Column(name = "`firstName`", nullable = false)
    private String firstName;

    @Column(name = "`middleName`")
    private String middleName;

    @Column(name = "`lastName`", nullable = false)
    private String lastName;

    private String email;

    @Column(name = "`avatarLink`")
    private String avatarLink;
}
