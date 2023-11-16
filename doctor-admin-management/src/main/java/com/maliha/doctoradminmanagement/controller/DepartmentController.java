package com.maliha.doctoradminmanagement.controller;
import com.maliha.doctoradminmanagement.model.DepartmentDTO;
import com.maliha.doctoradminmanagement.service.DepartmentService;
import com.maliha.doctoradminmanagement.service.PopulateDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private PopulateDepartmentService populateDepartmentService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/populate")
    public ResponseEntity<?> populateDepartment( ){
        if(populateDepartmentService.populateDepartment()){
            System.out.println("Controller");
            return new ResponseEntity<>("Database populated", HttpStatus.ACCEPTED);}
        return new ResponseEntity<>("Cannot perform this action", HttpStatus.FORBIDDEN);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDTO),HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllDepartment(@RequestBody DepartmentDTO departmentDTO){
        return new ResponseEntity<>(departmentService.getAllDepartment(),HttpStatus.CREATED);
    }
    @GetMapping("/{departmentId}/get")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long departmentId){
        return new ResponseEntity<>(departmentService.getAllDepartment(),HttpStatus.CREATED);
    }
    @PutMapping("/{departmentId}/update")
    public ResponseEntity<?> updateDepartment (@PathVariable Long departmentId,@RequestBody DepartmentDTO departmentDTO)throws Exception{
        return new ResponseEntity<>(departmentService.updateDepartment(departmentId,departmentDTO),HttpStatus.CREATED);
    }
    @GetMapping("/{departmentName}/search")
    public ResponseEntity<?> searchDepartment(@PathVariable String departmentName ) throws Exception {
        return new ResponseEntity<>(departmentService.getDepartmentByName(departmentName),HttpStatus.ACCEPTED);
    }

}
