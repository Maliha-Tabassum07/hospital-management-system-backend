package com.maliha.pharmacymanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "medicine")
public class MedicineEntity {

    @Id
    private String id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Transient
    private Long numericId;

    private String medicineName;
    private String genericName;
    private String medicineType;
    private String manufacturer;
    private String concentration;
    private String unitPrice;
    private LocalDate expirationDate;
    private String sideEffect;
    @ManyToOne
    private  SymptomEntity symptom;

    @PrePersist
    private void generateCustomId() {
        String prefix = "M";
        String formattedId = String.format("%s%02d", prefix, numericId);
        setId(formattedId);
    }
    public MedicineEntity() {
    }

    public MedicineEntity(String id, Long numericId, String medicineName, String genericName, String medicineType, String manufacturer, String concentration, String unitPrice, LocalDate expirationDate, String sideEffect, SymptomEntity symptom) {
        this.id = id;
        this.numericId = numericId;
        this.medicineName = medicineName;
        this.genericName = genericName;
        this.medicineType = medicineType;
        this.manufacturer = manufacturer;
        this.concentration = concentration;
        this.unitPrice = unitPrice;
        this.expirationDate = expirationDate;
        this.sideEffect = sideEffect;
        this.symptom = symptom;
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

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public SymptomEntity getSymptom() {
        return symptom;
    }

    public void setSymptom(SymptomEntity symptom) {
        this.symptom = symptom;
    }
}
