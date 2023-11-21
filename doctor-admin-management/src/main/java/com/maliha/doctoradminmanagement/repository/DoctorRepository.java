package com.maliha.doctoradminmanagement.repository;


import com.maliha.doctoradminmanagement.entity.DepartmentEntity;
import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Integer> {
    Optional<DoctorEntity> findByEmail(String email);
    List<DoctorEntity> findAllByRole(String role);
    Optional<DoctorEntity> findByName(String name);
    Boolean existsByName(String name);
    Optional<DoctorEntity> findBySpecialId(String specialId);
    List<DoctorEntity> findAllByDepartmentId(Long departmentId);
}
