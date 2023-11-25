package com.maliha.pharmacymanagement.repository;

import com.maliha.pharmacymanagement.entity.MedicineEntity;
import com.maliha.pharmacymanagement.entity.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer> {
    Optional<MedicineEntity> findByMedicineName(String medicineName);
    List<MedicineEntity> findAllBySymptom(SymptomEntity symptomEntity);
    Optional<MedicineEntity> findBySpecialId(String specialId);
    Boolean existsBySpecialId(String specialId);
    Boolean existsByGenericName(String genericName);
    Boolean existsByMedicineName(String medicineName);
    List<MedicineEntity> findAllByGenericName(String genericName);
    List<MedicineEntity> findAllByMedicineName(String medicineName);
    List<MedicineEntity> findAllBySpecialId(String specialId);
}
