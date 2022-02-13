package com.example.offerdaysongs.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Copyright {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @CreationTimestamp
    private LocalDate createdAt;

    private LocalDate expirationDate;
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "copyright_company_id_fk"))
    private Company company;

}
