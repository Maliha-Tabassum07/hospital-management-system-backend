package com.example.demo.service;

import com.example.demo.entity.DepartmentEntity;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PopulateDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Boolean populateDepartment(){
//        if (departmentRepository.existsById(1l)){
//            return false;
//        }
        DepartmentEntity departmentEntity=new DepartmentEntity();
        departmentEntity.setName("CARDIOLOGY");
        departmentEntity.setDescription("With the help of State-of-the-heart-technology, equipment and monitoring devices, our cardiologists are providing excellent heart care to all the patients.");
        departmentRepository.save(departmentEntity);
        return true;

    }
}
