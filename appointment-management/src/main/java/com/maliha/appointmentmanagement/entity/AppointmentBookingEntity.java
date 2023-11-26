package com.maliha.appointmentmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="appointment_booking")
public class AppointmentBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer patientId;
    private LocalDate bookingDate;
    private Boolean completion;
    @ManyToOne
    private AppointmentSlotEntity appointmentSlotEntity;
}
