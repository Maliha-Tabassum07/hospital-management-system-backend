package com.maliha.patientmanagement.controller;

import com.maliha.patientmanagement.entity.PatientEntity;
import com.maliha.patientmanagement.model.*;
import com.maliha.patientmanagement.service.PatientService;
import com.maliha.patientmanagement.utilities.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PatientDTO patientDTO) throws Exception {
        try {
            PatientEntity createdPatient = patientService.createPatient(patientDTO);
            List<String> roles = new ArrayList<String>();
            roles.add("ROLE_" + createdPatient.getRole());
            String accessToken = JWTUtils.generateToken(createdPatient.getEmail(), roles);
            UserRegistrationResponse response = new UserRegistrationResponse(createdPatient, accessToken);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/create/health-record")
    public ResponseEntity<?> createHealthRecord(@RequestBody HealthRecordDTO healthRecordDTO)throws Exception{
        return new ResponseEntity<>(patientService.createHealthRecord(healthRecordDTO),HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllPatient(){
        return new ResponseEntity<>(patientService.getAllPatient(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/all/health-record")
    public List<HealthRecordViewDTO> getAllPatientHealthRecord(){
        return patientService.getAllHealthRecord();
    }
    @GetMapping("/{patientId}/health-record")
    public ResponseEntity<?> getPatientHealthRecordById(@PathVariable Integer patientId)throws Exception{
        return new ResponseEntity<>(patientService.getHealthRecordById(patientId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatientById(@PathVariable Integer patientId)throws Exception{
        return new ResponseEntity<>(patientService.getPatientById(patientId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/health-record")
    public ResponseEntity<?> viewHealthRecord()throws Exception{
        return new ResponseEntity<>(patientService.getSelfHealthRecord(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/patient-info")
    public ResponseEntity<?> viewProfile()throws Exception{
        return new ResponseEntity<>(patientService.getSelfInfo(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/getPatientByEmail")
    public PatientFeignDTO getPatientByEmail(@RequestParam("email") String email){
        return patientService.getPatientByEmail(email);
    }
    @GetMapping("/recommendation")
    public RecommendationDTO getPatientRecommendation() throws Exception{
        return patientService.getRecommendation();
    }
    @PutMapping("/update/patient-profile")
    public ResponseEntity<?> updateProfile(@RequestBody PatientDTO patientDTO) {
        return new ResponseEntity<>(patientService.updatePatientProfile(patientDTO),HttpStatus.ACCEPTED);
    }
    @PutMapping("/update/health-record")
    public ResponseEntity<?> updateHealthRecord(@RequestBody HealthRecordDTO healthRecordDTO) throws Exception{
        return new ResponseEntity<>(patientService.updateHealthRecord(healthRecordDTO),HttpStatus.ACCEPTED);
    }
    @GetMapping("/count")
    public ResponseEntity<?> getPatientCount(){
        return new ResponseEntity<>(patientService.getCount(),HttpStatus.ACCEPTED);
    }
}
