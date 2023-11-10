package com.maliha.doctormanagement.service;

import com.maliha.doctormanagement.entity.DoctorEntity;
import com.maliha.doctormanagement.model.DoctorDTO;
import com.maliha.doctormanagement.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public DoctorDTO createUser(DoctorDTO user) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        if(doctorRepository.findByEmail(user.getEmail()).isPresent())
            throw new Exception("Record already exists");

        DoctorEntity userEntity = new DoctorEntity();
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setRole("ADMIN");
        DoctorEntity storedUserDetails =doctorRepository.save(userEntity);
        DoctorDTO returnedValue = modelMapper.map(storedUserDetails,DoctorDTO.class);
        return returnedValue;
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

}
