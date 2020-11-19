package com.igpodg.eyentelligence.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tile {
    public Tile() {}
    public Tile(Dashboard dashboard, Short width, Short height,
                Short positionX, Short positionY, Visualization visualization)
    {
        this.dashboard = dashboard;
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
        this.visualization = visualization;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dashboardId", nullable = false)
    private Dashboard dashboard;

    @Column(nullable = false)
    private Short width;

    @Column(nullable = false)
    private Short height;

    @Column(nullable = false)
    private Short positionX;

    @Column(nullable = false)
    private Short positionY;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "visualizationId", nullable = false)
    private Visualization visualization;
}
