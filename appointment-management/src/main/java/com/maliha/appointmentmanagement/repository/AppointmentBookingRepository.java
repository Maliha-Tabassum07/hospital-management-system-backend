package com.maliha.appointmentmanagement.repository;

import com.maliha.appointmentmanagement.entity.AppointmentBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentBookingRepository extends JpaRepository<AppointmentBookingEntity,Long> {
}
