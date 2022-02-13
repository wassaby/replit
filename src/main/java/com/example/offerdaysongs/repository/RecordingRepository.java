package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long>, JpaSpecificationExecutor<Recording> {
}
