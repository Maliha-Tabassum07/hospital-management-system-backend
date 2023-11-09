package com.maliha.doctormanagement.repository;


import com.maliha.doctormanagement.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
