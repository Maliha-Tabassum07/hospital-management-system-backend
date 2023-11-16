package com.maliha.appointmentmanagement.repository;

import com.maliha.appointmentmanagement.entity.AppointmentSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlotEntity,Long> {
}
