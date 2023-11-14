package com.maliha.doctormanagement.controller;

import com.maliha.doctormanagement.service.DoctorService;
import com.maliha.doctormanagement.service.PopulateDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private PopulateDepartmentService populateDepartmentService;

    @GetMapping("/populate")
    public ResponseEntity<?> populateDepartment( ){

        if(populateDepartmentService.populateDepartment()){
            System.out.println("Controller");
            return new ResponseEntity<>("Database populated", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("Cannot perform this action", HttpStatus.FORBIDDEN);
    }

}
