package com.maliha.pharmacymanagement.repository;

import com.maliha.pharmacymanagement.entity.MedicineEntity;
import com.maliha.pharmacymanagement.entity.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SymptomRepository extends JpaRepository<SymptomEntity,Long>{
    Optional<SymptomEntity> findByName(String name);
    Boolean existsByName(String name);
}
