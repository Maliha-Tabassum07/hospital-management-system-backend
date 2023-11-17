package com.maliha.appointmentmanagement.repository;

import com.maliha.appointmentmanagement.entity.AppointmentScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentScheduleRepository extends JpaRepository<AppointmentScheduleEntity,Long> {
    Optional<AppointmentScheduleEntity> findByDoctorId(Integer doctorId);

}
