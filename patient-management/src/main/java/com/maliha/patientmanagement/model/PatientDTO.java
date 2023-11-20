package com.maliha.patientmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private String specialId;
    private String name;
    @NotBlank(message = "Your Id cannot be null")
    @Email(message = "Email must be a valid email address")
    private String email;
    @NotBlank(message = "Password cannot be null")
    private String password;
    private String role;
    private String address;
    private String phone;
    private String gender;
    private String imageUrl;
    private String fatherName;
    private String motherName;
    private String maritialStatus;
    private String occupation;
    private String nationality;
    private String emergencyContactName;
    private String emergencyContactNo;
}
