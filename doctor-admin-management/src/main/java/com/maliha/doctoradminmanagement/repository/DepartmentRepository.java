package com.maliha.doctoradminmanagement.repository;



import com.maliha.doctoradminmanagement.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
