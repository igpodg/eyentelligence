package com.igpodg.eyentelligence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Invitation {
    public Invitation() {}
    public Invitation(Integer creatorUserId, Integer targetTeamId,
                      Date createdDateTime, Date expirationDateTime)
    {
        this.creatorUserId = creatorUserId;
        this.targetTeamId = targetTeamId;
        this.createdDateTime = createdDateTime;
        this.expirationDateTime = expirationDateTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer creatorUserId;

    @Column(nullable = false)
    private Integer targetTeamId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDateTime;
}
