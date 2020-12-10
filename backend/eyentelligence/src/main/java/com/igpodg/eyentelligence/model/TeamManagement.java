package com.igpodg.eyentelligence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class TeamManagement {
    public TeamManagement() {}
    public TeamManagement(PK_TeamManagement id, String role) {
        this.id = id;
        this.role = role;
    }

    @Embeddable
    @EqualsAndHashCode
    private static class PK_TeamManagement implements Serializable {
        @ManyToOne
        @JoinColumn(name = "userId")
        private User user;

        @ManyToOne
        @JoinColumn(name = "teamId")
        private Team team;

        public PK_TeamManagement() {}
        public PK_TeamManagement(User user, Team team) {
            this.user = user;
            this.team = team;
        }
    }

    @Id
    private PK_TeamManagement id;

    @Column(columnDefinition = "char(1)", nullable = false)
    private String role;
}
