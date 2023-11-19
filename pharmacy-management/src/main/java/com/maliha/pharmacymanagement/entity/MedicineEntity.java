package com.maliha.pharmacymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicine")
public class MedicineEntity {

    private String specialId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String medicineName;
    private String genericName;
    private String medicineType;
    private String manufacturer;
    private String concentration;
    private String unitPrice;
    private LocalDate expirationDate;
    private String sideEffect;
    @ManyToMany
    private List<SymptomEntity> symptom;

}
