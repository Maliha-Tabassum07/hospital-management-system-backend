package com.maliha.appointmentmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name ="appointment_schedule")
public class AppointmentScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorId;
    private LocalTime prefferedStartTime;
    private String day1;
    private String day2;
    private Integer room;

    public AppointmentScheduleEntity() {
    }

    public AppointmentScheduleEntity(Long id, String doctorId, LocalTime prefferedStartTime, String day1, String day2, Integer room) {
        this.id = id;
        this.doctorId = doctorId;
        this.prefferedStartTime = prefferedStartTime;
        this.day1 = day1;
        this.day2 = day2;
        this.room = room;
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

    public LocalTime getPrefferedStartTime() {
        return prefferedStartTime;
    }

    public void setPrefferedStartTime(LocalTime prefferedStartTime) {
        this.prefferedStartTime = prefferedStartTime;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }
}
