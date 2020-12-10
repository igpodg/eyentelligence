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
        private Integer userId;
        private Integer teamId;

        public PK_TeamManagement() {}
        public PK_TeamManagement(Integer userId, Integer teamId) {
            this.userId = userId;
            this.teamId = teamId;
        }
    }

    @Id
    private PK_TeamManagement id;

    @Column(columnDefinition = "char(1)", nullable = false)
    private String role;
}
