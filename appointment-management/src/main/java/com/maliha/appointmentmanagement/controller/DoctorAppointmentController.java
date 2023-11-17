package com.maliha.appointmentmanagement.controller;

import com.maliha.appointmentmanagement.model.AppointmentScheduleDTO;
import com.maliha.appointmentmanagement.service.DoctorAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class DoctorAppointmentController {
    @Autowired
    private DoctorAppointmentService doctorAppointmentService;

    @PostMapping("/{doctorId}/schedule/create")
    public ResponseEntity<?> createAppointmentSchedule(@PathVariable Integer doctorId, @RequestBody AppointmentScheduleDTO appointmentScheduleDTO) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.createAppointmentSchedule(doctorId,appointmentScheduleDTO), HttpStatus.ACCEPTED);
    }
    @GetMapping("/{doctorId}/schedule/get")
    public ResponseEntity<?> getAppointmentSchedule(@PathVariable Integer id) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.getAppointmentSchedule(id),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{doctorId}/slot/get")
    public ResponseEntity<?> getAppointmentSlot(@PathVariable Integer doctorId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.getAllAppointmentSlot(doctorId),HttpStatus.ACCEPTED);
    }
    @PostMapping("{patientId}/{slotId}/book")
    public ResponseEntity<?> bookAppointmentSlot(@PathVariable("patientId") Integer patientId,@PathVariable("slotId") Long slotId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.bookSlot(patientId,slotId),HttpStatus.ACCEPTED);
    }
    @PutMapping("{patientId}/{bookingId}/cancel")
    public ResponseEntity<?> cancelAppointmentSlot(@PathVariable("patientId") Integer patientId,@PathVariable("bookingId") Long bookingId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.cancelSlot(patientId,bookingId),HttpStatus.ACCEPTED);
    }

}
