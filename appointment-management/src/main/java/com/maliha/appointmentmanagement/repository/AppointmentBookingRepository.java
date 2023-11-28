package com.maliha.appointmentmanagement.repository;

import com.maliha.appointmentmanagement.entity.AppointmentBookingEntity;
import com.maliha.appointmentmanagement.entity.AppointmentSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentBookingRepository extends JpaRepository<AppointmentBookingEntity,Long> {
    List<AppointmentBookingEntity> findAllByAppointmentSlotEntity(AppointmentSlotEntity appointmentSlotEntity);
    List<AppointmentBookingEntity> findAllByPatientId(Integer patientId);
    Boolean existsByPatientIdAndCompletion(Integer patientId,Boolean completion);
    AppointmentBookingEntity findByAppointmentSlotEntityAndCompletion(AppointmentSlotEntity appointmentSlotEntity, Boolean completion);
}
