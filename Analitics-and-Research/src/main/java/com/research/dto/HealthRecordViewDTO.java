package com.research.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthRecordViewDTO {
    private String specialId;
    private Double heightCm;
    private Double weightKg;
    private Double bloodSugarLevel;
    private String bloodPressure;
    private String bloodGroup;
    private Integer heartRate;
    private Double bmi;
    private LocalDate date;
    private Integer sleepHour;
    private Boolean smoke;
    private Boolean allergy;
}
