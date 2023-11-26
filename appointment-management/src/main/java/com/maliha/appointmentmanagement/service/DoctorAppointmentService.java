package com.maliha.appointmentmanagement.service;

import com.maliha.appointmentmanagement.entity.AppointmentBookingEntity;
import com.maliha.appointmentmanagement.entity.AppointmentScheduleEntity;
import com.maliha.appointmentmanagement.entity.AppointmentSlotEntity;
import com.maliha.appointmentmanagement.model.AppointmentScheduleDTO;
import com.maliha.appointmentmanagement.networkmanager.DoctorFeignClient;
import com.maliha.appointmentmanagement.networkmanager.PatientFeignClient;
import com.maliha.appointmentmanagement.repository.AppointmentBookingRepository;
import com.maliha.appointmentmanagement.repository.AppointmentScheduleRepository;
import com.maliha.appointmentmanagement.repository.AppointmentSlotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private DoctorFeignClient doctorFeignClient;
    @Autowired
    private PatientFeignClient patientFeignClient;
    public AppointmentScheduleDTO createAppointmentSchedule(AppointmentScheduleDTO appointmentScheduleDTO) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        System.out.println(userEmail);
        Integer doctorId=doctorFeignClient.getDoctorByEmail(userEmail).getId();
        if(!appointmentScheduleRepository.existsByDoctorId(doctorId)) {
            AppointmentScheduleEntity appointmentScheduleEntity = new AppointmentScheduleEntity();
            if (!appointmentScheduleDTO.getDay1().equals(appointmentScheduleDTO.getDay2())) {

                appointmentScheduleEntity.setDoctorId(doctorId);
                appointmentScheduleEntity.setPreferedStartTime(appointmentScheduleDTO.getPreferedStartTime());
                appointmentScheduleEntity.setDay1(appointmentScheduleDTO.getDay1());
                appointmentScheduleEntity.setDay2(appointmentScheduleDTO.getDay2());

                Long count = 0l;
                for (int i = 0; i < 10; i++) {
                    AppointmentSlotEntity appointmentSlotEntity = new AppointmentSlotEntity();
                    appointmentSlotEntity.setDoctorId(doctorId);
                    appointmentSlotEntity.setDay(appointmentScheduleDTO.getDay1());
                    appointmentSlotEntity.setStartTime(appointmentScheduleDTO.getPreferedStartTime().plusMinutes(count));
                    appointmentSlotEntity.setStatus("AVAILABLE");
                    appointmentSlotRepository.save(appointmentSlotEntity);
                    count = count + 20;
                }
                count = 0l;
                for (int i = 0; i < 10; i++) {
                    AppointmentSlotEntity appointmentSlotEntity = new AppointmentSlotEntity();
                    appointmentSlotEntity.setDoctorId(doctorId);
                    appointmentSlotEntity.setDay(appointmentScheduleDTO.getDay2());
                    appointmentSlotEntity.setStartTime(appointmentScheduleDTO.getPreferedStartTime().plusMinutes(count));
                    appointmentSlotEntity.setStatus("AVAILABLE");
                    appointmentSlotRepository.save(appointmentSlotEntity);
                    count = count + 20;
                }
                return new ModelMapper().map(appointmentScheduleRepository.save(appointmentScheduleEntity), AppointmentScheduleDTO.class);
            }
        }
        throw new Exception();
    }
    public AppointmentScheduleDTO getAppointmentSchedule(Integer doctorId){
        return new ModelMapper().map(appointmentScheduleRepository.findByDoctorId(doctorId),AppointmentScheduleDTO.class);
    }
    public List<AppointmentSlotEntity> getAllAppointmentSlot(Integer doctorId) {
        return appointmentSlotRepository.findAllByDoctorId(doctorId);
    }

    public Boolean bookSlot(Long slotId)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        System.out.println(userEmail);
        Integer patientId=patientFeignClient.getPatientByEmail(userEmail).getId();
        AppointmentSlotEntity appointmentSlotEntity=appointmentSlotRepository.findById(slotId).orElseThrow(() -> new Exception());
        if (appointmentSlotRepository.existsById(slotId)&& appointmentSlotEntity.getStatus().equals("AVAILABLE")){
        AppointmentBookingEntity appointmentBookingEntity=new AppointmentBookingEntity();
        appointmentBookingEntity.setPatientId(patientId);
        appointmentBookingEntity.setCompletion(false);
        appointmentBookingEntity.setBookingDate(LocalDate.now());
            appointmentBookingEntity.setAppointmentSlotEntity(appointmentSlotRepository.findById(slotId).orElseThrow(() -> new Exception()));
            appointmentBookingRepository.save(appointmentBookingEntity);
            appointmentSlotEntity.setStatus("BOOKED");
            appointmentSlotRepository.save(appointmentSlotEntity);
            return true;
        }
        return false;
    }
    public Boolean cancelSlot(Long bookingId)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Integer patientId=patientFeignClient.getPatientByEmail(userEmail).getId();
        AppointmentSlotEntity appointmentSlotEntity=appointmentBookingRepository.findById(bookingId).orElseThrow(() -> new Exception()).getAppointmentSlotEntity();
        if (appointmentSlotEntity.getStatus().equals("BOOKED") && appointmentBookingRepository.findById(bookingId).orElseThrow(() -> new Exception()).getPatientId()==patientId && !appointmentBookingRepository.findById(bookingId).orElseThrow(() -> new Exception()).getCompletion()){
            appointmentSlotEntity.setStatus("AVAILABLE");
            appointmentBookingRepository.findById(bookingId).orElseThrow(() -> new Exception()).setCompletion(true);
            appointmentSlotRepository.save(appointmentSlotEntity);
            appointmentBookingRepository.save(appointmentBookingRepository.findById(bookingId).get());
            return true;
        }
        return false;
    }

    public Boolean completeSlotBooking(Long bookingId)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Integer doctorId=doctorFeignClient.getDoctorByEmail(userEmail).getId();
        AppointmentSlotEntity appointmentSlotEntity=appointmentBookingRepository.findById(bookingId).orElseThrow(() -> new Exception()).getAppointmentSlotEntity();
        if (appointmentSlotEntity.getStatus().equals("BOOKED") && appointmentSlotEntity.getDoctorId()==doctorId && !appointmentBookingRepository.findById(bookingId).orElseThrow(() -> new Exception()).getCompletion()){
            appointmentSlotEntity.setStatus("AVAILABLE");
            appointmentBookingRepository.findById(bookingId).orElseThrow(() -> new Exception()).setCompletion(true);
            appointmentSlotRepository.save(appointmentSlotEntity);
            appointmentBookingRepository.save(appointmentBookingRepository.findById(bookingId).get());
            return true;
        }
        return false;
    }
    public Boolean makeSlotAvailable(Long slotId)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Integer doctorId=doctorFeignClient.getDoctorByEmail(userEmail).getId();
        AppointmentSlotEntity appointmentSlotEntity=appointmentSlotRepository.findById(slotId).orElseThrow(() -> new Exception());
        if (appointmentSlotEntity.getStatus().equals("UNAVAILABLE") && appointmentSlotEntity.getDoctorId()==doctorId ){
            appointmentSlotEntity.setStatus("AVAILABLE");
            appointmentSlotRepository.save(appointmentSlotEntity);
            return true;
        }
        return false;
    }
    public Boolean makeSlotUnavailable(Long slotId)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Integer doctorId=doctorFeignClient.getDoctorByEmail(userEmail).getId();
        AppointmentSlotEntity appointmentSlotEntity=appointmentSlotRepository.findById(slotId).orElseThrow(() -> new Exception());
        if (appointmentSlotRepository.existsById(slotId)&& appointmentSlotEntity.getStatus().equals("AVAILABLE")&& appointmentSlotEntity.getDoctorId()==doctorId){
            appointmentSlotEntity.setStatus("UNAVAILABLE");
            appointmentSlotRepository.save(appointmentSlotEntity);
            return true;
        }
        return false;
    }
    public List<AppointmentBookingEntity> findAllByPatientId() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Integer patientId=patientFeignClient.getPatientByEmail(userEmail).getId();
        return appointmentBookingRepository.findAllByPatientId(patientId);
    }
    //update schedule
}
