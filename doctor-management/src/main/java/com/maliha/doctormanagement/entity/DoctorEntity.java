package com.maliha.doctormanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctor")
public class DoctorEntity {
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
    private String imageUrl;
    private String qualification;
    @ManyToOne
    private DepartmentEntity department;
    @ManyToOne
    private  DesignationEntity designation;
    @ManyToMany
    private List<DepartmentEntity> specialty;

    @PrePersist
    private void generateCustomId() {
        String prefix = "D";
        String formattedId = String.format("%s%02d", prefix, numericId);
        setId(formattedId);
    }

    public DoctorEntity(String id, Long numericId, String name, String email, String password, String role, String address, String imageUrl, String qualification, DepartmentEntity department, DesignationEntity designation, List<DepartmentEntity> specialty) {
        this.id = id;
        this.numericId = numericId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
        this.imageUrl = imageUrl;
        this.qualification = qualification;
        this.department = department;
        this.designation = designation;
        this.specialty = specialty;
    }

    public DoctorEntity() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public DesignationEntity getDesignation() {
        return designation;
    }

    public void setDesignation(DesignationEntity designation) {
        this.designation = designation;
    }

    public List<DepartmentEntity> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List<DepartmentEntity> specialty) {
        this.specialty = specialty;
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
