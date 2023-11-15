package com.maliha.doctoradminmanagement.repository;


import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer> {
    Optional<DoctorEntity> findByEmail(String email);
}
