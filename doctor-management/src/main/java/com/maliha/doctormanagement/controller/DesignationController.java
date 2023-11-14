package com.maliha.doctormanagement.controller;

import com.maliha.doctormanagement.service.PopulateDesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/designation")
public class DesignationController {
    @Autowired
    private PopulateDesignationService populateDesignationService;

    @GetMapping("/populate")
    public ResponseEntity<?> populateDesignation( ){

        if(populateDesignationService.populateDesignation()){
            return new ResponseEntity<>("Database populated", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("Cannot perform this action", HttpStatus.FORBIDDEN);
    }
}
