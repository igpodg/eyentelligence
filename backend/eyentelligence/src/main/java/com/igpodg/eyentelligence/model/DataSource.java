package com.igpodg.eyentelligence.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Data
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class DataSource {
    public DataSource() {}
    public DataSource(String type, String name, Object settings) {
        this.type = type;
        this.name = name;
        this.settings = settings;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "char(1)", nullable = false)
    private String type;

    @Column(nullable = false)
    private String name;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private Object settings;
}
