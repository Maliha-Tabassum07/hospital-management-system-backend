package com.maliha.appointmentmanagement.service;

import com.maliha.appointmentmanagement.entity.AppointmentScheduleEntity;
import com.maliha.appointmentmanagement.model.AppointmentScheduleDTO;
import com.maliha.appointmentmanagement.repository.AppointmentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorAppointmentService {
    @Autowired
    private AppointmentScheduleRepository appointmentScheduleRepository;
    public AppointmentScheduleEntity createAppointmentSchedule(AppointmentScheduleDTO appointmentScheduleDTO){
        AppointmentScheduleEntity appointmentScheduleEntity=new AppointmentScheduleEntity();
        appointmentScheduleEntity.setDoctorId(appointmentScheduleDTO.getDoctorId());
        appointmentScheduleEntity.setPreferedStartTime(appointmentScheduleDTO.getPreferedStartTime());
        appointmentScheduleEntity.setDay1(appointmentScheduleDTO.getDay1());
        appointmentScheduleEntity.setDay2(appointmentScheduleDTO.getDay2());
        appointmentScheduleEntity.setRoom(appointmentScheduleDTO.getRoom());
        return appointmentScheduleRepository.save(appointmentScheduleEntity);

    }
}
