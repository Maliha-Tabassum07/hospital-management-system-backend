package com.maliha.appointmentmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name ="appointment_slot")
public class AppointmentSlotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorId;
    private String status;
    private String day;
    private LocalTime startTime;
    public AppointmentSlotEntity() {
    }
    public AppointmentSlotEntity(Long id, String doctorId, String status, String day, LocalTime startTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.status = status;
        this.day = day;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
