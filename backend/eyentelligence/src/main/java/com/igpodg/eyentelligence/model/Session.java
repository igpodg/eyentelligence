package com.igpodg.eyentelligence.model;

import com.vladmihalcea.hibernate.type.basic.Inet;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLInetType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@TypeDef(name = "ipv4", typeClass = PostgreSQLInetType.class, defaultForType = Inet.class)
public class Session {
    public Session() {}
    public Session(User user,
                   Date issueDateTime, String issueClientUA, Inet issueClientIP,
                   Date lastDateTime, String lastClientUA, Inet lastClientIP)
    {
        this.user = user;
        this.issueDateTime = issueDateTime;
        this.issueClientUA = issueClientUA;
        this.issueClientIP = issueClientIP;
        this.lastDateTime = lastDateTime;
        this.lastClientUA = lastClientUA;
        this.lastClientIP = lastClientIP;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDateTime;

    @Column(length = 2048, nullable = false)
    private String issueClientUA;

    @Column(columnDefinition = "cidr", nullable = false)
    private Inet issueClientIP;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDateTime;

    @Column(length = 2048, nullable = false)
    private String lastClientUA;

    @Column(columnDefinition = "cidr", nullable = false)
    private Inet lastClientIP;
}
