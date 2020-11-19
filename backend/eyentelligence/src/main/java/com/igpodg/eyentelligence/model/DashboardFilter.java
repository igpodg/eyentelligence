package com.igpodg.eyentelligence.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Data
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class DashboardFilter {
    public DashboardFilter() {}
    public DashboardFilter(Short priority, String name, Integer dashboardId, Object settings) {
        this.priority = priority;
        this.name = name;
        this.dashboardId = dashboardId;
        this.settings = settings;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Short priority;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer dashboardId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private Object settings;
}
