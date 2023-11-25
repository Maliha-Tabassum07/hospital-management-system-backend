package com.maliha.patientmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "health-record")
public class HealthRecordEntity {

    @Id
    private Integer id;
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
