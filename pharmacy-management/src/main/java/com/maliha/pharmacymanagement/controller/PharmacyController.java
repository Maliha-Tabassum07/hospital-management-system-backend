package com.maliha.pharmacymanagement.controller;

import com.maliha.pharmacymanagement.model.MedicineDTO;
import com.maliha.pharmacymanagement.service.MedicineService;
import com.maliha.pharmacymanagement.service.PopulateMedicineService;
import com.maliha.pharmacymanagement.service.PopulateSymptomService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/create")
    public ResponseEntity<?> createMedicine(@RequestBody MedicineDTO medicineDTO) throws Exception{
        return new ResponseEntity<>(medicineService.createMedicine(medicineDTO),HttpStatus.CREATED);
    }
    @PutMapping("/{medicineId}/update")
    public ResponseEntity<?> updateMedicine(@PathVariable Integer medicineId,@RequestBody MedicineDTO medicineDTO) throws Exception{
        return new ResponseEntity<>(medicineService.updateMedicine(medicineDTO,medicineId),HttpStatus.CREATED);
    }
    @GetMapping("/medicine/all")
    public ResponseEntity<?> getAllMedicine( ) throws Exception{
            return new ResponseEntity<>(medicineService.getAllMedicine(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/medicineBySymptomId")
    public List<String> getAllMedicineBySymptomId(@RequestParam Long symptomId) throws Exception{
        return medicineService.getAllSymptomId(symptomId);
    }
    @GetMapping("/{symptomName}")
    public ResponseEntity<?> getAllMedicineBySymptomName(@PathVariable String symptomName) throws Exception{
        return new ResponseEntity<>(medicineService.getAllBySymptomName(symptomName), HttpStatus.ACCEPTED);
    }
    @GetMapping("/{specialId}/get")
    public ResponseEntity<?> getMedicineBySpecialId(@PathVariable String specialId)throws Exception{
        return new ResponseEntity<>(medicineService.getBySpecialId(specialId),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{medicineId}/delete")
    public ResponseEntity<?> deleteMedicineById(@PathVariable Integer medicineId)throws Exception{
        return new ResponseEntity<>(medicineService.deleteMedicine(medicineId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{input}/search")
    public ResponseEntity<?> medicineHelpDesk(@PathVariable String input)throws Exception{
        return new ResponseEntity<>(medicineService.medicineHelpDesk(input),HttpStatus.ACCEPTED);
    }
}
