package com.maliha.patientmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class PatientEntity {
    private String specialId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String password;
    private String role;
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
