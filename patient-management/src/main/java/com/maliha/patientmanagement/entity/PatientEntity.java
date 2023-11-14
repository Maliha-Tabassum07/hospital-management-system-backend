package com.maliha.patientmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class PatientEntity {
    @Id
    private String id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Transient
    private Long numericId;

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
    private String maritialStatus;
    private String occupation;
    private String nationality;
    private String emergencyContactName;
    private String emergencyContactNo;

    public PatientEntity() {
    }

    public PatientEntity(String id, Long numericId, String name, String email, String password, String role, String address, String phone, String gender, String imageUrl, String fatherName, String motherName, String maritialStatus, String occupation, String nationality, String emergencyContactName, String emergencyContactNo) {
        this.id = id;
        this.numericId = numericId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.imageUrl = imageUrl;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.maritialStatus = maritialStatus;
        this.occupation = occupation;
        this.nationality = nationality;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNo = emergencyContactNo;
    }

    @PrePersist
    private void generateCustomId() {
        String prefix = "P";
        String formattedId = String.format("%s%02d", prefix, numericId);
        setId(formattedId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMaritialStatus() {
        return maritialStatus;
    }

    public void setMaritialStatus(String maritialStatus) {
        this.maritialStatus = maritialStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNumericId() {
        return numericId;
    }

    public void setNumericId(Long numericId) {
        this.numericId = numericId;
    }
}
