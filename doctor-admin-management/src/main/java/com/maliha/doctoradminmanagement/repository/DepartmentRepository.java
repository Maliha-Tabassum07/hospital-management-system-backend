package com.maliha.doctoradminmanagement.repository;



import com.maliha.doctoradminmanagement.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    Optional<DepartmentEntity> findByName(String name);
    Boolean existsByName(String name);
}
