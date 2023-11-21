package com.maliha.doctoradminmanagement.controller;

import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import com.maliha.doctoradminmanagement.model.DoctorDTO;
import com.maliha.doctoradminmanagement.model.UserRegistrationResponse;
import com.maliha.doctoradminmanagement.service.DoctorService;
import com.maliha.doctoradminmanagement.utilities.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/all")
    public ResponseEntity<?> getAllDoctor(){
        return new ResponseEntity<>(doctorService.getAllDoctor(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}/get")
    public ResponseEntity<?> getDoctorById(@PathVariable Integer id) throws Exception{
        return new ResponseEntity<>(doctorService.getDoctorById(id),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{specialId}/get")
    public ResponseEntity<?> getDoctorById(@PathVariable String specialId) throws Exception{
        return new ResponseEntity<>(doctorService.getDoctorBySpecialId(specialId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{name}/get")
    public ResponseEntity<?> getDoctorByName(@PathVariable String name) throws Exception{
        return new ResponseEntity<>(doctorService.getDoctorByName(name),HttpStatus.ACCEPTED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateDoctorById(@RequestBody DoctorDTO doctorDTO) throws Exception{
        return new ResponseEntity<>(doctorService.updateDoctor(doctorDTO),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{departmentId}/all")
    public ResponseEntity<?> getDoctorByDepartmentId(@PathVariable Long departmentId) throws Exception{
        return new ResponseEntity<>(doctorService.getAllDoctorbyDepartmentId(departmentId),HttpStatus.ACCEPTED);
    }

}
