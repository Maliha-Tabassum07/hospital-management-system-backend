package com.maliha.doctormanagement.repository;

import com.maliha.doctormanagement.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,String> {
    Optional<DoctorEntity> findByEmail(String email);
}
