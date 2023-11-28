package com.maliha.appointmentmanagement.repository;

import com.maliha.appointmentmanagement.entity.AppointmentScheduleEntity;
import com.maliha.appointmentmanagement.entity.AppointmentSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlotEntity,Long> {
    List<AppointmentSlotEntity> findAllByDoctorId(Integer doctorId);
    List<AppointmentSlotEntity> findAllByDoctorIdAndStatus(Integer doctorId,String status);

}
