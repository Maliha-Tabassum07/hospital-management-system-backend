package com.maliha.doctoradminmanagement.repository;



import com.maliha.doctoradminmanagement.entity.DepartmentEntity;
import com.maliha.doctoradminmanagement.entity.DesignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesignationRepository extends JpaRepository<DesignationEntity,Long> {
    Boolean existsByName(String name);
    Optional<DesignationEntity> findByName(String name);
}
