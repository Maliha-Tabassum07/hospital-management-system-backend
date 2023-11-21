package com.maliha.patientmanagement.repository;

import com.maliha.patientmanagement.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity,Integer> {
    Optional<PatientEntity> findByEmail(String email);
}
