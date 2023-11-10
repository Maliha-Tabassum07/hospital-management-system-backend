package com.maliha.doctormanagement.model;

import com.maliha.doctormanagement.entity.DepartmentEntity;
import com.maliha.doctormanagement.entity.DesignationEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import java.util.List;

public class DoctorDTO {
    private String id;
    private String name;
    @NotBlank(message = "Your Id cannot be null")
    @Email(message = "Email must be a valid email address")
    private String email;
    @NotBlank(message = "Password cannot be null")
    private String password;
    private String address;
    private String imageUrl;
    private String qualification;
    private DepartmentEntity department;
    private DesignationEntity designation;
    private List<DepartmentEntity> specialty;
    public DoctorDTO() {
    }

    public DoctorDTO(String id,String name, String email, String password, String address, String imageUrl, String qualification, DepartmentEntity department, DesignationEntity designation, List<DepartmentEntity> specialty) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.imageUrl = imageUrl;
        this.qualification = qualification;
        this.department = department;
        this.designation = designation;
        this.specialty = specialty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
