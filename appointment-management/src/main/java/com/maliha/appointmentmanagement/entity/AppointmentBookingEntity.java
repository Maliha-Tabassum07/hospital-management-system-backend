package com.maliha.appointmentmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name ="appointment_booking")
public class AppointmentBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientId;
    private LocalTime presentDate;
    @ManyToOne
    private AppointmentSlotEntity appointmentSlotEntity;


}
