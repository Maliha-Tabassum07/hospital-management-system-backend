package com.maliha.pharmacymanagement.controller;

import com.maliha.pharmacymanagement.service.MedicineService;
import com.maliha.pharmacymanagement.service.PopulateMedicineService;
import com.maliha.pharmacymanagement.service.PopulateSymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private PopulateSymptomService populateSymptomService;
    @Autowired
    private PopulateMedicineService populateMedicineService;
    @PostMapping("/populate/symptom")
    public ResponseEntity<?> populateSymptom( ){
        if(populateSymptomService.populateSymptom()){
            return new ResponseEntity<>("Database populated", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("Cannot perform this action", HttpStatus.FORBIDDEN);
    }
    @PostMapping("/populate/medicine")
    public ResponseEntity<?> populateMedicine( ){
        if(populateMedicineService.populateMedicine()){
            return new ResponseEntity<>("Database populated", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("Cannot perform this action", HttpStatus.FORBIDDEN);
    }
    @GetMapping("/medicine/all")
    public ResponseEntity<?> getAllMedicine( ) throws Exception{
            return new ResponseEntity<>(medicineService.getAllMedicine(), HttpStatus.ACCEPTED);
    }
}
