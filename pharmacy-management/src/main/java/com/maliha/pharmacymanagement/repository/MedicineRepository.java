package com.maliha.pharmacymanagement.repository;

import com.maliha.pharmacymanagement.entity.MedicineEntity;
import com.maliha.pharmacymanagement.entity.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {
    Optional<MedicineEntity> findByMedicineName(String medicineName);
    List<MedicineEntity> findAllBySymptom(SymptomEntity symptomEntity);
    Optional<MedicineEntity> findBySpecialId(String specialId);
}
