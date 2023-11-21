package com.maliha.patientmanagement.model;


import com.maliha.patientmanagement.entity.PatientEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {
    private PatientEntity patientEntity;
    private String token;

}
