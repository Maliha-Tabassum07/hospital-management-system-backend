package com.maliha.doctoradminmanagement.service;


import com.maliha.doctoradminmanagement.entity.DepartmentEntity;
import com.maliha.doctoradminmanagement.entity.DoctorEntity;
import com.maliha.doctoradminmanagement.model.DoctorDTO;
import com.maliha.doctoradminmanagement.repository.DepartmentRepository;
import com.maliha.doctoradminmanagement.repository.DesignationRepository;
import com.maliha.doctoradminmanagement.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService implements UserDetailsService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DesignationRepository designationRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public DoctorEntity createUser(DoctorDTO doctorDTO) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        if(doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent())
            throw new Exception("Record already exists");
        DoctorEntity userEntity = new DoctorEntity();
        userEntity.setName(doctorDTO.getName());
        userEntity.setEmail(doctorDTO.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(doctorDTO.getPassword()));
        userEntity.setRole("DOCTOR");
        userEntity.setAddress(doctorDTO.getAddress());
        userEntity.setImageUrl(doctorDTO.getImageUrl());
        userEntity.setQualification(doctorDTO.getQualification());
        userEntity.setDepartment(departmentRepository.findById(doctorDTO.getDepartment()).orElseThrow(() -> new Exception()));
        userEntity.setImageUrl(doctorDTO.getImageUrl());
        userEntity.setDesignation(designationRepository.findById(doctorDTO.getDesignation()).orElseThrow(() -> new Exception()));
        List<DepartmentEntity> departmentEntityList=new ArrayList<>();
        for(Long id:doctorDTO.getSpecialty()){
            departmentEntityList.add(departmentRepository.findById(id).orElseThrow(() -> new Exception()));
        }
        userEntity.setSpecialty(departmentEntityList);
        DoctorEntity storedUserDetails =doctorRepository.save(userEntity);
        storedUserDetails.setId(String.format("%s%02d", "D", storedUserDetails.getNumericId()));
        return doctorRepository.save(storedUserDetails);
    }
    public DoctorEntity readByEmail(String email) {
        return doctorRepository.findByEmail(email).get();
    }
    public DoctorDTO getDoctor(String email) {
        DoctorEntity doctorEntity = doctorRepository.findByEmail(email).get();
        if(doctorEntity == null) throw new UsernameNotFoundException("No record found");
        DoctorDTO returnValue = new DoctorDTO();
        BeanUtils.copyProperties(doctorEntity,returnValue);
        return returnValue;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DoctorEntity userEntity = doctorRepository.findByEmail(email).get();
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(userEntity.getRole()));
        if (userEntity == null) throw new UsernameNotFoundException(email)
                ;
        return new User(userEntity.getEmail(), userEntity.getPassword(),
                true, true, true, true,
                roles);
    }

}
