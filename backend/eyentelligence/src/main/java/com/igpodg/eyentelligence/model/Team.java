package com.igpodg.eyentelligence.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "`Team`")
public class Team {
    public Team(String name, String type, Integer parentTeamId) {
        this.name = name;
        this.type = type;
        this.parentTeamId = parentTeamId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "CHAR(1)", nullable = false)
    private String type;

    @Column(name = "`parentTeamId`")
    private Integer parentTeamId;
}
