package com.maliha.doctoradminmanagement.controller;

import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import com.maliha.doctoradminmanagement.model.DoctorDTO;
import com.maliha.doctoradminmanagement.model.DoctorFeignDTO;
import com.maliha.doctoradminmanagement.model.DoctorViewDTO;
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
    @GetMapping("/profile")
    public ResponseEntity<?> getDoctorProfile() throws Exception{
        return new ResponseEntity<>(doctorService.getDoctorById(),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{designationId}/designationId/get")
    public ResponseEntity<?> getDoctorBySpecialId(@PathVariable Long designationId) throws Exception{
        return new ResponseEntity<>(doctorService.getAllDoctorByDesignationId(designationId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{designationName}/designationName/get")
    public ResponseEntity<?> getDoctorByDesignationName(@PathVariable String designationName) throws Exception{
        return new ResponseEntity<>(doctorService.getAllDoctorByDesignationName(designationName),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{departmentName}/departmentName/get")
    public ResponseEntity<?> getDoctorByDepartmentName(@PathVariable String departmentName) throws Exception{
        return new ResponseEntity<>(doctorService.getAllDoctorByDepartmentName(departmentName),HttpStatus.ACCEPTED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateDoctorProfile(@RequestBody DoctorDTO doctorDTO) throws Exception{
        return new ResponseEntity<>(doctorService.updateDoctor(doctorDTO),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{departmentId}/all")
    public ResponseEntity<?> getDoctorByDepartmentId(@PathVariable Long departmentId) throws Exception{
        return new ResponseEntity<>(doctorService.getAllDoctorbyDepartmentId(departmentId),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{input}/search")
    public ResponseEntity<?> getDoctorHelpDesk(@PathVariable String input)throws Exception{
        return new ResponseEntity<>(doctorService.doctorHelpDesk(input),HttpStatus.ACCEPTED);
    }
    //feign client
    @GetMapping("/getDoctorByEmail")
    public DoctorFeignDTO getDoctorByEmail(@RequestParam("email") String email){
        return doctorService.getDoctorByEmail(email);
    }
    //feign client
    @GetMapping("/getAllDoctorByDepartmentId")
    public List<String> getAllDoctorByDepartmentId(@RequestParam Long departmentId) throws Exception{
        return doctorService.getAllDoctorNameByDepartmentId(departmentId);
    }
}
