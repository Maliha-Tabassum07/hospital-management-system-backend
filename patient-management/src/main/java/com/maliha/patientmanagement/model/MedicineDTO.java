package com.maliha.patientmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {
    private String medicineName;
    private String genericName;
    private String medicineType;
    private String manufacturer;
    private String concentration;
    private String unitPrice;
    private LocalDate expirationDate;
    private String sideEffect;
    private List<Long> symptomList;
}
