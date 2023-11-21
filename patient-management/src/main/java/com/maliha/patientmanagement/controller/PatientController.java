package com.maliha.patientmanagement.controller;

import com.maliha.patientmanagement.entity.PatientEntity;
import com.maliha.patientmanagement.model.PatientDTO;
import com.maliha.patientmanagement.model.UserRegistrationResponse;
import com.maliha.patientmanagement.service.PatientService;
import com.maliha.patientmanagement.utilities.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
