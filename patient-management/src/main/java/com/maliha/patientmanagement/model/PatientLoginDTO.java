package com.maliha.patientmanagement.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientLoginDTO {
    @NotBlank(message = "Email cannot be null")
    private String email;
    @NotBlank(message = "Password cannot be null")
    private String password;
}
