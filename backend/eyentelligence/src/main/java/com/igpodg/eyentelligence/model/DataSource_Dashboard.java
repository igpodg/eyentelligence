package com.igpodg.eyentelligence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class DataSource_Dashboard {
    public DataSource_Dashboard() {}
    public DataSource_Dashboard(PK_DataSource_Dashboard id, Boolean isActive) {
        this.id = id;
        this.isActive = isActive;
    }

    @Embeddable
    @EqualsAndHashCode
    private static class PK_DataSource_Dashboard implements Serializable {
        private Integer dataSourceId;
        private Integer dashboardId;

        public PK_DataSource_Dashboard() {}
        public PK_DataSource_Dashboard(Integer dataSourceId, Integer dashboardId) {
            this.dataSourceId = dataSourceId;
            this.dashboardId = dashboardId;
        }
    }

    @Id
    private PK_DataSource_Dashboard id;

    @Column(nullable = false)
    private Boolean isActive;
}
