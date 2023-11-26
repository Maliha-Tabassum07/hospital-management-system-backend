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

    @PostMapping("/schedule/create")
    public ResponseEntity<?> createAppointmentSchedule( @RequestBody AppointmentScheduleDTO appointmentScheduleDTO) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.createAppointmentSchedule(appointmentScheduleDTO), HttpStatus.ACCEPTED);
    }
    @GetMapping("/{doctorId}/schedule/get")
    public ResponseEntity<?> getAppointmentSchedule(@PathVariable Integer doctorId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.getAppointmentSchedule(doctorId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{doctorId}/slot/get")
    public ResponseEntity<?> getAppointmentSlot(@PathVariable Integer doctorId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.getAllAppointmentSlot(doctorId),HttpStatus.ACCEPTED);
    }
    @PostMapping("/{slotId}/book")
    public ResponseEntity<?> bookAppointmentSlot(@PathVariable("slotId") Long slotId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.bookSlot(slotId),HttpStatus.ACCEPTED);
    }
    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<?> cancelAppointmentSlot(@PathVariable("bookingId") Long bookingId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.cancelSlot(bookingId),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{bookingId}/makeAvailable/byBookingId")
    public ResponseEntity<?> makeAvailable(@PathVariable Long bookingId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.completeSlotBooking(bookingId),HttpStatus.ACCEPTED);
    }
    @PutMapping("/{slotId}/makeAvailable/bySlotId")
    public ResponseEntity<?> makeAvailableBySlotId(@PathVariable Long slotId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.makeSlotAvailable(slotId),HttpStatus.ACCEPTED);
    }
    @PutMapping("/{slotId}/makeUnavailable")
    public ResponseEntity<?> makeUnavailable(@PathVariable Long slotId) throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.makeSlotUnavailable(slotId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/get/appointmentBooked")
    public ResponseEntity<?> appointmentBookedByPatientId() throws Exception{
        return new ResponseEntity<>(doctorAppointmentService.findAllByPatientId(),HttpStatus.ACCEPTED);
    }
}
