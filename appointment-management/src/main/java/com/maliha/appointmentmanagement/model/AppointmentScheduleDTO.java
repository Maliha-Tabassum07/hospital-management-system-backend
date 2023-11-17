package com.maliha.appointmentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentScheduleDTO {
    private LocalTime preferedStartTime;
    private String day1;
    private String day2;
}
