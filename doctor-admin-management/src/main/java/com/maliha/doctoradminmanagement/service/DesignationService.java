package com.maliha.doctoradminmanagement.service;

import com.maliha.doctoradminmanagement.entity.DepartmentEntity;
import com.maliha.doctoradminmanagement.entity.DesignationEntity;
import com.maliha.doctoradminmanagement.model.DepartmentDTO;
import com.maliha.doctoradminmanagement.model.DesignationDTO;
import com.maliha.doctoradminmanagement.repository.DesignationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DesignationService {
    @Autowired
    private DesignationRepository designationRepository;

    public List<DesignationDTO> getAllDesignation() {
        List<DesignationDTO> designationDTOList= new ArrayList<>();
        for(DesignationEntity designationEntity:designationRepository.findAll()){
            designationDTOList.add(new ModelMapper().map(designationEntity,DesignationDTO.class));
        }
        return designationDTOList.stream()
                .sorted(Comparator.comparing(DesignationDTO::getName))
                .collect(Collectors.toList());
    }
    public DesignationDTO createDesignation(DesignationDTO designationDTO){
        DesignationEntity designationEntity=new DesignationEntity();
        designationEntity.setName(designationDTO.getName());
        return new ModelMapper().map(designationRepository.save(designationEntity),DesignationDTO.class);
    }
}
