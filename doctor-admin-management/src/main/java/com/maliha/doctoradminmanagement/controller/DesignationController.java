package com.maliha.doctoradminmanagement.controller;

import com.maliha.doctoradminmanagement.model.DepartmentDTO;
import com.maliha.doctoradminmanagement.service.DepartmentService;
import com.maliha.doctoradminmanagement.service.DesignationService;
import com.maliha.doctoradminmanagement.service.PopulateDesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/designation")
public class DesignationController {
    @Autowired
    private PopulateDesignationService populateDesignationService;
    @Autowired
    private DesignationService designationService;

    @GetMapping("/populate")
    public ResponseEntity<?> populateDesignation( ){

        if(populateDesignationService.populateDesignation()){
            return new ResponseEntity<>("Database populated", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("Cannot perform this action", HttpStatus.FORBIDDEN);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(designationService.getAllDesignation(),HttpStatus.CREATED);
    }
}
