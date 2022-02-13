package com.example.offerdaysongs.model;

import liquibase.pro.packaged.E;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
public class Recording {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String version;
    ZonedDateTime releaseTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    Singer singer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "record_copyrights",
            joinColumns = {@JoinColumn(name = "record_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "copyright_id", referencedColumnName = "id")}
    )
    private List<Copyright> copyrights;
}
