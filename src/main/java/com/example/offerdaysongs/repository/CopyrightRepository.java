package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.Copyright;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CopyrightRepository extends JpaRepository<Copyright, Long>, JpaSpecificationExecutor<Copyright> {
    static Specification<Copyright> betweenDates(LocalDate from, LocalDate to) {
        return (item, cq, cb) -> cb.between(item.get("expirationDate"), from, to);
    }
}
