package com.igpodg.eyentelligence.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class DataSourceDashboard {
    public DataSourceDashboard() {}
    public DataSourceDashboard(PK_DataSource_Dashboard id, Boolean isActive) {
        this.id = id;
        this.isActive = isActive;
    }

    @Embeddable
    @EqualsAndHashCode
    private static class PK_DataSource_Dashboard implements Serializable {
        @ManyToOne
        @JoinColumn(name = "dataSourceId")
        private DataSource dataSource;

        @ManyToOne
        @JoinColumn(name = "dashboardId")
        private Dashboard dashboard;

        public PK_DataSource_Dashboard() {}
        public PK_DataSource_Dashboard(DataSource dataSource, Dashboard dashboard) {
            this.dataSource = dataSource;
            this.dashboard = dashboard;
        }
    }

    @Id
    private PK_DataSource_Dashboard id;

    @Column(nullable = false)
    @ColumnDefault("true")
    private Boolean isActive;
}
