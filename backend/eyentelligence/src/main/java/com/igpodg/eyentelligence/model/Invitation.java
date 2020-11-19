package com.igpodg.eyentelligence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Invitation {
    public Invitation() {}
    public Invitation(User creatorUser, Team targetTeam,
                      Date createdDateTime, Date expirationDateTime)
    {
        this.creatorUser = creatorUser;
        this.targetTeam = targetTeam;
        this.createdDateTime = createdDateTime;
        this.expirationDateTime = expirationDateTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creatorUserId", nullable = false)
    private User creatorUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "targetTeamId", nullable = false)
    private Team targetTeam;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDateTime;
}
