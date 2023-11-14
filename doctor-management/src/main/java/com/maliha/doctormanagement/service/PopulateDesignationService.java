package com.maliha.doctormanagement.service;

import com.maliha.doctormanagement.entity.DepartmentEntity;
import com.maliha.doctormanagement.entity.DesignationEntity;
import com.maliha.doctormanagement.repository.DepartmentRepository;
import com.maliha.doctormanagement.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulateDesignationService {
    @Autowired
    private DesignationRepository designationRepository;

    public Boolean populateDesignation(){
        if (designationRepository.existsById(1l)){
            return false;
        }
        DesignationEntity designationEntity=new DesignationEntity();
        designationEntity.setName("Head of the department");
        designationRepository.save(designationEntity);

        designationEntity=new DesignationEntity();
        designationEntity.setName("Professor");
        designationRepository.save(designationEntity);

        designationEntity=new DesignationEntity();
        designationEntity.setName("Assistant Professor");
        designationRepository.save(designationEntity);

        designationEntity=new DesignationEntity();
        designationEntity.setName("Sr. Consultant");
        designationRepository.save(designationEntity);

        designationEntity=new DesignationEntity();
        designationEntity.setName("Consultant");
        designationRepository.save(designationEntity);
        return true;

    }
}
