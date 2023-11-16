package com.maliha.appointmentmanagement.repository;

import com.maliha.appointmentmanagement.entity.AppointmentScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentScheduleRepository extends JpaRepository<AppointmentScheduleEntity,Long> {
}
