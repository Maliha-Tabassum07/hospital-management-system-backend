package com.maliha.doctormanagement.controller;

import com.maliha.doctormanagement.model.DoctorDTO;
import com.maliha.doctormanagement.model.UserRegistrationResponse;
import com.maliha.doctormanagement.service.DoctorService;
import com.maliha.doctormanagement.utilities.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DoctorDTO doctorDto) throws Exception {
        try {
            DoctorDTO createdDoctor = doctorService.createUser(doctorDto);
            List<String> roles = new ArrayList<String>();
            roles.add("ROLE_" + createdDoctor.getRole());

            String accessToken = JWTUtils.generateToken(createdDoctor.getEmail(), roles);
            UserRegistrationResponse response = new UserRegistrationResponse(createdDoctor, accessToken);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
