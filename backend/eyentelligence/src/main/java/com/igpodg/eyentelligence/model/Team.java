package com.igpodg.eyentelligence.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Team {
    public Team() {}
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

    private Integer parentTeamId;
}
