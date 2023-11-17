package com.maliha.doctoradminmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor")
public class DoctorEntity {
    private String specialId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String password;
    private String role;
    private String address;
    private String imageUrl;
    private String phone;
    private String qualification;
    private Integer room;
    @ManyToOne
    private DepartmentEntity department;
    @ManyToOne
    private  DesignationEntity designation;
    @ManyToMany
    private List<DepartmentEntity> specialty;
}
