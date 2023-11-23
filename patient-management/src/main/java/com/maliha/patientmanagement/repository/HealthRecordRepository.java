package com.maliha.patientmanagement.repository;

import com.maliha.patientmanagement.entity.HealthRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRecordRepository extends JpaRepository<HealthRecordEntity,Integer> {
}
