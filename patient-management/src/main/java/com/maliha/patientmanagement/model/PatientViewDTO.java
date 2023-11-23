package com.maliha.patientmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientViewDTO {
    private String name;
    private String specialId;
    private String address;
    private String phone;
    private String gender;
    private String imageUrl;
    private String fatherName;
    private String motherName;
    private String maritalStatus;
    private String occupation;
    private String nationality;
    private String emergencyContactName;
    private String emergencyContactNo;
}
