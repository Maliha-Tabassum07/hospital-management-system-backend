package com.maliha.pharmacymanagement.repository;

import com.maliha.pharmacymanagement.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<MedicineEntity, String> {
}
