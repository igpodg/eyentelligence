package com.igpodg.eyentelligence.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
public class Team {
    public Team() {}
    public Team(String name, String type, Team parentTeam) {
        this.name = name;
        this.type = type;
        this.parentTeam = parentTeam;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "char(1)", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentTeamId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team parentTeam;
}
