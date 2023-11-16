package com.maliha.doctoradminmanagement.service;

import com.maliha.doctoradminmanagement.entity.DepartmentEntity;
import com.maliha.doctoradminmanagement.model.DepartmentDTO;
import com.maliha.doctoradminmanagement.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    public List<DepartmentDTO> getAllDepartment() {
        List<DepartmentDTO> departmentDTOList= new ArrayList<>();
        for(DepartmentEntity departmentEntity:departmentRepository.findAll()){
            departmentDTOList.add(new ModelMapper().map(departmentEntity,DepartmentDTO.class));
        }
        return departmentDTOList.stream()
                .sorted(Comparator.comparing(DepartmentDTO::getName))
                .collect(Collectors.toList());
    }
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO){
        DepartmentEntity departmentEntity=new DepartmentEntity();
        departmentEntity.setName(departmentDTO.getName());
        departmentEntity.setDescription(departmentDTO.getDescription());
        departmentEntity.setFloorNo(departmentDTO.getFloorNo());
        return new ModelMapper().map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }
    public DepartmentDTO getDepartmentById (Long departmentId) throws Exception {
        if(departmentRepository.existsById(departmentId)){
            return new ModelMapper().map(departmentRepository.findById(departmentId).orElseThrow(() -> new NullPointerException()),DepartmentDTO.class);
        }
        throw new Exception();
    }
    public DepartmentDTO updateDepartment (Long departmentId,DepartmentDTO departmentDTO) throws Exception{
        DepartmentEntity departmentEntity=departmentRepository.findById(departmentId).orElseThrow(() -> new NullPointerException());
        departmentEntity.setName(departmentDTO.getName());
        departmentEntity.setDescription(departmentDTO.getDescription());
        departmentEntity.setFloorNo(departmentDTO.getFloorNo());
        return new ModelMapper().map(departmentRepository.save(departmentEntity),DepartmentDTO.class);
    }
    public DepartmentDTO getDepartmentByName (String departmentName) throws Exception {
        if(departmentRepository.existsByName(departmentName)){
            return new ModelMapper().map(departmentRepository.findByName(departmentName).orElseThrow(() -> new NullPointerException()),DepartmentDTO.class);
        }
        throw new Exception();
    }
}
