package com.maliha.appointmentmanagement.service;

import com.maliha.appointmentmanagement.entity.AppointmentBookingEntity;
import com.maliha.appointmentmanagement.entity.AppointmentScheduleEntity;
import com.maliha.appointmentmanagement.entity.AppointmentSlotEntity;
import com.maliha.appointmentmanagement.model.AppointmentScheduleDTO;
import com.maliha.appointmentmanagement.repository.AppointmentBookingRepository;
import com.maliha.appointmentmanagement.repository.AppointmentScheduleRepository;
import com.maliha.appointmentmanagement.repository.AppointmentSlotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorAppointmentService {
    @Autowired
    private AppointmentScheduleRepository appointmentScheduleRepository;
    @Autowired
    private AppointmentSlotRepository appointmentSlotRepository;
    @Autowired
    private AppointmentBookingRepository appointmentBookingRepository;
    public AppointmentScheduleDTO createAppointmentSchedule(Integer doctorId,AppointmentScheduleDTO appointmentScheduleDTO) throws Exception{
        AppointmentScheduleEntity appointmentScheduleEntity=new AppointmentScheduleEntity();
        if(appointmentScheduleDTO.getDay1()==appointmentScheduleDTO.getDay2()) {
            appointmentScheduleEntity.setDoctorId(doctorId);
            appointmentScheduleEntity.setPreferedStartTime(appointmentScheduleDTO.getPreferedStartTime());
            appointmentScheduleEntity.setDay1(appointmentScheduleDTO.getDay1());
            appointmentScheduleEntity.setDay2(appointmentScheduleDTO.getDay2());
            Integer count=0;
            for (int i=0;i<10;i++){
                AppointmentSlotEntity appointmentSlotEntity=new AppointmentSlotEntity();
                appointmentSlotEntity.setDoctorId(doctorId);
                appointmentSlotEntity.setDay(appointmentScheduleDTO.getDay1());
                appointmentSlotEntity.setStartTime(appointmentSlotEntity.getStartTime().plusMinutes(count));
                appointmentSlotEntity.setStatus("AVAILABLE");
                appointmentSlotRepository.save(appointmentSlotEntity);
                count=count+20;
            }
            count=0;
            for (int i=0;i<10;i++){
                AppointmentSlotEntity appointmentSlotEntity=new AppointmentSlotEntity();
                appointmentSlotEntity.setDoctorId(doctorId);
                appointmentSlotEntity.setDay(appointmentScheduleDTO.getDay2());
                appointmentSlotEntity.setStartTime(appointmentSlotEntity.getStartTime().plusMinutes(count));
                appointmentSlotEntity.setStatus("AVAILABLE");
                appointmentSlotRepository.save(appointmentSlotEntity);
                count=count+20;
            }
            return new ModelMapper().map(appointmentScheduleRepository.save(appointmentScheduleEntity),AppointmentScheduleDTO.class);
        }
        throw new Exception();
    }
    public AppointmentScheduleDTO getAppointmentSchedule(Integer doctorId){
        return new ModelMapper().map(appointmentScheduleRepository.findByDoctorId(doctorId),AppointmentScheduleDTO.class);
    }
    public List<AppointmentSlotEntity> getAllAppointmentSlot(Integer doctorId) {
        return appointmentSlotRepository.findAllByDoctorId(doctorId);
    }
    public Boolean bookSlot(Integer patientId, Long slotId)throws Exception{
        AppointmentSlotEntity appointmentSlotEntity=appointmentSlotRepository.findById(slotId).orElseThrow(() -> new Exception());
        if (appointmentSlotRepository.existsById(slotId)&& appointmentSlotEntity.getStatus()=="AVAILABLE"){
        AppointmentBookingEntity appointmentBookingEntity=new AppointmentBookingEntity();
        appointmentBookingEntity.setPatientId(patientId);
        appointmentBookingEntity.setBookingDate(LocalDate.now());
            appointmentBookingEntity.setAppointmentSlotEntity(appointmentSlotRepository.findById(slotId).orElseThrow(() -> new Exception()));
            appointmentBookingRepository.save(appointmentBookingEntity);
            appointmentSlotEntity.setStatus("BOOKED");
            appointmentSlotRepository.save(appointmentSlotEntity);
            return true;
        }
        return false;
    }
    public Boolean cancelSlot(Integer patientId, Long bookingId)throws Exception{
        AppointmentSlotEntity appointmentSlotEntity=appointmentBookingRepository.findById(bookingId).orElseThrow(() -> new Exception()).getAppointmentSlotEntity();
        if (appointmentSlotEntity.getStatus()=="BOOKED" && appointmentBookingRepository.findById(bookingId).orElseThrow(() -> new Exception()).getPatientId()==patientId){
            appointmentSlotEntity.setStatus("AVAILABLE");
            appointmentSlotRepository.save(appointmentSlotEntity);
            return true;
        }
        return false;
    }
}
