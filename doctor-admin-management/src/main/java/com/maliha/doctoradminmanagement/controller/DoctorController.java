package com.maliha.doctoradminmanagement.controller;

import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import com.maliha.doctoradminmanagement.model.DoctorDTO;
import com.maliha.doctoradminmanagement.model.UserRegistrationResponse;
import com.maliha.doctoradminmanagement.service.DoctorService;
import com.maliha.doctoradminmanagement.utilities.JWTUtils;
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
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DoctorDTO doctorDto) throws Exception {
        try {
            DoctorEntity createdDoctor = doctorService.createUser(doctorDto);
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
