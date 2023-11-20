package com.maliha.patientmanagement.repository;

import com.maliha.patientmanagement.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity,Integer> {
}
