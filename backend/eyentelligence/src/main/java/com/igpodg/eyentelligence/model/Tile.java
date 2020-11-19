package com.igpodg.eyentelligence.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Tile {
    public Tile() {}
    public Tile(Integer dashboardId, Short width, Short height,
                Short positionX, Short positionY, Integer visualizationId)
    {
        this.dashboardId = dashboardId;
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
        this.visualizationId = visualizationId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer dashboardId;

    @Column(nullable = false)
    private Short width;

    @Column(nullable = false)
    private Short height;

    @Column(nullable = false)
    private Short positionX;

    @Column(nullable = false)
    private Short positionY;

    @Column(nullable = false)
    private Integer visualizationId;
}
